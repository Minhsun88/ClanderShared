package com.example.gp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.gp.databinding.ActivityGroupBinding;
import com.example.gp.databinding.ActivityIndexBinding;

public class Group extends AppCompatActivity {
    private ActivityGroupBinding B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = ActivityGroupBinding.inflate(getLayoutInflater());
        setContentView(B.getRoot());

        B.groupChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        B.groupSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}