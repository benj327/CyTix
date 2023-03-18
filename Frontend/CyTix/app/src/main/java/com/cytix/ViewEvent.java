package com.cytix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.cytix.api.ApiClientFactory.*;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cytix.model.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewEvent extends AppCompatActivity implements CheckerAdaptor.ItemClickListener{

    ArrayList<String> ticketList = new ArrayList<>();
    ArrayList<Long> checkedUserList = new ArrayList<>();
    CheckerAdaptor adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        Event fromChecker = (Event)getIntent().getSerializableExtra("EventObj");

        TextView name = findViewById(R.id.viewEvent_name);
        TextView date = findViewById(R.id.viewEvent_date);
        TextView location = findViewById(R.id.viewEvent_location);
        TextView status = findViewById(R.id.showError);

        name.setText("Event name: " + fromChecker.getEvent_name());
        date.setText("Date: " + fromChecker.getEvent_time());
        location.setText("Location: " + fromChecker.getEvent_location());

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CheckerAdaptor(ViewEvent.this, ticketList);
        adapter.setClickListener(ViewEvent.this);
        recyclerView.setAdapter(adapter);

        getTicketApi().getTickets().enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                if(response.isSuccessful()){
                    if(response != null){
                        for(int i = 0; i < response.body().size(); i++){
                            if(response.body().get(i).getChecked() && response.body().get(i).getEvent() == fromChecker.getEvent_id()){
                                checkedUserList.add(response.body().get(i).getOwner());
                            }
                        }
                        getUserApi().getUsers().enqueue(new Callback<List<User>>() {
                            @Override
                            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                boolean found = false;
                                if(response.isSuccessful()) {
                                    if (response != null) {
                                        for(int i = 0; i < response.body().size(); i++){
                                            found = false;
                                            for(int j = 0; j < checkedUserList.size(); j++){
                                                if(response.body().get(i).getId() == checkedUserList.get(j) && found == false){
                                                    ticketList.add(response.body().get(i).getUserName() + "\t" + response.body().get(i).getUserEmail());
                                                    found = true;
                                                }
                                            }
                                        }
                                        //adapter = new CheckerAdaptor(ViewEvent.this, ticketList);
                                        //adapter.setClickListener(ViewEvent.this);
                                        //recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }else {
                                        status.setText("Null in GetUser");
                                    }
                                }else {
                                    status.setText("Fail in GetUser\n" + response.errorBody());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<User>> call, Throwable t) {
                                status.setText("onFail in GetUser\n" + t.getLocalizedMessage());
                            }
                        });
                    }else {
                        status.setText("Null in GetTicket");
                    }
                }else {
                    status.setText("Fail in GetTicket\n" + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                status.setText("onFail in GetTicket\n" + t.getLocalizedMessage());
            }
        });



    }
    @Override
    public void onItemClick(View view, int position) {
    }
}