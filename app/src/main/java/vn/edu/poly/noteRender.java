package vn.edu.poly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class noteRender extends SQLiteOpenHelper {

    public static final String TABLE_NAME="note";
    public static final String COL_STT="stt";
    public static final String COL_TITLE="title";
    public static final String COL_DATE="date";
    public static final String COL_CONTEXT="context";

    public noteRender(Context context){
        super(context,"note.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME + " ("+COL_STT+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_TITLE+" TEXT,"+COL_DATE+" TEXT,"+COL_CONTEXT+" TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
