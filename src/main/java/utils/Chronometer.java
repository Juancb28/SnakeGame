package utils;

import java.util.Timer;
import java.util.TimerTask;

public class Chronometer {

    private int seconds;
    private Timer timer;
    private int minutes, leftSeconds;

    public Chronometer() {
        timer = new Timer();
        setSeconds(0);
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getLeftSeconds() {
        return leftSeconds;
    }

    public void setLeftSeconds(int leftSeconds) {
        this.leftSeconds = leftSeconds;

    }

    public void initChronometer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;
                setMinutes(seconds / 60);
                setLeftSeconds(seconds % 60);
            }
        }, 1000, 1000);
    }

    public String getChronometer() {
        return String.format("TIME %02d:%02d", getMinutes(), getLeftSeconds());
    }

    public void resumeChronometer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;
                setMinutes(getMinutes());
                setLeftSeconds(getLeftSeconds());
                setMinutes(seconds / 60);
                setLeftSeconds(seconds % 60);
            }
        }, 1000, 1000);
    }

    public void stopChronometer() {
        timer.cancel();
    }

    public void resetChronometer() {
        stopChronometer();
        timer = new Timer();
        initChronometer();
    }

}
