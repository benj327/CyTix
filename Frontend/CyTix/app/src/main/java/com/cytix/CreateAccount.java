package com.cytix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cytix.api.SlimCallback;
import com.cytix.model.User;
import static com.cytix.api.ApiClientFactory.getUserApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        EditText userName = findViewById(R.id.createAccount_userName2);
        EditText password = findViewById(R.id.createAccount_userPassword2);
        EditText email = findViewById(R.id.createAccount_userEmail);

        Spinner accType = findViewById(R.id.accSelector);
        List<String> types = new ArrayList<>();
        types.add("User");
        types.add("Admin");
        types.add("Checker");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accType.setAdapter(adapter);

        Button createButton = findViewById(R.id.createAccount_createButton);

        TextView testbox = findViewById(R.id.testing_box);
        testbox.setMovementMethod(new ScrollingMovementMethod());
        testbox.setHeight(350);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = new User();
                newUser.setUserName(userName.getText().toString());
                newUser.setPassword(email.getText().toString());
                newUser.setUserEmail(password.getText().toString());
                newUser.setAccountType(String.valueOf(accType.getSelectedItem()));

                if(newUser.getUserName().isEmpty() || newUser.getPassword().isEmpty() || newUser.getUserEmail().isEmpty()) {
                    Toast.makeText(CreateAccount.this, "One of the fields is empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    CheckAccUnique(newUser, userName, password, email, testbox);
                }
            }
        });
    }

    private void CheckAccUnique(User newUser, EditText userName, EditText password, EditText email, TextView testbox) {
        getUserApi().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    boolean isUnique = true;
                    for (int i = (response.body() != null ? response.body().size() : 0) - 1; i >= 0; i--) {
                        if (response.body().get(i).getUserName().equals(newUser.getUserName()) || response.body().get(i).getUserEmail().equals(newUser.getUserEmail())) {
                            isUnique = false;
                            Toast.makeText(CreateAccount.this, "We already have that user please try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if (isUnique) {
                        //newUser.setId((long) (response.body() != null ? response.body().size() : 0));
                        CreateNewAcc(newUser, userName, password, email, testbox);
                    } else {
                        Toast.makeText(CreateAccount.this, "We already have that user please try again", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(CreateAccount.this, "Error code" + response.code() + " msg " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                //setContentView(R.layout.activity_error);
                CreateNewAcc(newUser, userName, password, email, testbox);
                Toast.makeText(CreateAccount.this, "On Fail",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CreateNewAcc(User newUser, EditText userName, EditText password, EditText email, TextView testbox) {
        getUserApi().createUser(newUser).enqueue(new SlimCallback<>(resp -> {
            userName.setText("");
            password.setText("");
            email.setText("");
            RegenerateUsers(testbox);
            startActivity(new Intent(CreateAccount.this, Login.class));
        }));
    }

    /**
     * Used to test the get request from the server
     * @param apiText1: test box to print users to
     */
    void RegenerateUsers(TextView apiText1) {
        getUserApi().getUsers().enqueue(new SlimCallback<>(users -> {
            apiText1.setText("");

            for (int i = users.size() - 1; i >= 0; i--) {
                apiText1.append(users.get(i).printable());
            }
        }));
    }
}