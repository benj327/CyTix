package com.cytix;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.cytix.api.ApiClientFactory.getEventApi;
import static com.cytix.api.ApiClientFactory.getTicketApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cytix.model.Event;
import com.cytix.model.Ticket;
import com.cytix.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyTicket extends AppCompatActivity {
    Long selectEventID;
    Event selectEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        User fromHome = (User)getIntent().getSerializableExtra("UserObj");

        ArrayList<Event> event_list = new ArrayList<>();
        ArrayList<String> event_name_list = new ArrayList<>();
        TextView eventID = findViewById(R.id.event_id);
        TextView eventDate = findViewById(R.id.event_date);
        TextView eventLocation = findViewById(R.id.event_location);
        TextView eventAttendees = findViewById(R.id.event_attendees);

        TextView ticketStatus = findViewById(R.id.ticket_status);
        Button buyTicketButton = findViewById(R.id.buyTicket_button);

        // data to populate the Spinner with
        getEventApi().getEvents().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(@NonNull Call<List<Event>> call, @NonNull Response<List<Event>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        for(int i = response.body().size() - 1; i >= 0; i--){
                            event_name_list.add(response.body().get(i).getEvent_name());
                            event_list.add(response.body().get(i));
                        }
                    }
                }
                Spinner myEvent = findViewById(R.id.eventSelector2);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(BuyTicket.this, android.R.layout.simple_spinner_item, event_name_list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myEvent.setAdapter(adapter);
                myEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        int index = adapterView.getSelectedItemPosition();
                        selectEventID = event_list.get(index).getEvent_id();
                        selectEvent = event_list.get(index);
                        eventID.setText("EventID: " + selectEventID);
                        eventDate.setText(event_list.get(index).getEvent_time());
                        eventLocation.setText(event_list.get(index).getEvent_location());
                        eventAttendees.setText("Number of Attendees:  " + event_list.get(index).getEvent_attendees().size());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        eventDate.setText("No Event");
                        eventLocation.setText("No Event");
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Event>> call, @NonNull Throwable t) {
                Toast.makeText(BuyTicket.this, "Fail Toast", Toast.LENGTH_SHORT).show();
            }
        });
        buyTicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ticket newTicket = new Ticket();
                newTicket.setEvent(selectEventID);
                newTicket.setOwner(fromHome.getId());

                getTicketApi().createTicket(newTicket).enqueue(new Callback<Ticket>() {
                    @Override
                    public void onResponse(@NonNull Call<Ticket> call, @NonNull Response<Ticket> response) {
                        if(response.isSuccessful()) {
                            ticketStatus.setText("Create ticket for event " + selectEventID);
                            getEventApi().addAttendee(selectEventID, fromHome.getId()).enqueue(new Callback<Event>() {
                                @Override
                                public void onResponse(Call<Event> call, Response<Event> response) {
                                    if(response.isSuccessful()) {
                                        ticketStatus.setText("Success buy ticket for event " + selectEventID);
                                    }
                                    else {
                                        ticketStatus.setText("Failed response addAttendee \n" + response.errorBody());
                                    }
                                }

                                @Override
                                public void onFailure(Call<Event> call, Throwable t) {
                                    ticketStatus.setText("Failed to update event \n" + t.getLocalizedMessage());
                                }
                            });
                        }
                        else {
                            ticketStatus.setText("Failed to buy ticket. Bad response \n" + response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Ticket> call, @NonNull Throwable t) {
                        ticketStatus.setText("Failed to buy ticket \n" + t.getLocalizedMessage());
                    }
                });
            }
        });
    }
}