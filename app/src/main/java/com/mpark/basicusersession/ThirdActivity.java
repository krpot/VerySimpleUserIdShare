package com.mpark.basicusersession;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mpark.basicusersession.data.MemorySessionStore;
import com.mpark.basicusersession.data.SessionStore;

public class ThirdActivity extends AppCompatActivity {

    private final SessionStore sessionStore = MemorySessionStore.getInstance();

    private TextView userIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

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
        String userId = isUserLoggedIn ? loggedInUserId : getString(R.string.non_login_status_text);
        userIdText.setText(userId);
    }

    private void setupViews() {
        userIdText = findViewById(R.id.user_id_text);

        findViewById(R.id.close_button).setOnClickListener(v -> finish());
    }
}