package com.example.gp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gp.databinding.ActivityGroupBinding;
import com.example.gp.databinding.ActivityPostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Post extends AppCompatActivity {
    private ActivityPostBinding B;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> arrayListPost = new ArrayList<>();
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(B.getRoot());

        db.collection("Post")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot doc: task.getResult())
                            {
                                String PostId = doc.getString("postId");
                                Log.d("Demo",""+PostId);
                                arrayListPost.add(PostId);

                            }
                            Log.d("Demo",""+arrayListPost.size());
                            adapter =new PostAdapter(Post.this,arrayListPost);
                            B.RecyclerView.setAdapter(adapter);
                            B.RecyclerView.setLayoutManager(new LinearLayoutManager(Post.this));
                        }
                    }
                });

        B.btnAddNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Post.this,AddNewPost.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        arrayListPost.clear();  // 清空原本的adapter data
        // 重新載入data
        db.collection("Post")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot doc : task.getResult()){
                                String PostId = doc.getString("postId");
                                arrayListPost.add(PostId);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}