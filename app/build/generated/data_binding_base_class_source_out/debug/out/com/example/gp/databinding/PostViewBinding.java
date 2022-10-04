// Generated by view binder compiler. Do not edit!
package com.example.gp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.gp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PostViewBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText PostEdText;

  @NonNull
  public final ImageView PostImg;

  @NonNull
  public final TextView PostName;

  @NonNull
  public final TextView PostTime;

  @NonNull
  public final LinearLayout PostViewLinear;

  @NonNull
  public final HorizontalScrollView PostViewScrollView;

  private PostViewBinding(@NonNull ConstraintLayout rootView, @NonNull EditText PostEdText,
      @NonNull ImageView PostImg, @NonNull TextView PostName, @NonNull TextView PostTime,
      @NonNull LinearLayout PostViewLinear, @NonNull HorizontalScrollView PostViewScrollView) {
    this.rootView = rootView;
    this.PostEdText = PostEdText;
    this.PostImg = PostImg;
    this.PostName = PostName;
    this.PostTime = PostTime;
    this.PostViewLinear = PostViewLinear;
    this.PostViewScrollView = PostViewScrollView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PostViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PostViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.post_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PostViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.PostEdText;
      EditText PostEdText = ViewBindings.findChildViewById(rootView, id);
      if (PostEdText == null) {
        break missingId;
      }

      id = R.id.PostImg;
      ImageView PostImg = ViewBindings.findChildViewById(rootView, id);
      if (PostImg == null) {
        break missingId;
      }

      id = R.id.PostName;
      TextView PostName = ViewBindings.findChildViewById(rootView, id);
      if (PostName == null) {
        break missingId;
      }

      id = R.id.PostTime;
      TextView PostTime = ViewBindings.findChildViewById(rootView, id);
      if (PostTime == null) {
        break missingId;
      }

      id = R.id.PostViewLinear;
      LinearLayout PostViewLinear = ViewBindings.findChildViewById(rootView, id);
      if (PostViewLinear == null) {
        break missingId;
      }

      id = R.id.PostViewScrollView;
      HorizontalScrollView PostViewScrollView = ViewBindings.findChildViewById(rootView, id);
      if (PostViewScrollView == null) {
        break missingId;
      }

      return new PostViewBinding((ConstraintLayout) rootView, PostEdText, PostImg, PostName,
          PostTime, PostViewLinear, PostViewScrollView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
