package com.a6studios.fbchat.package_MainActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
    FirestoreDataBase fdb;
    RV_Adapter_UsersList mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        rvUsers = (RecyclerView)findViewById(R.id.rv_list_of_reged_users);
        LinearLayoutManager m = new LinearLayoutManager(this);
        rvUsers.setLayoutManager(m);
        mAdapter = new RV_Adapter_UsersList(this);
        rvUsers.setAdapter(mAdapter);
        fdb = FirestoreDataBase.getFirestoreDatabase();
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
        //Getting Multiple documents:

        fdb.setmQuery(fdb.getDb().collection("reged_users"));
        fdb.setmListenerRegistration(mAdapter);

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
        else
        {
            fdb = FirestoreDataBase.getFirestoreDatabase();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fdb.unregisterListnerRegistertion();
        FirestoreDataBase.cleanUp();
    }

    public void onClick(View view) {
        FirebaseAuth.getInstance().signOut();
        onStart();
    }
}
