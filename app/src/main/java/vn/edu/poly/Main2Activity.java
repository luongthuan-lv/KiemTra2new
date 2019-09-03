package vn.edu.poly;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    private noteDao noteDao;
    private Toolbar toolbar2;
    private EditText edtAddContext, edtAddDate, edtAddTitle;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);

        edtAddContext = findViewById(R.id.edtAddContext);
        edtAddTitle = findViewById(R.id.edtAddTitle);
        edtAddDate = findViewById(R.id.edtAddDate);
        btnAdd = findViewById(R.id.btnAdd);

        edtAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectDate();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteDao = new noteDao(Main2Activity.this);
                String title = edtAddTitle.getText().toString().trim();
                String context = edtAddContext.getText().toString().trim();
                String date = edtAddDate.getText().toString().trim();

                if (title.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Vui lòng nhập tiêu đề !", Toast.LENGTH_SHORT).show();
                    return;
                } else if (context.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Vui lòng nhập nội dung!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (date.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Vui lòng nhập thời gian!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Note note = new Note();
                note.title = title;
                note.conten = context;
                note.date = date;
                long result = noteDao.insertNote(note);

                edtAddTitle.setText("");
                edtAddContext.setText("");
                edtAddDate.setText("");

                if (result > 0) {
                    Toast.makeText(Main2Activity.this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main2Activity.this, "Thêm Không Thành Công!", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(Main2Activity.this,MainActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void SelectDate() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtAddDate.setText(year + " / " + (month + 1) + " / " + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
