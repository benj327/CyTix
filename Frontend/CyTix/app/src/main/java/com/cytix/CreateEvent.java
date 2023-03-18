package com.cytix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cytix.api.SlimCallback;
import com.cytix.model.*;

import static com.cytix.api.ApiClientFactory.*;

import java.util.List;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        User fromLogin = (User)getIntent().getSerializableExtra("UserObj");
        Long hostID = fromLogin.getId();

        EditText name = findViewById(R.id.createEvent_eventName);
        EditText date = findViewById(R.id.createEvent_eventDate);
        EditText location = findViewById(R.id.createEvent_eventLocation);
        Button createEventButton = findViewById(R.id.createEvent_createButton);
        TextView checkerList = findViewById(R.id.createEvent_checkers);
        checkerList.setMovementMethod(new ScrollingMovementMethod());

        getUserApi().getUsers().enqueue(new SlimCallback<List<User>>(response -> {
            for (int i = 0; i <= response.size() - 1; i++) {
                if (response.get(i).getAccountType().equals("Checker")) {
                    checkerList.append(response.get(i).printable());
                    checkerList.append("\n");
                }
            }
        }));

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("") || !date.getText().toString().equals("") || !location.getText().toString().equals("")) {
                    Event newEvent = new Event(name.getText().toString(), date.getText().toString(), location.getText().toString());
                    newEvent.setEvent_host(hostID);
                    getEventApi().createEvent(newEvent).enqueue(new SlimCallback<Event>(response -> {
                        Intent viewEvent = new Intent(view.getContext(), ViewEvent.class);
                        viewEvent.putExtra("EventObj", newEvent);
                        startActivity(viewEvent);
                    }));
                }
                else {
                    Toast.makeText(CreateEvent.this, "One of the fields is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}