package com.a6studios.fbchat.package_ChatBox;

import com.a6studios.fbchat.FirestoreDataBase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import static com.a6studios.fbchat.FirestoreDataBase.*;

/**
 * Created by HP on 3/20/2018.
 */

public class POJO_Message {
    private  ServerTimestamp ts;
    private String from_uid;
    private String to_uid;
    private String message;
    private boolean sent;
    POJO_Message(){}

    public ServerTimestamp getTs() {
        return ts;
    }

    public void setTs(ServerTimestamp ts) {
        this.ts = ts;
    }

    public String getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(String from_uid) {
        this.from_uid = from_uid;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        String modifieds = message.replaceAll("^\\s+","");
        this.message = modifieds.replaceAll("\\s+$","");
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public POJO_Message(String m,String from_uid,String to_uid)
    {
        setMessage(m);
        setFrom_uid(from_uid);
        setTo_uid(to_uid);
        setTs(FirestoreDataBase.getTimeStamp());
        if(getFrom_uid().equals(FirestoreDataBase.getUserId()))
        {
            setSent(true);
        }
        else
        {
            setSent(false);
        }
    }
}
