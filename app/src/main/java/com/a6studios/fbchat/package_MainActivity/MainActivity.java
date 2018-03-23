package com.a6studios.fbchat.package_MainActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import com.a6studios.fbchat.FirestoreDataBase;
import com.a6studios.fbchat.R;
import com.a6studios.fbchat.package_OTPVerifiation.OTPVerification;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView tv;
    RecyclerView rvUsers;
    RV_Adapter_UsersList mAdapter_usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        rvUsers = (RecyclerView)findViewById(R.id.rv_list_of_reged_users);
        LinearLayoutManager m = new LinearLayoutManager(this);
        rvUsers.setLayoutManager(m);
        final RV_Adapter_UsersList mAdapter = new RV_Adapter_UsersList(this);
        rvUsers.setAdapter(mAdapter);
        FirebaseFirestore db= FirebaseFirestore.getInstance();
        /*//Getting one single document

        CollectionReference users = FirebaseFirestore.getInstance().collection("reged_users");
        DocumentReference docRef = users.document("A6qCmCfOiihJeiFBgj4tJQ9r2Ow2");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                POJO_Users user = documentSnapshot.toObject(POJO_Users.class);
                Toast.makeText(getApplicationContext()," nam "+user.getName(),Toast.LENGTH_SHORT).show();
            }
        });*/
        final ArrayList<POJO_Users> al = new ArrayList<POJO_Users>();
        //Getting Multiple documents:
        db.collection("reged_users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                POJO_Users u = document.toObject(POJO_Users.class);
                                al.add(u);
                                mAdapter.addUser(u);
                                Toast.makeText(getApplicationContext(),"Name "+u.getName(),Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("ERROR", "Error getting documents: ", task.getException());
                        }
                    }
                });
        mAdapter.addListUsers(al);

    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            Intent loginStart = new Intent(this,OTPVerification.class);
            startActivity(loginStart);
            FirestoreDataBase.cleanUp();
            finish();
        }
    }

    public void onClick(View view) {
        FirebaseAuth.getInstance().signOut();
        onStart();
    }
}
