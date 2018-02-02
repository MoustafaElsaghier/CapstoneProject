package elsaghier.developer.com.capstoneproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.Window;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ELSaghier on 2/2/2018.
 */

public class ToDoDialog extends Dialog {

    @BindView(R.id.message_TIL)
    TextInputLayout message;

    public ToDoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_to_do_dialog);

    }

    @OnClick(R.id.save_btn)
    void addTo_RT_DB() {
        String m = message.getEditText().getText().toString();
        if (m.isEmpty())
            message.setError("Text can't be empty");
        else
            addToRealTimeDB(m);
        dismiss();
    }
    @OnClick(R.id.cancel_btn)
    void cancelDialog() {
        dismiss();
    }

    void addToRealTimeDB(String message) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue(message);
    }

}
