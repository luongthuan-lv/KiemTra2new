package vn.edu.poly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class noteAdapter extends RecyclerView.Adapter<MyViewHolder> {
    public Context context;
    public List<Note> noteList;
    public noteDao noteDao;

    public noteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
        this.noteDao = new noteDao(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.note, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Note note=noteList.get(position);
       holder.edtTitle.setText(note.title);
       holder.edtDate.setText(note.date);
       holder.contrain.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context,Main3Activity.class);
                Note note=noteList.get(holder.getLayoutPosition());
                Bundle bundle=new Bundle();
                bundle.putInt("stt",0);
                bundle.putString("title",note.title);
                bundle.putString("content",note.conten);
                bundle.putString("date",note.date);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
           }
       });





    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


}
