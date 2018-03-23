package com.a6studios.fbchat.package_ChatBox;

import com.google.firebase.firestore.ServerTimestamp;

/**
 * Created by HP on 3/20/2018.
 */

public class POJO_Message {
    ServerTimestamp ts;
    String from_uid;
    String to_uid;
    String message;
    POJO_Message(){}
}
