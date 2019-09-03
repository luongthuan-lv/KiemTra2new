package vn.edu.poly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static vn.edu.poly.noteRender.COL_CONTEXT;
import static vn.edu.poly.noteRender.COL_DATE;
import static vn.edu.poly.noteRender.COL_STT;
import static vn.edu.poly.noteRender.COL_TITLE;
import static vn.edu.poly.noteRender.TABLE_NAME;

public class noteDao {
    public noteRender noteRender;

    public noteDao(Context context) {
        noteRender = new noteRender(context);
    }

    public long insertNote(Note note) {
        SQLiteDatabase sqLiteDatabase = noteRender.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       // contentValues.put(COL_STT, note.stt);
        contentValues.put(COL_TITLE, note.title);
        contentValues.put(COL_DATE, note.date);
        contentValues.put(COL_CONTEXT, note.conten);
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long deleteNote(Note note) {
        SQLiteDatabase sqLiteDatabase = noteRender.getWritableDatabase();
        long result= sqLiteDatabase.delete(TABLE_NAME, COL_STT + "=?", new String[]{String.valueOf(note.stt)});
        sqLiteDatabase.close();
        return result;
    }

    public List<Note> getAllNote() {
        SQLiteDatabase sqLiteDatabase = noteRender.getReadableDatabase();
        List<Note> noteList = new ArrayList<>();
        String SELECT = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Note note = new Note();
                note.stt = cursor.getInt(cursor.getColumnIndex(COL_STT));
                note.title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
                note.date = cursor.getString(cursor.getColumnIndex(COL_DATE));
                note.conten = cursor.getString(cursor.getColumnIndex(COL_CONTEXT));


                noteList.add(note);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return noteList;
    }

    public Note getDataById(int id) {
        SQLiteDatabase sqLiteDatabase = noteRender.getReadableDatabase();
        String SELECT = "SELECT * FROM " + TABLE_NAME+" where "+COL_TITLE+"=?";

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, new String[]{id+""});
        cursor.moveToFirst();
        Note note = new Note();
        //note.stt = cursor.getInt(cursor.getColumnIndex(COL_STT));
        note.title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
        note.date = cursor.getString(cursor.getColumnIndex(COL_DATE));
        note.conten = cursor.getString(cursor.getColumnIndex(COL_CONTEXT));
        return note;
    }


    public void Close() {
        noteRender.close();
    }


}
