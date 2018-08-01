package holdem;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private static int interval = 0;
    private static boolean heckle = false;

    private static Timer myTimer = new Timer();

    public static int getMyTimerPeriod() {
        return interval;
    }

    static void setMyTimer(int seconds) {
        if (seconds >= 10) {
            MyTimerTask.myTimer.scheduleAtFixedRate(new MyTimerTask(), 1000, seconds);
            interval = seconds;
        } else {
            interval = -1;
        }
    }

    public static boolean getHeckleEnabled() {
        return heckle;
    }

    public static void setHeckle(boolean set) {
        heckle = set;
    }

    @Override
    public void run() {
        System.out.println("Timer task started at:" + new Date());
        completeTask();
        System.out.println("Timer task finished at:" + new Date());
    }

    private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
