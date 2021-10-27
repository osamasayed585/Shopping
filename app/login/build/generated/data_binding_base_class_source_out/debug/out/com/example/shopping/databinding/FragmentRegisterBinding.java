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

public final class FragmentRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputLayout TextInputLayoutEmail;

  @NonNull
  public final TextInputLayout TextInputLayoutName;

  @NonNull
  public final TextInputLayout TextInputLayoutPassword;

  @NonNull
  public final TextInputLayout TextInputLayoutPhone;

  @NonNull
  public final ConstraintLayout bottomCard;

  @NonNull
  public final TextView errorMessage;

  @NonNull
  public final ImageView logoImageView;

  @NonNull
  public final TextView registerLoginHere;

  @NonNull
  public final TextInputEditText registrationEmail;

  @NonNull
  public final TextInputEditText registrationPassword;

  @NonNull
  public final TextInputEditText registrationPhone;

  @NonNull
  public final ProgressBar registrationProgressBar;

  @NonNull
  public final Button registrationRegistration;

  @NonNull
  public final TextInputEditText registrationUsername;

  @NonNull
  public final TextView tvAleardy;

  @NonNull
  public final TextView tvLogo;

  private FragmentRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputLayout TextInputLayoutEmail, @NonNull TextInputLayout TextInputLayoutName,
      @NonNull TextInputLayout TextInputLayoutPassword,
      @NonNull TextInputLayout TextInputLayoutPhone, @NonNull ConstraintLayout bottomCard,
      @NonNull TextView errorMessage, @NonNull ImageView logoImageView,
      @NonNull TextView registerLoginHere, @NonNull TextInputEditText registrationEmail,
      @NonNull TextInputEditText registrationPassword, @NonNull TextInputEditText registrationPhone,
      @NonNull ProgressBar registrationProgressBar, @NonNull Button registrationRegistration,
      @NonNull TextInputEditText registrationUsername, @NonNull TextView tvAleardy,
      @NonNull TextView tvLogo) {
    this.rootView = rootView;
    this.TextInputLayoutEmail = TextInputLayoutEmail;
    this.TextInputLayoutName = TextInputLayoutName;
    this.TextInputLayoutPassword = TextInputLayoutPassword;
    this.TextInputLayoutPhone = TextInputLayoutPhone;
    this.bottomCard = bottomCard;
    this.errorMessage = errorMessage;
    this.logoImageView = logoImageView;
    this.registerLoginHere = registerLoginHere;
    this.registrationEmail = registrationEmail;
    this.registrationPassword = registrationPassword;
    this.registrationPhone = registrationPhone;
    this.registrationProgressBar = registrationProgressBar;
    this.registrationRegistration = registrationRegistration;
    this.registrationUsername = registrationUsername;
    this.tvAleardy = tvAleardy;
    this.tvLogo = tvLogo;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.TextInputLayout_email;
      TextInputLayout TextInputLayoutEmail = ViewBindings.findChildViewById(rootView, id);
      if (TextInputLayoutEmail == null) {
        break missingId;
      }

      id = R.id.TextInputLayout_name;
      TextInputLayout TextInputLayoutName = ViewBindings.findChildViewById(rootView, id);
      if (TextInputLayoutName == null) {
        break missingId;
      }

      id = R.id.TextInputLayout_password;
      TextInputLayout TextInputLayoutPassword = ViewBindings.findChildViewById(rootView, id);
      if (TextInputLayoutPassword == null) {
        break missingId;
      }

      id = R.id.TextInputLayout_phone;
      TextInputLayout TextInputLayoutPhone = ViewBindings.findChildViewById(rootView, id);
      if (TextInputLayoutPhone == null) {
        break missingId;
      }

      id = R.id.bottomCard;
      ConstraintLayout bottomCard = ViewBindings.findChildViewById(rootView, id);
      if (bottomCard == null) {
        break missingId;
      }

      id = R.id.errorMessage;
      TextView errorMessage = ViewBindings.findChildViewById(rootView, id);
      if (errorMessage == null) {
        break missingId;
      }

      id = R.id.logoImageView;
      ImageView logoImageView = ViewBindings.findChildViewById(rootView, id);
      if (logoImageView == null) {
        break missingId;
      }

      id = R.id.register_loginHere;
      TextView registerLoginHere = ViewBindings.findChildViewById(rootView, id);
      if (registerLoginHere == null) {
        break missingId;
      }

      id = R.id.registration_email;
      TextInputEditText registrationEmail = ViewBindings.findChildViewById(rootView, id);
      if (registrationEmail == null) {
        break missingId;
      }

      id = R.id.registration_password;
      TextInputEditText registrationPassword = ViewBindings.findChildViewById(rootView, id);
      if (registrationPassword == null) {
        break missingId;
      }

      id = R.id.registration_phone;
      TextInputEditText registrationPhone = ViewBindings.findChildViewById(rootView, id);
      if (registrationPhone == null) {
        break missingId;
      }

      id = R.id.registration_progressBar;
      ProgressBar registrationProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (registrationProgressBar == null) {
        break missingId;
      }

      id = R.id.registration_Registration;
      Button registrationRegistration = ViewBindings.findChildViewById(rootView, id);
      if (registrationRegistration == null) {
        break missingId;
      }

      id = R.id.registration_username;
      TextInputEditText registrationUsername = ViewBindings.findChildViewById(rootView, id);
      if (registrationUsername == null) {
        break missingId;
      }

      id = R.id.tvAleardy;
      TextView tvAleardy = ViewBindings.findChildViewById(rootView, id);
      if (tvAleardy == null) {
        break missingId;
      }

      id = R.id.tvLogo;
      TextView tvLogo = ViewBindings.findChildViewById(rootView, id);
      if (tvLogo == null) {
        break missingId;
      }

      return new FragmentRegisterBinding((ConstraintLayout) rootView, TextInputLayoutEmail,
          TextInputLayoutName, TextInputLayoutPassword, TextInputLayoutPhone, bottomCard,
          errorMessage, logoImageView, registerLoginHere, registrationEmail, registrationPassword,
          registrationPhone, registrationProgressBar, registrationRegistration,
          registrationUsername, tvAleardy, tvLogo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
