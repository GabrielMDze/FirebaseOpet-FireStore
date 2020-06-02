package com.example.firebaseopet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;

public class ImageActivity extends AppCompatActivity {

    private ImageView imagePicked;
    private File imageFile;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imagePicked = findViewById(R.id.imagePicked);
    }

    @Override
    protected void onStart(){
        super.onStart();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void selecionarImagem(View view) {
        ImagePicker.Companion.with(this)
                .compress(1024)
                .maxResultSize(1080,1080)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            imagePicked.setImageURI(uri);

            imageFile = ImagePicker.Companion.getFile(data);
        }else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Tarefa Cancelada", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadFile(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userPath = "images/" + user.getUid() + "/";
        final StorageReference imageRef = storageReference.child(userPath+"image_"+Util.getTimeStamp()+".png");

        UploadTask task = imageRef.putFile(Uri.fromFile(imageFile));
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ImageActivity.this, "Falha no upload!", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(ImageActivity.this, imageRef.getDownloadUrl().toString(), Toast.LENGTH_SHORT).show();
                //imageRef.getDownloadUrl();
            }
        });
    }
}
