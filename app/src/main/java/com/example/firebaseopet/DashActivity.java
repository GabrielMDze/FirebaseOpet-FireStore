package com.example.firebaseopet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textWelcome;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        mAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        textWelcome.setText("Bem-vindo"+user.getEmail());
        db = FirebaseFirestore.getInstance();
    }

    public void sair(View view){
        mAuth.signOut();
        Intent inicio = new Intent(DashActivity.this,MainActivity.class);
        startActivity(inicio);
        finish();
    }

    public void registrardadosusuario(View view) {
        Intent cadoneactivity = new Intent(DashActivity.this,CadOneActivity.class);
        startActivity(cadoneactivity);
    }

    public void registrardadosvenda(View view) {
        Intent cadoneactivity = new Intent(DashActivity.this,Cad2Activity.class);
        startActivity(cadoneactivity);
    }

    public void buscar(View view) {
        Intent cadoneactivity = new Intent(DashActivity.this,BuscaActivity.class);
        startActivity(cadoneactivity);
    }

    public void gerardadosnofirebase(View view) {
        List<Pessoa> pessoas = PopulateUtil.loadPessoas();

        for (Pessoa p : pessoas){
            db.collection("pessoas").add(p);
        }
    }

}
