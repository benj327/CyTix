package com.cytix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import com.cytix.model.User;

public class CheckerMessage extends AppCompatActivity{
    private Button check_send_message;
    private TextView viewMessage;
    private OkHttpClient client;

    private  final  class EchoWebSocketListener extends WebSocketListener {
        private  static  final int NORMAL_CLOSURE_STATUS = 1000;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send("Hello");
            webSocket.close(NORMAL_CLOSURE_STATUS, "Good bye");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            showOutput("Receiving: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            showOutput("Receiving bytes: " + bytes.hex());
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            showOutput("Closing: " + code + "/" + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            showOutput("Error: " + t.getMessage());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker_message);

        User fromChecker = (User)getIntent().getSerializableExtra("UserObj");
        String name = fromChecker.getUserName();

        viewMessage = findViewById(R.id.checker_view);
        viewMessage.setText("Name: "+name);

        EditText message_out = findViewById(R.id.check_message);
        check_send_message = findViewById(R.id.check_Send);
        client = new OkHttpClient();

        Request request = new Request.Builder().url("ws://coms-309-002.class.las.iastate.edu:8080/websockets/" + name).build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);

        check_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!message_out.getText().toString().isEmpty()) {
                    String message = name + " send: " + message_out.getText();
                    ws.send(message);
                    viewMessage.setText(viewMessage.getText().toString() + "\n\n" + message);
                    message_out.setText("");
                }
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        client.dispatcher().executorService().shutdown();
    }

    private void showOutput(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewMessage.setText(viewMessage.getText().toString()+"\n\n" + txt);
            }
        });
    }
}
