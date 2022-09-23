package com.example.gp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gp.databinding.ActivityNoteBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Note extends AppCompatActivity {
    private ActivityNoteBinding B;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String RcTitle;
    ArrayList<String> arrayList = new ArrayList<>();
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = ActivityNoteBinding.inflate(getLayoutInflater());
        setContentView(B.getRoot());
        Intent it2 =getIntent();
//        String userid =it2.getStringExtra("userid");

        db.collection("Notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot doc: task.getResult())
                            {
                                String Title = doc.getString("Title");
                                RcTitle = Title;
                                Log.d("Demo",""+Title);
                                arrayList.add(RcTitle);

                            }
                            Log.d("Demo",""+arrayList.size());
                            adapter =new NoteAdapter(Note.this,arrayList);
                            B.RecyclerView.setAdapter(adapter);
                            B.RecyclerView.setLayoutManager(new LinearLayoutManager(Note.this));
                        }
                    }
                });


        B.btnAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Note.this,AddNewNote.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        arrayList.clear();  // 清空原本的adapter data
        // 重新載入data
        db.collection("Notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot doc : task.getResult()){
                                String Title = doc.getString("Title");
                                RcTitle = Title;
                                arrayList.add(RcTitle);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}