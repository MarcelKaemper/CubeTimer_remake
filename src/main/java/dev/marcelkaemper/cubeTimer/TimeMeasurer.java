package dev.marcelkaemper.cubeTimer;

import java.util.concurrent.TimeUnit;

public class TimeMeasurer {
    private long startTime;
    private long endTime;

    public void startTimer() {
        startTime = System.nanoTime();
        CubeTimer.getGui().setStatusText("Started");
    }

    public double endTimer() {

        endTime = System.nanoTime();

        long elapsedTime = endTime-startTime;

        CubeTimer.getGui().setStatusText(String.valueOf((double) elapsedTime/1_000_000_000));

        return (double) elapsedTime/1_000_000_000;
    }

}
