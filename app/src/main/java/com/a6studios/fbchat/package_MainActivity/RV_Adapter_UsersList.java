package com.a6studios.fbchat.package_MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a6studios.fbchat.R;
import com.a6studios.fbchat.package_OTPVerifiation.POJO_User;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by HP on 3/22/2018.
 */

class RV_ViewHolder_UsersList extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView mUserName;
    public RV_ViewHolder_UsersList(View itemView) {
        super(itemView);
        mUserName = itemView.findViewById(R.id.rv_item_name);
        mUserName.setOnClickListener(this);

    }

    void bind(POJO_Users u){mUserName.setText(u.getName());}

    @Override
    public void onClick(View v) {

    }
}

public class RV_Adapter_UsersList extends RecyclerView.Adapter {

    ArrayList<POJO_Users>  mUsers;
    private Context mContext;

    public RV_Adapter_UsersList(Context context)
    {
        mContext = context;
        mUsers =new  ArrayList<POJO_Users>();
    }

    void addListUsers(ArrayList<POJO_Users> al)
    {
        for(int i=0;i<al.size();i++)
        {
            addUser(al.get(i));
        }
    }

    void addUser(POJO_Users m)
    {
        mUsers.add(m);
        notifyItemInserted(mUsers.size()-1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v;
        v = inflater.inflate(R.layout.item_reged_user,parent,false);
        return new RV_ViewHolder_UsersList(v);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RV_ViewHolder_UsersList mholder = (RV_ViewHolder_UsersList) holder;
        mholder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
