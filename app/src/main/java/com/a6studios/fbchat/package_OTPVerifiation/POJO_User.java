package com.a6studios.fbchat.package_OTPVerifiation;

import com.a6studios.fbchat.package_MainActivity.POJO_Users;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by HP on 3/20/2018.
 */

public class POJO_User {
    String name;
    POJO_User(){}
    POJO_User(FirebaseUser u){
        if(u.getDisplayName()==null)
            name = u.getPhoneNumber().toString();
        else
        name = u.getDisplayName();};

    public String getName() {
        return name;
    }
}
