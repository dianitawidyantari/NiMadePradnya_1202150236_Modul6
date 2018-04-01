package com.example.widya.nimadepradnya_1202150236_modul6;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adaptComent extends RecyclerView.Adapter<adaptComent.CommentHolder>{
    Context con;
    List<dbComent> list;

    public adaptComent(Context con, List<dbComent> list) {
        this.con = con;
        this.list = list;
    }
    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(con).inflate(R.layout.cardview_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        dbComent cur = list.get(position);
        holder.id_coment.setText(cur.getIdcomenter());
        holder.comentt.setText(cur.getComent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        TextView id_coment, comentt;
        public CommentHolder(View itemView) {
            super(itemView);
            id_coment = itemView.findViewById(R.id.idcoment);
            comentt = itemView.findViewById(R.id.coment);
        }
    }
}
