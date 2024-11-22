package com.example.cassowary;

public class Stopwatch {
    private long startTime;
    private boolean isRunning;
    private long elapsedTime;
    private String name;  // New field for the stopwatch name

    public Stopwatch(String name) {
        this.name = name;
        startTime = 0;
        isRunning = false;
        elapsedTime = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Stopwatch() {
        startTime = 0;
        isRunning = false;
        elapsedTime = 0;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            isRunning = true;
        }
    }

    public void pause() {
        if (isRunning) {
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }

    public void reset() {
        startTime = 0;
        elapsedTime = 0;
        isRunning = false;
    }

    public String getTime() {
        long totalElapsedTime = isRunning ? System.currentTimeMillis() - startTime : elapsedTime;
        long milliseconds = totalElapsedTime % 1000;
        long seconds = (totalElapsedTime / 1000) % 60;
        long minutes = (totalElapsedTime / (1000 * 60)) % 60;
        long hours = totalElapsedTime / (1000 * 60 * 60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public boolean isRunning() {
        return isRunning;
    }
}
