package com.example.firebaseopet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadOneActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText editNome, editTelefone;
    private Spinner spinnerPrioridade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_one);

        editNome = findViewById(R.id.editNome);
        editTelefone = findViewById(R.id.editTelefone);
        spinnerPrioridade = (Spinner) findViewById(R.id.prioridade_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CadOneActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.prioridade_array));
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridade.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void salvarnofirebase1(View view) {
        String titulo = editNome.getText().toString();
        String categoria = editTelefone.getText().toString();
        String priopridade = spinnerPrioridade.getSelectedItem().toString();

        Map<String,Object> dadosTarefa = new HashMap<>();
        dadosTarefa.put("data criacao", FieldValue.serverTimestamp());
        dadosTarefa.put("titulo",titulo);
        dadosTarefa.put("prioridade",priopridade);
        dadosTarefa.put("categoria",categoria);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("tarefas").document(user.getUid())
                .set(dadosTarefa)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        String mensagem = "Dados cadastrados com sucesso!";
                        Toast.makeText(CadOneActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String mensagem = "Erro ao gravar os dados.";
                        Toast.makeText(CadOneActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
