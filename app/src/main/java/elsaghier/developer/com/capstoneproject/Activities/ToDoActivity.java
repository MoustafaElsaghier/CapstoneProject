package elsaghier.developer.com.capstoneproject.Activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import elsaghier.developer.com.capstoneproject.Models.ProgressDialogClass;
import elsaghier.developer.com.capstoneproject.Models.ToDoModel;
import elsaghier.developer.com.capstoneproject.R;

public class ToDoActivity extends AppCompatActivity {

    @BindView(R.id.todo_recycler)
    RecyclerView messagesRV;

    @BindView(R.id.to_do_TIL)
    TextInputLayout message;
    FirebaseDatabase database;
    DatabaseReference myRef;

    String retrievedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child(getString(R.string.msg));
        messagesRV.setLayoutManager(new LinearLayoutManager(this));
        messagesRV.setHasFixedSize(true);
        if (savedInstanceState != null)
        {
            retrievedMessage = savedInstanceState.getString(getString(R.string.to_do_message));
            message.getEditText().setText(retrievedMessage);
        }
    }

    @OnClick(R.id.save_btn)
    void addTo_RT_DB() {
        ProgressDialogClass.showProgressDialog(this, getString(R.string.save_data_), getString(R.string.save_data));
        String m = message.getEditText().getText().toString();
        if (m.isEmpty())
            message.setError(getString(R.string.text_empty));
        else {
            addToRealTimeDB(new ToDoModel(m));
            message.getEditText().setText(getString(R.string.empty_str));
        }
        ProgressDialogClass.hideProgressDialog();
    }

    private void addToRealTimeDB(ToDoModel message) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(getString(R.string.msg));
        myRef.push().setValue(message);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String s = message.getEditText().getText().toString();
        outState.putString(getString(R.string.to_do_message),s);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null)
            retrievedMessage = savedInstanceState.getString(getString(R.string.to_do_message));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<ToDoModel, ToDoViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ToDoModel, ToDoViewHolder>(ToDoModel.class,
                R.layout.to_do_item,
                ToDoViewHolder.class
                , myRef) {
            @Override
            protected void populateViewHolder(ToDoViewHolder viewHolder, ToDoModel model, int position) {
                viewHolder.setRowItem(model.getItem());
            }
        };
        messagesRV.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ToDoViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        void setRowItem(String item) {
            ((TextView) mView.findViewById(R.id.to_do_item_TV)).setText(item);
        }
    }
}
