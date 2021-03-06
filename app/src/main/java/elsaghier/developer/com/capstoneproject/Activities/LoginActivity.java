package elsaghier.developer.com.capstoneproject.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import elsaghier.developer.com.capstoneproject.R;

import static elsaghier.developer.com.capstoneproject.Models.ProgressDialogClass.hideProgressDialog;
import static elsaghier.developer.com.capstoneproject.Models.ProgressDialogClass.showProgressDialog;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.TIL_name)
    TextInputLayout userEmail;

    @BindView(R.id.TIL_pass)
    TextInputLayout userPassword;

    boolean isValidEmail, isValidPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        userEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (userNameValidation(editable.toString())) {
                    userEmail.setError("");
                } else {
                    if (editable.toString().length() == 0)
                        userEmail.setError(getString(R.string.email_error));
                    else
                        userEmail.setError(getString(R.string.email_inValid));
                }
            }
        });
        userPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (passwordValidation(editable.toString())) {
                    userPassword.setError("");
                } else {
                    if (editable.toString().length() == 0)
                        userPassword.setError(getString(R.string.password_empty));
                    else
                        userPassword.setError(getString(R.string.password_rule));
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @OnClick(R.id.signUp_TV)
    void setSingUpTV() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.BTN_login)
    void login() {
        showProgressDialog(this, getString(R.string.auth_process), getString(R.string.wait_str));
        if (isValidEmail && isValidPassword) {
            // handle login request
            String email = userEmail.getEditText().getText().toString();
            String password = userPassword.getEditText().getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            hideProgressDialog();
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            hideProgressDialog();
            showErrorDialog();
            emptyFields();
        }
    }

    void showErrorDialog() {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(LoginActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(LoginActivity.this);
        }
        builder.setTitle(R.string.login_failed)
                .setMessage(R.string.invalid_login_item)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    boolean userNameValidation(String email) {
        isValidEmail = (!TextUtils.isEmpty(email) &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return isValidEmail;
    }

    boolean passwordValidation(String password) {
        isValidPassword = (!TextUtils.isEmpty(password) && (password.length() >= 6));
        return isValidPassword;
    }

    void emptyFields() {
        userEmail.getEditText().setText(R.string.empty_str);
        userPassword.getEditText().setText(R.string.empty_str);
    }

}
