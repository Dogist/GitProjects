package htlpinkafeld.at.minesweeper.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Chronometer;

/**
 * Created by User on 15.05.2016.
 */
public class MyChronometer extends Chronometer {

    private boolean isRunning = false;

    public MyChronometer(Context context) {
        super(context);
    }

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyChronometer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void start() {
        super.start();
        isRunning = true;
    }

    @Override
    public void stop() {
        super.stop();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
