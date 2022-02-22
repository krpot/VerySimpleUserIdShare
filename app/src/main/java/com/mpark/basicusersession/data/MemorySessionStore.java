package com.mpark.basicusersession.data;

import androidx.annotation.Nullable;

public class MemorySessionStore implements SessionStore {

    public static MemorySessionStore getInstance() {
        return Impl.INSTANCE;
    }

    private static class Impl {
        static MemorySessionStore INSTANCE = new MemorySessionStore();
    }

    private String loggedInUserId = null;

    private MemorySessionStore() {
    }

    @Nullable
    @Override
    public String getLoggedInUserId() {
        return loggedInUserId;
    }

    @Override
    public void setLoggedInUserId(@Nullable String value) {
        loggedInUserId = value;
    }
}
