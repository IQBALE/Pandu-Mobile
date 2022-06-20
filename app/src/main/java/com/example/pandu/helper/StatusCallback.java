package com.example.pandu.helper;

public interface StatusCallback {
    void loading();

    void success();

    void error(String message);

    void empty();
}
