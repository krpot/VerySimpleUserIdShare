package com.mpark.basicusersession;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.mpark.basicusersession.data.MemorySessionStore;
import com.mpark.basicusersession.data.SessionStore;

public class LoginActivity extends AppCompatActivity {

    private final SessionStore sessionStore = MemorySessionStore.getInstance();

    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private ProgressBar loadingProgressBar;

    private String username() {
        return usernameEdit.getText().toString();
    }

    private String password() {
        return passwordEdit.getText().toString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        setupViews();
    }

    private void setupViews() {
        loadingProgressBar = findViewById(R.id.loading);
        usernameEdit = findViewById(R.id.username_edit);
        passwordEdit = findViewById(R.id.password_edit);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateForm();
            }
        };

        usernameEdit.addTextChangedListener(afterTextChangedListener);
        passwordEdit.addTextChangedListener(afterTextChangedListener);
        passwordEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                performLogin();
            }
            return false;
        });

        setupLoginButton();
    }

    private void setupLoginButton() {
        loginButton = findViewById(R.id.login_button);
        loginButton.setVisibility(View.GONE);
        loginButton.setOnClickListener(v -> {
            performLogin();
        });

        final String userId = sessionStore.getLoggedInUserId();
        if (userId == null || userId.isEmpty()) {
            loginButton.setVisibility(View.VISIBLE);
        }
    }

    private void performLogin() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        sessionStore.setLoggedInUserId(username());
        loadingProgressBar.setVisibility(View.GONE);

        finish();
    }

    private void validateForm() {
        loginButton.setEnabled(!missingFieldExists());
    }

    private boolean missingFieldExists() {
        return username().isEmpty() || password().isEmpty();
    }
}