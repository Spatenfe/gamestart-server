package de.gamestart.java.data;

import com.google.gson.Gson;

public abstract class Data {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
