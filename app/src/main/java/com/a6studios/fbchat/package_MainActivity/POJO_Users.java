package com.a6studios.fbchat.package_MainActivity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by HP on 3/20/2018.
 */

public class POJO_Users {
    private String name;
    private String reg_id;

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public POJO_Users(){}

    POJO_Users(FirebaseUser u)
    {
        if (u.getProviderId()=="phone")
        {
            name = u.getPhoneNumber();
        }
        else
            name = u.getDisplayName();
        reg_id = u.getUid();
    }
    POJO_Users(String s)
    {
        name = s;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
