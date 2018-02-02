package elsaghier.developer.com.capstoneproject.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import elsaghier.developer.com.capstoneproject.Models.Film;
import elsaghier.developer.com.capstoneproject.R;
import elsaghier.developer.com.capstoneproject.ToDoDialog;

public class ToDoActivity extends AppCompatActivity {

    ToDoDialog doDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Film f = new Film();
        f.setBackdropPath("fff00");
        f.setId(3);
        f.setGeneres("FDSF");
        f.setOverview("GAGGGG");
        myRef.push().setValue(f);
        doDialog = new ToDoDialog(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDialog.show();
            }
        });
    }

}
