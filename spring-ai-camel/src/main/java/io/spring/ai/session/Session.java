package io.spring.ai.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Session implements Serializable {

    private  final String id;
    private final long timestamp;
    private final Request request;

    private long lastTouchedTimestamp;

    private String input;
    private final List<String> thinkings = new ArrayList<>();

    private String output;

    public Session(Request request) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
        this.request = request;
        this.lastTouchedTimestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public long getCreatedTimestamp() {
        return timestamp;
    }

    public Request getRequest() {
        return request;
    }

    public long getLastTouchedTimestamp() {
        return lastTouchedTimestamp;
    }

    public Session touch() {
        this.lastTouchedTimestamp = System.currentTimeMillis();
        return this;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void addThinking(String thinking) {
        this.thinkings.add(thinking);
    }

    public List<String> getThinkings() {
        return thinkings;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
