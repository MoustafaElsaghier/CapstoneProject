package elsaghier.developer.com.capstoneproject.Activities;

import android.os.Bundle;
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
import elsaghier.developer.com.capstoneproject.Models.ToDoModel;
import elsaghier.developer.com.capstoneproject.R;
import elsaghier.developer.com.capstoneproject.ToDoDialog;

public class ToDoActivity extends AppCompatActivity {

    ToDoDialog doDialog;

    @BindView(R.id.todo_recycler)
    RecyclerView messagesRV;

    FirebaseDatabase database;
    DatabaseReference myRef;
    //Todo:: configurate dialog state
    boolean isDialogOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        doDialog = new ToDoDialog(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child(getString(R.string.msg));
        messagesRV.setLayoutManager(new LinearLayoutManager(this));
        messagesRV.setHasFixedSize(true);
    }

    @OnClick(R.id.fab)
    void fabClick() {
        doDialog.show();
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
