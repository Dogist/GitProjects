package at.htlpinkafeld.projectmanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import at.htlpinkafeld.projectmanager.gui.SettingsActivity;
import at.htlpinkafeld.projectmanager.gui.SlidingTabLayout;
import at.htlpinkafeld.projectmanager.gui.ViewPagerAdapter;
import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;
import at.htlpinkafeld.projectmanager.service.ServiceClass;


public class MainActivity extends AppCompatActivity {

    private static final Logger log = Logger.getLogger(MainActivity.class.getName());

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Members","Projects", "Activities"};
    int Numboftabs =3;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context=getApplicationContext();

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Under construction!", Toast.LENGTH_SHORT).show();
                //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                //try {
                //
//
                //} catch (ParseException e) {
                //    e.printStackTrace();
                //}
                startActivity(new Intent(this, SettingsActivity.class));

                return true;
            case R.id.writeActM:
                FileOutputStream out=null;
                try {
                    out= openFileOutput("activities.csv", Context.MODE_PRIVATE);
                    ServiceClass sc=ServiceClass.getServiceClass();
                    for(int i=0; i<sc.sizeA();i++){
                        Activity a=sc.getActivity(i);
                        String res=a.getId()+";"+a.getName()+";"+a.getPrior()+";"+a.getEffort()+";"+a.getStartDat()+";"+a.getEndDat();
                        out.write(res.getBytes());
                        out.write('\n');
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return true;
            case R.id.showActM:
                FileInputStream in=null;
                try {
                    in = openFileInput("activities.csv");
                    int c;
                    String res="";
                    byte[] buffer=new byte[32];
                    while(in.read(buffer)!=-1){
                        res+=new String(buffer);
                        buffer=new byte[32];
                    }
                    Toast.makeText(this, res, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(in!=null){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return true;
            case R.id.showProjectM:
                //for(Project p:PMSQL_DAOFactory.getPM_DAOFactory(this).getProjectDAO().getEntityList()){
                //    Toast.makeText(this, p.toString(), Toast.LENGTH_LONG).show();
                //}
                Toast.makeText(this, ServiceClass.getServiceClass().getProject(5).toString(), Toast.LENGTH_LONG).show();
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
            sc.updateMember(mem);
        }
        sc.printAllM();
    }

    public void delMem(View v){

        ServiceClass sc = ServiceClass.getServiceClass();
        int id=Integer.parseInt(((EditText) findViewById(R.id.m_id_text)).getText().toString());
        if(sc.sizeM()>id)
            sc.removeMemberByID(id);
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
