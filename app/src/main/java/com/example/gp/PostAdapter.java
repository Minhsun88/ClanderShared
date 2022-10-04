package com.example.gp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PostAdapter  extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context mContext;
    ArrayList<String> arrayListPost;

    public PostAdapter(Context mContext, ArrayList<String> arrayListPost) {
        this.mContext = mContext;
        this.arrayListPost = arrayListPost;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.post_view,parent,false);

        ViewHolder holder =  new ViewHolder(itemView);
        holder.textViewName = itemView.findViewById(R.id.PostName);
        holder.textViewTime = itemView.findViewById(R.id.PostTime);
        holder.imageView = itemView.findViewById(R.id.PostImg);
        holder.editText = itemView.findViewById(R.id.PostEdText);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        StorageReference StorageRef = FirebaseStorage.getInstance().getReference();

        db.collection("Post")
                .whereEqualTo("postId",arrayListPost.get(position))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot doc: task.getResult())
                            {
                                String Name = doc.getString("Name");
                                String Text = doc.getString("Text");
                                String Time = doc.getString("Time");

                                holder.textViewName.setText(Name);
                                holder.editText.setText(Text);
                                holder.textViewTime.setText(Time);

                            }
                        }
                    }
                });

//        StorageRef.child("PostImg").listAll()
//                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
//                    @Override
//                    public void onSuccess(ListResult listResult) {
//                        for (StorageReference item : listResult.getItems()){
//                            item.getDownloadUrl()
//                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            ImageView imageView = new ImageView(mContext);//新增ImageView
//                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 400);
//                                            imageView.setLayoutParams(params);//調整ImageView大小
//                                            Glide.with(mContext)
//                                                    .load(uri)
//                                                    .into(imageView);
//
//                                            holder.linear.addView(imageView);
//                                        }
//                                    });
//                        }
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return arrayListPost.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName,textViewTime;
        ImageView imageView;
        ScrollView scrollView;
        LinearLayout linear;
        EditText editText;
//        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
