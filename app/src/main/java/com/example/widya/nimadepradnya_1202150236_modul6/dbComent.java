package com.example.widya.nimadepradnya_1202150236_modul6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class dbComent {
    String idcomenter, coment, comentfoto;

    public dbComent(){

    }
    public dbComent(String idcomenter, String coment, String comentfoto){
        this.idcomenter = idcomenter;
        this.coment = coment;
        this.comentfoto = comentfoto;
    }

    public String getIdcomenter() {
        return idcomenter;
    }

    public String getComent() {
        return coment;
    }

    public String getComentfoto() {
        return comentfoto;
    }
}