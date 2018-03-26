package com.a6studios.fbchat.package_ChatBox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a6studios.fbchat.R;

public class ChatBox extends AppCompatActivity {
    Button send;
    Button recv;
    EditText msg;
    RecyclerView rvMessages;
    Chat chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        send = findViewById(R.id.s);
        recv = findViewById(R.id.r);
        msg = findViewById(R.id.msg_et);
        rvMessages = findViewById(R.id.rvMessages);
        LinearLayoutManager m = new LinearLayoutManager(this);
        rvMessages.setLayoutManager(m);
        chatAdapter = new Chat(this);
        rvMessages.setAdapter(chatAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!msg.getText().toString().isEmpty() & !msgLength0(msg.getText().toString())) {
                    POJO_Message m = new POJO_Message();
                    m.setMessage(msg.getText().toString());
                    m.setSent(true);
                    msg.setText("");
                    chatAdapter.addItem(m);
                    rvMessages.smoothScrollToPosition(chatAdapter.getItemCount());
                }
            }
        });
        recv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!msg.getText().toString().isEmpty() & !msgLength0(msg.getText().toString())) {
                    POJO_Message m = new POJO_Message();
                    m.setMessage(msg.getText().toString());
                    m.setSent(false);
                    msg.setText("");
                    chatAdapter.addItem(m);
                    rvMessages.smoothScrollToPosition(chatAdapter.getItemCount());
                }
            }
        });
    }

    boolean msgLength0(String s) {
        String modifieds = s.replaceAll("^\\s+", "");
        s = modifieds.replaceAll("\\s+$", "");
        return s.length() == 0;
    }
}

