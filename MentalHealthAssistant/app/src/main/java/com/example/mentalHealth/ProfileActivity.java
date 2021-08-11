package com.example.mentalHealth;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import io.kommunicate.KmConversationBuilder;
import io.kommunicate.Kommunicate;
import io.kommunicate.callbacks.KmCallback;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String APP_ID = "27bc5bf0f6c9ee33211a262c419b8c126";

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private ImageView chatbot;
    private ImageView relaxationStrategies;
    private ImageView todo;
    private Button buttonLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Kommunicate.init(getApplicationContext(), APP_ID);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        buttonLogout = findViewById(R.id.buttonLogout);
        chatbot = findViewById(R.id.chatbot);
        relaxationStrategies = findViewById(R.id.relaxtionStrategies);
        todo = findViewById(R.id.todo);

        //displaying logged in user name
        textViewUserEmail.setText("Welcome " + user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        chatbot.setOnClickListener(this);
        relaxationStrategies.setOnClickListener(this);
        todo.setOnClickListener(this);

        Kommunicate.init(this, "27bc5bf0f6c9ee33211a262c419b8c126");
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if (view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(view == chatbot) {

            List<String> botIds = new ArrayList<>();
            botIds.add("mental-health-assistant-l351l"); // Add BOT_ID(s) to this list. Go to Manage Bots(https://dashboard.kommunicate.io/bots/manage-bots) -> Copy botID

            new KmConversationBuilder(this)
                    .setBotIds(botIds)
                    .setSingleConversation(false)
                    .launchConversation(new KmCallback() {
                        @Override
                        public void onSuccess(Object message) {
                            Log.d("Conversation", "Success : " + message);
                        }

                        @Override
                        public void onFailure(Object error) {
                            Log.d("Conversation", "Failure : " + error);
                        }
                    });
        }
        if(view == relaxationStrategies) {
            startActivity(new Intent(this, RelaxationActivity.class));
        }
        if(view == todo) {
            startActivity(new Intent(this, Maps.class));
        }
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        /*new KmChatBuilder(this).launchChat(new KmCallback() {
            @Override
            public void onSuccess(Object message) {
                Utils.printLog(ProfileActivity.this, "ChatTest", "Success : " + message);
            }

            @Override
            public void onFailure(Object error) {
                Utils.printLog(ProfileActivity.this, "ChatTest", "Failure : " + error);
            }
        });*/
    }


}