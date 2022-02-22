package com.mpark.basicusersession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mpark.basicusersession.data.MemorySessionStore;
import com.mpark.basicusersession.data.SessionStore;

public class SecondActivity extends AppCompatActivity {

    private final SessionStore sessionStore = MemorySessionStore.getInstance();

    private TextView userIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindLoggedInUser();
    }

    private void bindLoggedInUser() {
        String loggedInUserId = sessionStore.getLoggedInUserId();
        boolean isUserLoggedIn = loggedInUserId != null && !loggedInUserId.isEmpty();
        String userId = isUserLoggedIn ? loggedInUserId : getString(R.string.non_login_status_text);
        userIdText.setText(userId);
    }

    private void setupViews() {
        userIdText = findViewById(R.id.user_id_text);

        findViewById(R.id.go_to_next_button).setOnClickListener(v -> {
            goToThirdActivity();
        });
    }

    private void goToThirdActivity() {
        final Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
        finish();
    }
}