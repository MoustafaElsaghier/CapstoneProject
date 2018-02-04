package elsaghier.developer.com.capstoneproject.Activities;

import android.content.Context;
import android.content.DialogInterface;
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

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.email_TIL)
    TextInputLayout userEmail;

    @BindView(R.id.pass_TIL)
    TextInputLayout userPassword;

    @BindView(R.id.confirmPass_TIL)
    TextInputLayout userConfirmPassword;

    private boolean isValidEmail, isValidPassword;

    // fire base vars
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        userEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

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
        userConfirmPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String pass = userPassword.getEditText().getText().toString();
                if (matchedPasswords(editable.toString(), pass)) {
                    userConfirmPassword.setError(getString(R.string.empty_str));
                } else {
                    userConfirmPassword.setError(getString(R.string.mismatch_password));
                }
            }
        });

        // fire base
        mAuth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.signup_BTN)
    void signUp() {
        if (isValidEmail && isValidPassword) {
            String email, password;
            email = userEmail.getEditText().getText().toString();
            password = userPassword.getEditText().getText().toString();
            signUpNewUser(email, password);
            // send data to server
        } else {
            String email = userEmail.getEditText().getText().toString();
            if (TextUtils.isEmpty(email))
                userEmail.setError(getString(R.string.email_error));

            String pass = userPassword.getEditText().getText().toString();
            if (TextUtils.isEmpty(pass))
                userPassword.setError(getString(R.string.password_empty));

            String confirmPass = userConfirmPassword.getEditText().getText().toString();
            if (TextUtils.isEmpty(confirmPass))
                userConfirmPassword.setError(getString(R.string.pass_confirm));
            showErrorDialog(this, getString(R.string.error_field), getString(R.string.hint_tofix));
        }
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


    boolean matchedPasswords(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    void showErrorDialog(Context context, String dialogTittle, String dialogMessage) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(dialogTittle)
                .setMessage(dialogMessage)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    void signUpNewUser(String email, String password) {
        showProgressDialog(this, getString(R.string.signing_up), getString(R.string.save_data));
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpActivity.this, R.string.sign_up_success, Toast.LENGTH_SHORT).show();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, R.string.sign_up_failed,
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

}
