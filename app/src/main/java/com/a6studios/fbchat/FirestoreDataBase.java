package com.a6studios.fbchat;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.a6studios.fbchat.package_ChatBox.POJO_ChatRoom;
import com.a6studios.fbchat.package_ChatBox.POJO_Message;
import com.a6studios.fbchat.package_MainActivity.POJO_Users;
import com.a6studios.fbchat.package_OTPVerifiation.POJO_User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 3/20/2018.
 */

public class FirestoreDataBase {
    private static FirestoreDataBase mFirestoreDatabase;
    private static FirebaseFirestore db;
    private static FirebaseFirestoreSettings settings;
    private static FirebaseUser firebaseUser;
    private static String UserId ;
    private static final String rUsers = "reged_users";
    private static final String TAG = "MY FiREBASE ERROR";

    private static ListenerRegistration chatListernerRegisteration;
    private static Query chatQuery;
    private static String chatRoom;
    private static RecyclerView.Adapter RV_Adapter_chat;



    private FirestoreDataBase()
    {
        if(mFirestoreDatabase!=null)
            throw new RuntimeException("Use getFireStoreDataBase() method");
        else {
            db = FirebaseFirestore.getInstance();
            settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build();
            db.setFirestoreSettings(settings);
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                UserId = firebaseUser.getUid();
        }
    }

    public static synchronized FirestoreDataBase getFirestoreDatabase()
    {
        if(mFirestoreDatabase==null)
            mFirestoreDatabase = new FirestoreDataBase();
        return mFirestoreDatabase;
    }

    public static void setChatRoom(String toUserId)
    {
        String res;
        if(UserId.compareTo(toUserId)<0)
            res = UserId.concat(toUserId);
        else
            res = toUserId.concat(UserId);
        chatRoom = res;
    }

    public static void addChatroom(POJO_ChatRoom cr) {
        HashMap<String,Integer> m = new HashMap<String,Integer>();
        m.put("count",cr.getCount());
        m.put("notifyUser1",cr.getNotifyUser1());
        m.put("notifyUser2",cr.getNotifyUser2());
        db.collection("chats").document(chatRoom).set(m);
        db.collection("chats").document(chatRoom).collection("messages");
    }

    public static void setChatQuery()
    {
        chatQuery = db.collection("chats").document(chatRoom).collection("messages").orderBy("ts");
    }

    public static void setAdapter(RecyclerView.Adapter adapter){
        RV_Adapter_chat = adapter;
    }

    public void setChatListernerRegisteration()
    {
        final ArrayList<POJO_Message> al = new ArrayList<POJO_Message>();
        chatListernerRegisteration = chatQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "listen:error", e);
                    return;
                }

                for (DocumentChange dc : documentSnapshots.getDocumentChanges()) {
//                    if(dc.getOldIndex()==-1)
////                        RV_Adapter_chat.add((dc.getDocument().toObject(POJO_Message.class)));

                }
            }
        });
    }


    public static FirestoreDataBase getmFirestoreDatabase() {
        return mFirestoreDatabase;
    }

    public static FirebaseFirestore getDb() {
        return db;
    }

    public static FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }


    public static String getUserId() {
        return UserId;
    }

    public void addNewUser(POJO_User u)
    {
        HashMap<String,String> m = new HashMap<String, String>();
        m.put("name",u.getName());
        m.put("reg_id",u.getReg_id());
        db.collection(rUsers).document(getUserId()).set(m);
    }

    public static ServerTimestamp getTimeStamp()
    {
        return (ServerTimestamp)ServerValue.TIMESTAMP;
    }

    public static void cleanUp()
    {
        UserId = null;
        firebaseUser = null;
        db = null;
        settings=null;
        mFirestoreDatabase = null;
    }

}