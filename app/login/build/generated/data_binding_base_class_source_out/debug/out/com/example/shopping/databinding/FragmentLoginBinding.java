// Generated by view binder compiler. Do not edit!
package com.example.shopping.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.shopping.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout bottomCard;

  @NonNull
  public final Button btnLogin;

  @NonNull
  public final TextView doNot;

  @NonNull
  public final TextInputLayout emailTextInputLayout;

  @NonNull
  public final TextView errorMessage;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final TextView loginCancel;

  @NonNull
  public final TextInputEditText loginEmail;

  @NonNull
  public final TextInputEditText loginPassword;

  @NonNull
  public final ProgressBar loginProgressBar;

  @NonNull
  public final TextView loginRegistration;

  @NonNull
  public final TextView loginWith;

  @NonNull
  public final ImageView logoImageView;

  @NonNull
  public final TextInputLayout passwordTextInputLayout;

  @NonNull
  public final TextView tvLogo;

  private FragmentLoginBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout bottomCard, @NonNull Button btnLogin, @NonNull TextView doNot,
      @NonNull TextInputLayout emailTextInputLayout, @NonNull TextView errorMessage,
      @NonNull ImageView imageView3, @NonNull ImageView imageView4, @NonNull ImageView imageView5,
      @NonNull TextView loginCancel, @NonNull TextInputEditText loginEmail,
      @NonNull TextInputEditText loginPassword, @NonNull ProgressBar loginProgressBar,
      @NonNull TextView loginRegistration, @NonNull TextView loginWith,
      @NonNull ImageView logoImageView, @NonNull TextInputLayout passwordTextInputLayout,
      @NonNull TextView tvLogo) {
    this.rootView = rootView;
    this.bottomCard = bottomCard;
    this.btnLogin = btnLogin;
    this.doNot = doNot;
    this.emailTextInputLayout = emailTextInputLayout;
    this.errorMessage = errorMessage;
    this.imageView3 = imageView3;
    this.imageView4 = imageView4;
    this.imageView5 = imageView5;
    this.loginCancel = loginCancel;
    this.loginEmail = loginEmail;
    this.loginPassword = loginPassword;
    this.loginProgressBar = loginProgressBar;
    this.loginRegistration = loginRegistration;
    this.loginWith = loginWith;
    this.logoImageView = logoImageView;
    this.passwordTextInputLayout = passwordTextInputLayout;
    this.tvLogo = tvLogo;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomCard;
      ConstraintLayout bottomCard = ViewBindings.findChildViewById(rootView, id);
      if (bottomCard == null) {
        break missingId;
      }

      id = R.id.btnLogin;
      Button btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.doNot;
      TextView doNot = ViewBindings.findChildViewById(rootView, id);
      if (doNot == null) {
        break missingId;
      }

      id = R.id.emailTextInputLayout;
      TextInputLayout emailTextInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (emailTextInputLayout == null) {
        break missingId;
      }

      id = R.id.errorMessage;
      TextView errorMessage = ViewBindings.findChildViewById(rootView, id);
      if (errorMessage == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.login_cancel;
      TextView loginCancel = ViewBindings.findChildViewById(rootView, id);
      if (loginCancel == null) {
        break missingId;
      }

      id = R.id.login_email;
      TextInputEditText loginEmail = ViewBindings.findChildViewById(rootView, id);
      if (loginEmail == null) {
        break missingId;
      }

      id = R.id.login_Password;
      TextInputEditText loginPassword = ViewBindings.findChildViewById(rootView, id);
      if (loginPassword == null) {
        break missingId;
      }

      id = R.id.login_progressBar;
      ProgressBar loginProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (loginProgressBar == null) {
        break missingId;
      }

      id = R.id.login_registration;
      TextView loginRegistration = ViewBindings.findChildViewById(rootView, id);
      if (loginRegistration == null) {
        break missingId;
      }

      id = R.id.loginWith;
      TextView loginWith = ViewBindings.findChildViewById(rootView, id);
      if (loginWith == null) {
        break missingId;
      }

      id = R.id.logoImageView;
      ImageView logoImageView = ViewBindings.findChildViewById(rootView, id);
      if (logoImageView == null) {
        break missingId;
      }

      id = R.id.passwordTextInputLayout;
      TextInputLayout passwordTextInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (passwordTextInputLayout == null) {
        break missingId;
      }

      id = R.id.tvLogo;
      TextView tvLogo = ViewBindings.findChildViewById(rootView, id);
      if (tvLogo == null) {
        break missingId;
      }

      return new FragmentLoginBinding((ConstraintLayout) rootView, bottomCard, btnLogin, doNot,
          emailTextInputLayout, errorMessage, imageView3, imageView4, imageView5, loginCancel,
          loginEmail, loginPassword, loginProgressBar, loginRegistration, loginWith, logoImageView,
          passwordTextInputLayout, tvLogo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
