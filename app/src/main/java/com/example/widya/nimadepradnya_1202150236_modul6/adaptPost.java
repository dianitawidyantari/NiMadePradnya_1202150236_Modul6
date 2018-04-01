package com.example.widya.nimadepradnya_1202150236_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adaptPost extends RecyclerView.Adapter<adaptPost.PostViewHolder>{
    private List<dbPost> list;
    private Context con;

    public adaptPost(List<dbPost> list, Context con){
        this.list = list;
        this.con = con;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(con).inflate(R.layout.cardview_post, parent, false));
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        dbPost current = list.get(position);
        String [] user = current.uname.split("@");
        holder.user.setText(user[0]);
        holder.user.setTag(current.getKey());
        holder.caption.setText(current.getTitle());
        holder.deskripsi.setText(current.getCaption());
        holder.deskripsi.setTag(current.getFoto());
        Glide.with(con).load(current.getFoto()).placeholder(R.drawable.upload).override(450, 450).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView user, caption, deskripsi;
        public PostViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.uploadfoto);
            user = itemView.findViewById(R.id.uploader);
            caption = itemView.findViewById(R.id.caption);
            deskripsi = itemView.findViewById(R.id.deskripsi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pindah = new Intent(con, PostActivity.class);
                    pindah.putExtra("user", user.getText());
                    pindah.putExtra("key", user.getTag().toString());
                    pindah.putExtra("judul", caption.getText());
                    pindah.putExtra("caption", deskripsi.getText());
                    pindah.putExtra("image", deskripsi.getTag().toString());
                    con.startActivity(pindah);
                }
            });

        }
    }
}

