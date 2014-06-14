package de.tssn.m2dev.timeticker;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;


public class MainActivity extends Activity {
// Global Variables for the timer
    private Chronometer timer;
    private Character timerstatus; // ' ' = Stopped, 'X' = Started
    private Long TimeElapsed;

// Global Variables for goal counting
    private Integer GoalsHome;
    private Integer GoalsGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.Initialize();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            this.Initialize();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickStartStop(View v) {
        if (this.timerstatus == 'X') {
            this.TimeElapsed = SystemClock.elapsedRealtime() - this.timer.getBase();
            this.timer.stop();
            this.timerstatus = ' ';
        } else {
            this.timer.setBase(SystemClock.elapsedRealtime() - this.TimeElapsed);
            this.timer.start();
            this.timerstatus = 'X';
        }
    }

    public void onClickGoal(View v) {
        switch(v.getId()) {
            case R.id.bt_goalhome:
                this.GoalsHome++;
                break;
            case R.id.bt_goalguest:
                this.GoalsGuest++;
                break;
        }
        this.RefreshGoals();
    }

    public void Initialize() {
        this.timer = (Chronometer) findViewById(R.id.timer);

        // Reset the goals to 0
        this.GoalsHome = 0;
        this.GoalsGuest = 0;
        this.RefreshGoals();
        // Set the timer back to 00:00
        this.TimeElapsed = 0L;
        this.timer.stop();
        this.timerstatus = ' ';
    }

    public void RefreshGoals() {
        TextView tv = new TextView(this);
        tv = (TextView)findViewById(R.id.tv_GoalsHome);
        tv.setText(String.valueOf(this.GoalsHome));
        tv = (TextView)findViewById(R.id.tv_GoalsGuest);
        tv.setText(String.valueOf(this.GoalsGuest));
    }
}
