package vn.edu.poly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Main3Activity extends AppCompatActivity {
    Toolbar toolbar3;
    EditText edtShowTitle, edtShowContext, edtShowDate;
    public noteDao noteDao;
    public List<Note> noteList;
    public noteAdapter noteAdapter;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        edtShowTitle = findViewById(R.id.edtShowTitle);
        edtShowContext = findViewById(R.id.edtShowContext);
        edtShowDate = findViewById(R.id.edtShowDate);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("bundle");
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            int value = bundle.getInt("stt");

            position = value;
            edtShowTitle.setText(bundle.getString("title"));
            edtShowContext.setText(bundle.getString("content"));
            edtShowDate.setText(bundle.getString("date"));


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDelete:
                AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this);
                builder.setMessage("Bạn có muốn xóa dữ liệu?");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Main3Activity.this, "Đã hủy thao tác!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        edtShowTitle.setText("");
                        edtShowDate.setText("");
                        Note note = noteList.get(position);
                        noteDao = new noteDao(Main3Activity.this);
                        noteDao.deleteNote(note);
                        noteList.remove(position);
                        noteAdapter.notifyDataSetChanged();
                        startActivity(new Intent(Main3Activity.this, MainActivity.class));


                    }
                });

                builder.create();
                builder.show();

                break;
            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }


}
