package me.roybailey.akka;

import java.io.Serializable;

/**
 * Simple Command POJO
 */
public class Command implements Serializable {

    private final String data;

    public Command(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Command{" +
                "data='" + data + '\'' +
                '}';
    }
}
