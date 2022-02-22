package com.mpark.basicusersession;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mpark.basicusersession.data.MemorySessionStore;
import com.mpark.basicusersession.data.SessionStore;

public class MainActivity extends AppCompatActivity {

    private final SessionStore sessionStore = MemorySessionStore.getInstance();

    private TextView userIdText;
    private Button loginButton, logoutButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupLoggedInUser();
    }

    private void setupLoggedInUser() {
        String loggedInUserId = sessionStore.getLoggedInUserId();
        boolean isUserLoggedIn = loggedInUserId != null && !loggedInUserId.isEmpty();
        String sessionText = isUserLoggedIn ? loggedInUserId : getString(R.string.non_login_status_text);
        userIdText.setText(sessionText);

        loginButton.setVisibility(isUserLoggedIn ? View.GONE : View.VISIBLE);
        logoutButton.setVisibility(isUserLoggedIn ? View.VISIBLE : View.GONE);
    }

    private void setupViews() {
        userIdText = findViewById(R.id.user_id_text);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> {
            goToLoginActivity();
        });

        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            sessionStore.setLoggedInUserId(null);
            setupLoggedInUser();
        });

        nextButton = findViewById(R.id.go_to_next_button);
        nextButton.setOnClickListener(v -> {
            goToSecondActivity();
        });
    }

    private void goToLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToSecondActivity() {
        final Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}