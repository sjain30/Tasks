package com.sajal.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
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
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        firebaseDatabase= FirebaseDatabase.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strname = name.getText().toString();
                String stremail = email.getText().toString();
                String strpassword = password.getText().toString();
                User.addUser(stremail,strname,strpassword);
                User.addtoFirestore(stremail,strname,strpassword,getApplicationContext());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = firebaseDatabase.getReference("User").child(search.getText().toString());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if (dataSnapshot.exists()) {
                               StringBuffer buffer = new StringBuffer();
                               Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                               buffer.append("Email: " + map.get("Email") + "\n");
                               buffer.append("Name: " + map.get("Name") + "\n");
                               buffer.append("Password: " + map.get("Password") + "\n\n");
                               Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                           }
                           else
                               Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FoodActivity.class));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore firestore= FirebaseFirestore.getInstance();
                firestore.collection("User").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            StringBuffer buffer = new StringBuffer();
                            for (DocumentSnapshot d: list) {
                                User data = d.toObject(User.class);
                                buffer.append("Name"+data.Name+"\n");
                                buffer.append("Email"+data.Email+"\n");
                                buffer.append("Password"+data.Password+"\n\n");
//                                Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                            }
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(true);
                            builder.setTitle("Data");
                            builder.setMessage(buffer.toString());
                            builder.show();
                        }
                        else
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}

