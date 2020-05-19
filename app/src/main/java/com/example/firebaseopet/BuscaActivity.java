package com.example.firebaseopet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class BuscaActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textWelcome;
    private FirebaseFirestore db;
    private TextView textResultado;
    private EditText buscaNome, buscaSalarioMaior, buscaSalarioMenor, buscaSalario, buscaFilhos, buscaNomePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        mAuth = FirebaseAuth.getInstance();
        textWelcome = findViewById(R.id.textWelcome);
    }

    @Override
    protected void onStart(){
        super.onStart();
        db = FirebaseFirestore.getInstance();
        textResultado = findViewById(R.id.textResultado);
        buscaNome = findViewById(R.id.buscaNome);
        buscaNomePet = findViewById(R.id.buscaNomePet);
        buscaSalarioMaior = findViewById(R.id.buscaSalarioMaior);
        buscaSalarioMenor = findViewById(R.id.buscaSalarioMenor);
        buscaSalario = findViewById(R.id.buscaSalario);
        buscaFilhos = findViewById(R.id.buscaFilho);
    }

    public void buscaNome(View view) {
        final CollectionReference pessoas = db.collection("pessoas");

        Query query = pessoas.whereEqualTo("nome",buscaNome.getText().toString());
        consulta(query);
    }

    public void buscaNomePet(View view) {
        final CollectionReference pessoas = db.collection("pessoas");

        Query query = pessoas.whereArrayContains("pets",buscaNomePet.getText().toString());
        consulta(query);
    }

    public void buscaSalario(View view) {
        final CollectionReference pessoas = db.collection("pessoas");

        Query query = pessoas.whereGreaterThanOrEqualTo("salario",Double.parseDouble(buscaSalarioMenor.getText().toString())).whereLessThanOrEqualTo("salario",Double.parseDouble(buscaSalarioMaior.getText().toString()));
        consulta(query);
    }

    public void buscaSalarioFilhos(View view) {
        final CollectionReference pessoas = db.collection("pessoas");

        Query query = pessoas.whereGreaterThanOrEqualTo("qtde_filhos",Integer.parseInt(buscaFilhos.getText().toString()));
                                //.whereGreaterThanOrEqualTo("salario",);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            String resultado = "";
                            List<Pessoa> listPessoas = new ArrayList<>();
                            for (QueryDocumentSnapshot documento : task.getResult()){
                                resultado += "ID: " + documento.getId() +'\n'+  documento.getData().toString() + '\n';

                                listPessoas.add(documento.toObject(Pessoa.class));
                            }
                            Double salario = Double.parseDouble(buscaSalario.getText().toString());
                            resultado = "";
                            for (Pessoa p : listPessoas){
                                if (p.salario <= salario || p.salario >= salario ){
                                    resultado += p.toString() + '\n';
                                }
                            }
                            textResultado.setText(resultado);
                        }
                    }
                });

    }

    public void consulta(Query query){
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            String resultado = "";
                            List<Pessoa> listPessoas = new ArrayList<>();
                            for (QueryDocumentSnapshot documento : task.getResult()){
                                resultado += "ID: " + documento.getId() +'\n'+  documento.getData().toString() + '\n';

                                listPessoas.add(documento.toObject(Pessoa.class));
                            }
                            resultado = "";
                            for (Pessoa p : listPessoas){
                                resultado += p.toString() + '\n';
                            }
                            textResultado.setText(resultado);
                        }
                    }
                });
    }
}
