package com.cytix;

import static com.cytix.api.ApiClientFactory.getTicketApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cytix.model.Ticket;
import com.cytix.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        User fromLogin = (User)getIntent().getSerializableExtra("UserObj");
        String email = fromLogin.getUserEmail();
        String name = fromLogin.getUserName();

        TextView homeName = findViewById(R.id.homeName);
        homeName.setText("Name: "+name);
        TextView homeEmail = findViewById(R.id.homeEmail);
        homeEmail.setText("Email: "+email);

        TextView homeTickets = findViewById(R.id.ticketNum);
        ArrayList<Ticket> listTicket = new ArrayList<>();
        getTicketsFroBackEnd(homeTickets, listTicket);
        Button ticketButton = findViewById(R.id.user_ticketButton);
        Button eventButton = findViewById(R.id.user_eventButton);
        Button chatButton = findViewById(R.id.user_chatButton);

        ticketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeTickets.setText("");
                String ticketList = "";
                getTicketsFroBackEnd(homeTickets, listTicket);
                for (int i = listTicket.size() - 1; i >= 0; i--) {
                    if(listTicket.get(i).getOwner() != null
                            && listTicket.get(i).getOwner().equals(fromLogin.getId())){
                        ticketList += "Tick: " + listTicket.get(i).getTicketId()
                        +" Owner: " + listTicket.get(i).getOwner()
                        +" Event: " + listTicket.get(i).getEvent() + "\n";
                    }
                }
                if(ticketList.isEmpty()) {
                    ticketList = "No ticket";
                }
                homeTickets.setText(ticketList);
            }
        });
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(view.getContext(), BuyTicket.class);
                page.putExtra("UserObj", fromLogin);
                startActivity(page);
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(view.getContext(), UserChat.class);
                Bundle bun = new Bundle();
                bun.putLong("ID", fromLogin.getId());
                bun.putString("Name", fromLogin.getUserName());
                page.putExtras(bun);
                startActivity(page);
            }
        });


    }

    private void getTicketsFroBackEnd(TextView homeTickets, ArrayList<Ticket> listTicket) {
        getTicketApi().getTickets().enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                if(response.isSuccessful()){
                    for(int i = response.body().size() - 1; i >= 0; i--){
                        listTicket.add(response.body().get(i));
                    }
                }
                else {
                    homeTickets.setText("Error code" + response.code() + "    MSG: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                homeTickets.setText("On Failure");
            }
        });
    }
}