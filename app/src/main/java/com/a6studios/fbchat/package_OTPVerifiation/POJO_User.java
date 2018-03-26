package com.a6studios.fbchat.package_OTPVerifiation;

import com.a6studios.fbchat.package_MainActivity.POJO_Users;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by HP on 3/20/2018.
 */

public class POJO_User {
    private String name;
    private String reg_id;
    public POJO_User(){}
    public POJO_User(FirebaseUser u){
        if(u.getDisplayName()==null)
            name = u.getPhoneNumber().toString();
        else
        name = u.getDisplayName();
        reg_id = u.getUid();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }
}
