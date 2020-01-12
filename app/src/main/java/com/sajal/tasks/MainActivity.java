package com.sajal.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = findViewById(R.id.editText);
        final EditText email = findViewById(R.id.editText2);
        final EditText password = findViewById(R.id.editText3);
        final EditText search = findViewById(R.id.editText4);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        firebaseDatabase= FirebaseDatabase.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strname = name.getText().toString();
                String stremail = email.getText().toString();
                String strpassword = password.getText().toString();

                User.addUser(stremail,strname,strpassword);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = firebaseDatabase.getReference("User").child(search.getText().toString());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        StringBuffer buffer = new StringBuffer();
                            Map<String, Object> map = (Map<String, Object>)dataSnapshot.getValue();

                            buffer.append("Email: " + map.get("Email") + "\n");
                            buffer.append("Name: " + map.get("Name") + "\n");
                            buffer.append("Password: " + map.get("Password") + "\n\n");
                        Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}

