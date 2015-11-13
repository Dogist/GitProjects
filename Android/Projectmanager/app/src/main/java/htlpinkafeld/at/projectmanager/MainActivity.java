package htlpinkafeld.at.projectmanager;

import android.content.res.AssetManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import htlpinkafeld.at.projectmanager.gui.SlidingTabLayout;
import htlpinkafeld.at.projectmanager.gui.ViewPagerAdapter;
import htlpinkafeld.at.projectmanager.pojo.TeamMember;
import htlpinkafeld.at.projectmanager.service.ServiceClass;

public class MainActivity extends AppCompatActivity {

    private static final Logger log = Logger.getLogger(MainActivity.class.getName());

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Members","Projects", "Activities"};
    int Numboftabs =3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            setLoggingProperties("logging.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("onCreate()");
        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

    }

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addMem(View v){

        ServiceClass sc = ServiceClass.getServiceClass();

        EditText idE =(EditText) findViewById(R.id.m_id_text);
        EditText titleE =(EditText) findViewById(R.id.m_title_text);
        EditText jobE =(EditText) findViewById(R.id.m_job_text);
        EditText fnameE =(EditText) findViewById(R.id.m_fname_text);
        EditText lnameE =(EditText) findViewById(R.id.m_lname_text);
        EditText depE =(EditText) findViewById(R.id.m_dep_text);

        if(idE!=null&&titleE!=null&&jobE!=null&&fnameE!=null&&lnameE!=null&&depE!=null) {
            int id = Integer.parseInt(idE.getText().toString());
            String title = titleE.getText().toString();
            String job = jobE.getText().toString();
            String fname = fnameE.getText().toString();
            String lname = lnameE.getText().toString();
            String dep = depE.getText().toString();

            TeamMember mem = new TeamMember(id, title, job, fname, lname, dep);
            sc.addMember(mem);
        }
        sc.printAllM();
    }

    public void saveMem(View v){

        ServiceClass sc = ServiceClass.getServiceClass();

        if(sc.sizeM()>0) {
            EditText idE = (EditText) findViewById(R.id.m_id_text);
            EditText titleE = (EditText) findViewById(R.id.m_title_text);
            EditText jobE = (EditText) findViewById(R.id.m_job_text);
            EditText fnameE = (EditText) findViewById(R.id.m_fname_text);
            EditText lnameE = (EditText) findViewById(R.id.m_lname_text);
            EditText depE = (EditText) findViewById(R.id.m_dep_text);

            int id = Integer.parseInt(idE.getText().toString());
            String title = titleE.getText().toString();
            String job = jobE.getText().toString();
            String fname = fnameE.getText().toString();
            String lname = lnameE.getText().toString();
            String dep = depE.getText().toString();

            TeamMember mem = new TeamMember(id, title, job, fname, lname, dep);
            sc.updateMember(sc.sizeM() - 1, mem);
        }
        sc.printAllM();
    }

    public void delMem(View v){

        ServiceClass sc = ServiceClass.getServiceClass();

        if(sc.sizeM()>0)
        sc.removeMember(sc.sizeM()-1);
        sc.printAllM();
    }

    private void setLoggingProperties(String logProperties) throws Exception {

        LogManager lManager = LogManager.getLogManager();

        //access to the folder ‘assets’
        AssetManager am = getApplicationContext().getAssets();
        //opening the file
        InputStream inputStream = am.open(logProperties);

        if(lManager!=null) {
            lManager.readConfiguration(inputStream);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        log.info("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        log.info("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        log.info("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        log.info("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log.info("onDestroy");
    }

}
