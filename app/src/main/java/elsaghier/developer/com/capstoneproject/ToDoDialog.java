package elsaghier.developer.com.capstoneproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import elsaghier.developer.com.capstoneproject.Models.ProgressDialogClass;

/**
 * Created by ELSaghier on 2/2/2018.
 */

public class ToDoDialog extends Dialog {

    @BindView(R.id.message_TIL)
    TextInputLayout message;

    Button save, cancel;

    public ToDoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_to_do_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.save_btn)
    void addTo_RT_DB() {
        ProgressDialogClass.showProgressDialog(getContext(), "Save data", "Sending data to server");
        String m = message.getEditText().getText().toString();
        if (m.isEmpty())
            message.setError("Text can't be empty");
        else {
            addToRealTimeDB(m);
            message.getEditText().setText("");
        }
        ProgressDialogClass.hideProgressDialog();
        dismiss();
    }

    @OnClick(R.id.cancel_btn)
    void cancelDialog() {
        dismiss();
        message.getEditText().setText("");
    }

    private void addToRealTimeDB(String message) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("messages");
        myRef.push().setValue(message);
    }

}
