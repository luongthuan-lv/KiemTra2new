package vn.edu.poly;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder extends RecyclerView.ViewHolder {
    public EditText edtTitle,edtDate;
    public ConstraintLayout contrain;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        contrain=itemView.findViewById(R.id.contrain);
        edtTitle=itemView.findViewById(R.id.edtTitle);
        edtDate=itemView.findViewById(R.id.edtDate);

    }
}
