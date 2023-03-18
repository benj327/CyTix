package com.cytix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class UserChat extends AppCompatActivity {

    Button b1, b2;
    EditText e2;
    TextView t1;

    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat);

        Bundle fromLogin = getIntent().getExtras();
        b1 = (Button) findViewById(R.id.userchat_connectButton);
        b2 = (Button) findViewById(R.id.userchat_sendMessage);

        e2 = (EditText) findViewById(R.id.userchat_messageText);
        t1 = (TextView) findViewById(R.id.userchat_textview);
        t1.setMovementMethod(new ScrollingMovementMethod());


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setEnabled(false);
                Draft[] drafts = {
                        new Draft_6455()
                };

                /**
                 * If running this on an android device, make sure it is on the same network as your
                 * computer, and change the ip address to that of your computer.
                 * If running on the emulator, you can use localhost.
                 */
                String w = "ws://coms-309-002.class.las.iastate.edu:8080/websocket/" + fromLogin.getString("Name");

                try {
                    Log.d("Socket:", "Trying socket");
                    cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                        @Override
                        public void onMessage(String message) {
                            Log.d("", "run() returned: " + message);
                            String s = t1.getText().toString();
                            t1.setText(s + "\nServer:" + message);
                        }

                        @Override
                        public void onOpen(ServerHandshake handshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.d("CLOSE", "onClose() returned: " + reason);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Exception:", e.toString());
                        }
                    };
                } catch (URISyntaxException e) {
                    Log.d("Exception:", e.getMessage().toString());
                    e.printStackTrace();
                }
                cc.connect();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(e2.getText().toString());
                    e2.setText("");
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });
    }

}