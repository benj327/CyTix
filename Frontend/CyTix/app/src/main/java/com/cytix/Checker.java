package com.cytix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.cytix.api.ApiClientFactory.*;
import static com.cytix.api.ApiClientFactory.getTicketApi;

import com.cytix.model.Event;
import com.cytix.model.Ticket;
import com.cytix.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Checker extends AppCompatActivity{

    Event selectEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        User fromLogin = (User)getIntent().getSerializableExtra("UserObj");
        Long checkerID = fromLogin.getId();
        String email = fromLogin.getUserEmail();
        String name = fromLogin.getUserName();

        TextView checkerName = findViewById(R.id.checkerName);
        checkerName.setText("Name: " + name);
        TextView checkerEmail = findViewById(R.id.checkerEmail);
        checkerEmail.setText("Email: " + email);

        ArrayList<Event> event_list = new ArrayList<>();
        ArrayList<String> event_name_list = new ArrayList<>();
        TextView eventDate = findViewById(R.id.result_date);
        TextView eventLocation = findViewById(R.id.result_location);
        TextView result = findViewById(R.id.check_result);

        Bundle fromChecker = new Bundle();


        getEventApi().getEvents().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(@NonNull Call<List<Event>> call, @NonNull Response<List<Event>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null) {
                        for(int i = response.body().size() - 1; i >= 0; i--){
                            if(response.body().get(i).getEvent_checkers().size() != 0) {
                                if (response.body().get(i).getEvent_checkers().get(0).getId().equals(checkerID)) {
                                    event_name_list.add(response.body().get(i).getEvent_name());
                                    event_list.add(response.body().get(i));
                                }
                            }
                        }
                    }
                }
                Spinner myEvent = findViewById(R.id.eventSelector);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Checker.this, android.R.layout.simple_spinner_item, event_name_list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myEvent.setAdapter(adapter);
                myEvent.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        int index = adapterView.getSelectedItemPosition();
                        selectEvent = event_list.get(index);
                        fromChecker.putInt("eventID", index);
                        eventDate.setText(event_list.get(index).getEvent_time());
                        eventLocation.setText(event_list.get(index).getEvent_location());
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
                result.setText("Failure to get event");
            }
        });
        EditText tick_ID = findViewById(R.id.ticket_Check);
        Button check_ticket_ID = findViewById(R.id.check_Ticket);
        Button view_check_tickets = findViewById(R.id.ticketsChecked);
        Button check_message = findViewById(R.id.check_Talk);

        check_ticket_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long tickID = Long.parseLong(String.valueOf(tick_ID.getText()));
                getTicketApi().getTickets().enqueue(new Callback<List<Ticket>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Ticket>> call, @NonNull Response<List<Ticket>> response) {
                        if(response.isSuccessful()){
                            if(response.body() != null) {
                                for(int i = response.body().size() - 1; i >= 0; i--){
                                    Ticket matchTicket = response.body().get(i);
                                    if(GetTicketInfoByID(matchTicket, tickID, event_list, result))
                                    {
                                        matchTicket.setChecked(true);
                                        getTicketApi().updateTicket(tickID, matchTicket).enqueue(new Callback<Ticket>() {
                                            @Override
                                            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                                                if(response.isSuccessful()) {
                                                    result.setText("Ticket " + tickID + " successfully checked");
                                                } else {
                                                    result.setText("Callback updateTicket Failure! \n" + response.errorBody());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Ticket> call, Throwable t) {
                                                result.setText("onFailure updateTicket! \n" + t.getLocalizedMessage());
                                            }
                                        });
                                    }
                                }
                            }else{
                                result.setText("Null Failure!");
                            }
                        }else{
                            result.setText("Callback Failure! \n" + response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Ticket>> call, @NonNull Throwable t) {
                        result.setText("Server Unreachable! \n" + t.getLocalizedMessage());
                    }
                });
            }
        });

        view_check_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectEvent != null) {
                    Intent viewChecked = new Intent(view.getContext(), ViewEvent.class);
                    viewChecked.putExtra("EventObj", selectEvent);
                    startActivity(viewChecked);
                }else {
                    result.setText("No Event Selected!");
                }
            }
        });

        check_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewMessage = new Intent(view.getContext(), CheckerMessage.class);
                viewMessage.putExtra("UserObj", fromLogin);
                startActivity(viewMessage);
            }
        });

    }

    private boolean GetTicketInfoByID(Ticket response, Long tickID, ArrayList<Event> the_event_list, TextView result) {
        boolean findInfo = false;
        if(response.getTicketId() != null
            && response.getTicketId().equals(tickID)){
            Long ownerId = response.getOwner();
            Long eventId = response.getEvent();
            for(int j = the_event_list.size() - 1; j >= 0; j--){
                if(the_event_list.get(j).getEvent_id().equals(eventId)){
                    ShowMatchUserInfo(ownerId, result);
                    findInfo = true;
                    break;
                }
            }
        }
        if(!findInfo){
            result.setText("No Event Exists!");
        }
        return findInfo;
    }

    private void ShowMatchUserInfo(Long ownerId, TextView result) {
        getUserApi().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                String display_user_result = "";
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        for (int i = response.body().size() - 1; i >= 0; i--) {
                            display_user_result = Checker.this.getString(response.body().get(i), display_user_result, ownerId, result);
                            if (display_user_result == "")
                                break;
                        }
                    }else{
                        result.setText("ShowMatch Null Failure!");
                    }
                }else{
                    result.setText("ShowMatch Callback Failure!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                result.setText("ShowMatch Server Unreachable!");
            }
        });
    }

    @Nullable
    private String getString(User response, String display_user_result, Long ownerId, TextView result) {
        if(response.getId().equals(ownerId)){
            display_user_result += "Name: " + response.getUserName()
                    + "  Email: " + response.getUserEmail();
            result.setText(display_user_result);
            return null;
        }else{
            result.setText("No Owner Exists!");
        }
        return display_user_result;
    }
}
