package vn.edu.poly;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerNote;
    private noteDao noteDao;
    private noteAdapter noteAdapter;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        recyclerNote = findViewById(R.id.recyclerNote);
        recyclerNote.setHasFixedSize(true);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 8, RecyclerView.HORIZONTAL, true);

        noteDao = new noteDao(MainActivity.this);
        final List<Note> noteList = noteDao.getAllNote();

        recyclerNote.setLayoutManager(gridLayoutManager);
        noteAdapter = new noteAdapter(MainActivity.this, noteList);
        recyclerNote.setAdapter(noteAdapter);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                startActivity(new Intent(MainActivity.this, Main2Activity.class));

                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
