package com.a6studios.fbchat.package_MainActivity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by HP on 3/20/2018.
 */

public class POJO_Users {
    private String name;
    private String UID;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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
        UID = u.getUid();
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
