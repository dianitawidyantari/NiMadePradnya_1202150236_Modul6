package com.example.widya.nimadepradnya_1202150236_modul6;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class dbPost {
    String foto, title, caption, uname, key;

    public dbPost(){

    }
    public dbPost(String foto, String title, String caption, String uname ){
        this.foto = foto;
        this.title = title;
        this.caption = caption;
        this.uname = uname;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFoto() {
        return foto;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getUname() {
        return uname;
    }

    public String getKey() {
        return key;
    }
}