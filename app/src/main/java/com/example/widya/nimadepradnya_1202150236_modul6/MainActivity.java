package com.example.widya.nimadepradnya_1202150236_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText user, pass;
    ProgressDialog dialog;
    FirebaseAuth authen;
    FirebaseAuth.AuthStateListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        dialog = new ProgressDialog(this);
        authen = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = authen.getCurrentUser();
                if (user!=null){
                    Intent pindah = new Intent(MainActivity.this, HomeActivity.class);
                    pindah.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(pindah);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        authen.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        authen.removeAuthStateListener(listener);
    }

    public void login(View view) {
        dialog.setMessage("Logging in");

        String inuser = user.getText().toString();
        String inpass = pass.getText().toString();

        if (!TextUtils.isEmpty(inuser)|| !TextUtils.isEmpty(inpass)){
            dialog.show();

            authen.signInWithEmailAndPassword(inuser, inpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent pindah = new Intent(MainActivity.this, HomeActivity.class);
                        pindah.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(pindah);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });
        }else {
            Snackbar.make(findViewById(R.id.actlogin), "Field is Empty", Snackbar.LENGTH_LONG).show();
            user.setError("Field is Required");
            pass.setError("Field is Required");
        }
    }

    public void daftar(View view) {
        //Menampilkan dialog
        dialog.setMessage("Membuat akun. . .");
        dialog.show();

        //Membaca input user
        String inuser = user.getText().toString().trim();
        String inpass = pass.getText().toString().trim();

        //Apakah input user kosong?

        //Jika tidak :
        if(!TextUtils.isEmpty(inuser)||!TextUtils.isEmpty(inpass)){
            //Membuat user baru berdasarkan input user
            authen.createUserWithEmailAndPassword(inuser, inpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Operasi ketika pembuatan user baru berhasil
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                        Intent movehome = new Intent(MainActivity.this, HomeActivity.class);
                        movehome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                        startActivity(movehome);
                        finish();

                        //Operasi ketika pembuatan user baru gagal
                    }else{
                        Log.w("Firebase", task.getException());
                        Toast.makeText(MainActivity.this, "Account creation failed!", Toast.LENGTH_SHORT).show();
                        user.setText(null); pass.setText(null);
                    }
                    //Tutup dialog ketika berhasil atau pun tidak
                    dialog.dismiss();
                }
            });

            //Ketika input user kosong
        }else{
            user.setError("Field is Required");
            pass.setError("Field is Required");
            Toast.makeText(this, "The field is empty", Toast.LENGTH_SHORT).show();
            user.setText(null); pass.setText(null);
        }
    }
}
