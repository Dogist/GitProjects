package at.htlpinkafeld.projectmanager.ui;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.service.ActivitySaver;
import at.htlpinkafeld.projectmanager.service.ProjectManagerService;
import at.htlpinkafeld.projectmanager.ui.util.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Members", "Projects", "Activities"};
    int NumTabs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            setLoggingProperties("logging.properties");

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("info MainActivity#onCreate");
        log.warning("warn MainActivity#onCreate");
        log.fine("fine MainActivity#onCreate");
        log.finest("finest MainActivity#onCreate");

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, NumTabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assigning the Sliding FragmentTab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);

        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the FragmentTab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }

        });
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                FragmentTab fragment = (FragmentTab) adapter.getItem(position);
                if (fragment != null) {
                    fragment.tabBecameVisible();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        Button b = (Button) findViewById(R.id.activity_btn_new);
//        System.out.println("Activity new button: " + b);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
//            Toast.makeText(this, "Under Construction!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_write_activities:
                ActivitySaver.writeActivities(this, ProjectManagerService.getInstance().getAllActivities());
                break;
            case R.id.action_show_activities:
                String s = ActivitySaver.readRawActivities(this);
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();
                break;
//            case R.id.action_load_activities:
//                List<Activity> activities = ActivitySaver.readActivities(this);
//                for (Activity a : activities) {
//                    ProjectManagerService.getInstance().addActivity(a);
//                }
//                break;
        }


        return super.onOptionsItemSelected(item);
    }


    private static Logger log = Logger.getLogger(MainActivity.class.getName());

    private void setLoggingProperties(String logProperties) throws Exception {

        LogManager lManager = LogManager.getLogManager();

        AssetManager am = getApplicationContext().getAssets();
        InputStream inputStream = am.open(logProperties);

        if (lManager != null) {
            lManager.readConfiguration(inputStream);
        }


    }

}
