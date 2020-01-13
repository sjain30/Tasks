package com.sajal.tasks;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class User {
    public String Email;
    public String Name;
    public String Password;
    private static FirebaseDatabase firebaseDatabase;
    private static FirebaseFirestore firestore;

    public User() {
        this.Email = null;
        this.Name =null;
        this.Password =null;
    }

    public User(String email, String name, String password) {

        this.Email = email;
        this.Name =name;
        this.Password =password;
    }

    public static boolean addUser(String email, String name, String password)
    {
        User user = new User(email,name,password);
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("User");
        myRef.child(name).setValue(user);
        return true;
    }

    public static boolean addtoFirestore(String email, String name, String password, final Context context){

        User user = new User(email,name,password);
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("User").document(name).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        /*firestore.collection("User").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(context, "Document ID"+documentReference.getId(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });  */
        return true;
    }

}
