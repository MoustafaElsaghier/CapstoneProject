package elsaghier.developer.com.capstoneproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.Window;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ELSaghier on 2/2/2018.
 */

public class ToDoDialog extends Dialog {

    @BindView(R.id.message_TIL)
    TextInputLayout message;
    private Context c;
    private boolean isDialogOpened;

    public ToDoDialog(@NonNull Context context) {
        super(context);
        c = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_to_do_dialog);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.cancel_btn)
    void cancelDialog() {
        dismiss();
        message.getEditText().setText(c.getString(R.string.empty_str));
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
