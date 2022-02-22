package com.mpark.basicusersession.data;

import androidx.annotation.Nullable;

public interface SessionStore {
    @Nullable String getLoggedInUserId();
    void setLoggedInUserId(@Nullable String value);
}
