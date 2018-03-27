package com.a6studios.fbchat.package_OTPVerifiation;

import com.a6studios.fbchat.package_MainActivity.POJO_Users;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by HP on 3/20/2018.
 */

public class POJO_User {
    String name;
    String UID;

    public void setName(String name) {
        this.name = name;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    POJO_User(){}
    POJO_User(FirebaseUser u){
        if(u.getDisplayName()==null)
            name = u.getPhoneNumber().toString();
        else
        name = u.getDisplayName();
        UID = u.getUid();
    }

    public String getName() {
        return name;
    }
}
