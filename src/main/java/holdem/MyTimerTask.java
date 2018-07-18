package holdem;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static java.sql.Types.NULL;

public class MyTimerTask extends  TimerTask {

    private static int interval = 0;

    private static Timer myTimer = new Timer();

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

    public static int getMyTimerPeriod() {
        return interval;
    }

    public static void setMyTimer(int seconds) {
        if(seconds >10){
            MyTimerTask.myTimer.scheduleAtFixedRate(new MyTimerTask(), 1000, seconds);
            interval = seconds;
        }
        else{
            interval = -1;
        }
    }
}
