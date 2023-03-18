package com.cytix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.cytix.api.ApiClientFactory.getEventApi;
import static com.cytix.api.ApiClientFactory.getTicketApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cytix.model.Event;
import com.cytix.model.Ticket;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewVerifiedTicket extends AppCompatActivity implements CheckerAdaptor.ItemClickListener {

    CheckerAdaptor adapter;
    Long selectEventID;
    ArrayList<Long> eventIDs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified_tickets);
        Bundle fromChecker = getIntent().getExtras();
        Long userID = fromChecker.getLong("eventID");

        ArrayList<String> eventInfo = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rvTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // data to populate the RecyclerView with
        getEventApi().getEvents().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(@NonNull Call<List<Event>> call, @NonNull Response<List<Event>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        for(int i = response.body().size() - 1; i >= 0; i--){
                            eventInfo.add(response.body().get(i).getEvent_name()
                                    + "  L: " + response.body().get(i).getEvent_location() +
                                    "  Date: "+ response.body().get(i).getEvent_time());
                            eventIDs.add(response.body().get(i).getEvent_id());
                            adapter = new CheckerAdaptor(ViewVerifiedTicket.this, eventInfo);
                            adapter.setClickListener(ViewVerifiedTicket.this);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Event>> call, @NonNull Throwable t) {
                Toast.makeText(ViewVerifiedTicket.this, "Fail Toast", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        selectEventID = eventIDs.get(position);
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}