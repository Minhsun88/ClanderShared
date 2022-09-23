package com.example.gp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.gp.databinding.ActivityAddNewNoteBinding;
import com.example.gp.databinding.ActivityIndexBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddNewNote extends AppCompatActivity {
    private ActivityAddNewNoteBinding B;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = ActivityAddNewNoteBinding.inflate(getLayoutInflater());
        setContentView(B.getRoot());

        B.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = B.edTitle.getText().toString();
                String Text = B.edText.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                String Time =  format.format(new Date());

                HashMap<String,Object> Notes = new HashMap<>();
                Notes.put("Title",Title);
                Notes.put("Text",Text);
                Notes.put("Time",Time);

                db.collection("Notes")
                        .document(Title)
                        .set(Notes);

                finish();
            }
        });
    }
}