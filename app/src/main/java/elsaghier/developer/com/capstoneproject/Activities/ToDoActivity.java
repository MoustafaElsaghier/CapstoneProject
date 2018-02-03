package elsaghier.developer.com.capstoneproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import elsaghier.developer.com.capstoneproject.R;
import elsaghier.developer.com.capstoneproject.ToDoDialog;

public class ToDoActivity extends AppCompatActivity {

    ToDoDialog doDialog;

    @BindView(R.id.todo_recycler)
    RecyclerView messagesRV;
    ArrayList<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        doDialog = new ToDoDialog(this);
        data = new ArrayList<>();
        ButterKnife.bind(this);
        messagesRV.setLayoutManager(new LinearLayoutManager(this));
//        messagesRV.setAdapter();

    }

    @OnClick(R.id.fab)
    void fabClick() {
        doDialog.show();
    }

    void readFromFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        ChildEventListener mChildEventListener;
        // Read from the database
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        myRef.addChildEventListener(mChildEventListener);
    }
}
