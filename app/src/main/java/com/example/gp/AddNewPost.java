package com.example.gp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gp.databinding.ActivityAddNewPostBinding;
import com.example.gp.databinding.ActivityPostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddNewPost extends AppCompatActivity {
    private ActivityAddNewPostBinding B;
    private StorageReference StorageRef;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String postId = "Post";
    int Id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = ActivityAddNewPostBinding.inflate(getLayoutInflater());
        setContentView(B.getRoot());
        StorageRef = FirebaseStorage.getInstance().getReference();


        db.collection("MemberData")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot doc : task.getResult()){
                                String name = doc.getString("name");
                                B.textViewName.setText(name);
                            }
                        }
                    }
                });


        B.btnSavePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Text = B.PostEdText.getText().toString();
                String Name = B.textViewName.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                String Time =  format.format(new Date());
                Id = Id+1;
                postId =postId+""+Id;

                HashMap<String,Object> Post = new HashMap<>();
                Post.put("Text",Text);
                Post.put("Time",Time);
                Post.put("Name",Name);
                Post.put("postId",postId);



                db.collection("Post")
                        .document(postId)
                        .set(Post);
                finish();
            }
        });

        B.addPostImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");
                startActivityForResult(it, 101);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == 101 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            ImageView imageView = new ImageView(AddNewPost.this);//新增ImageView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 400);
            imageView.setLayoutParams(params);//調整ImageView大小
            Glide.with(AddNewPost.this)
                    .load(uri)
                    .into(imageView);
            B.addLinear.addView(imageView);

            StorageRef.child("PostImg").child(postId+uri.getLastPathSegment())
                    .putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AddNewPost.this,"上傳成功",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddNewPost.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
//            B.linear.removeAllViews();

        }

    }
}