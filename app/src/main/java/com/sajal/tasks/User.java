package com.sajal.tasks;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    public String Email;
    public String Name;
    public String Password;
    private static FirebaseDatabase firebaseDatabase;

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

}
