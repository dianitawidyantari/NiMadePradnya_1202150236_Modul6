package com.example.widya.nimadepradnya_1202150236_modul6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Widya on 4/1/2018.
 */

public class fragmentme extends Fragment {
    RecyclerView recview;
    DatabaseReference dbref;
    ArrayList<dbPost> array_list;
    adaptPost adapter;


    public fragmentme() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me, container, false);
        dbref = FirebaseDatabase.getInstance().getReference().child("post");
        array_list = new ArrayList<>();
        adapter = new adaptPost(array_list, this.getContext());
        recview = v.findViewById(R.id.fragmentme);

        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recview.setAdapter(adapter);

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dbPost cur = dataSnapshot.getValue(dbPost.class);
                if (cur.getUname().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                    cur.key = dataSnapshot.getKey();
                    array_list.add(cur);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

}

