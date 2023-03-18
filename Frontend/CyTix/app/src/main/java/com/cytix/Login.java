package com.cytix;

import static com.cytix.api.ApiClientFactory.getUserApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cytix.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText userName = findViewById(R.id.login_userName);
        EditText password = findViewById(R.id.login_userPassword);

        Button loginButton = findViewById(R.id.login_loginButton);
        Button createButton = findViewById(R.id.login_createAccount);

        loginButton.setOnClickListener(view -> {
            getUserApi().getUsers().enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            for (int i = response.body().size() - 1; i >= 0; i--) {
                                if (response.body().get(i).getUserName().equals(userName.getText().toString())
                                        && response.body().get(i).getPassword().equals(password.getText().toString())) {
                                    Intent forHome = new Intent(view.getContext(), Home.class);
                                    if (response.body().get(i).getAccountType().equals("Checker")) {
                                        forHome = new Intent(view.getContext(), Checker.class);
                                    } else if (response.body().get(i).getAccountType().equals("Admin")) {
                                        forHome = new Intent(view.getContext(), CreateEvent.class);
                                    }
                                    BundleActivity(response.body().get(i), forHome);
                                }
                            }
                        } else {
                            Toast.makeText(Login.this, "null", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(Login.this, "respond Failed!"+response.code()+response.message(), Toast.LENGTH_SHORT).show();
                        //Intent forHome = new Intent(view.getContext(), Home.class);
                        Intent forHome = new Intent(view.getContext(), Checker.class);
//                        Intent forHome = new Intent(view.getContext(), CreateEvent.class);
                        User newUser = new User();
                        newUser.setUserName("John Doe");
                        newUser.setUserEmail("jDoe@generic.com");
                        newUser.setId((long)100);
                        BundleActivity(newUser, forHome);
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                    Toast.makeText(Login.this, "Login Failed, Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateAccount.class));
            }
        });
    }

    private void BundleActivity(User the_user, Intent forHome) {
        forHome.putExtra("UserObj", the_user);
        startActivity(forHome);
    }
}