package at.htlpinkafeld.projectmanager.gui;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.service.ProjectSpinnerAdapter;
import at.htlpinkafeld.projectmanager.service.ServiceClass;


/**
 * Created by User on 05.10.2015.
 */
public class ActivityFragment extends AbstrDetailFragment {

    private static final Logger log = Logger.getLogger(ActivityFragment.class.getName());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.activity_fragment, container, false);

        final ServiceClass sc = ServiceClass.getServiceClass();

        final Spinner spinner = (Spinner) view.findViewById(R.id.a_proj_spinner);
        //set monthList in Adapter (would also work with a List<T>)
        ProjectSpinnerAdapter adapter = new ProjectSpinnerAdapter(this.getContext());
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        List prTit=new ArrayList();
//
//        for(int i=0;i<sc.sizeP();i++){
//            prTit.add(sc.getProject(i).getName());
//        }
//        ArrayAdapter<String> ada = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, prTit );
//        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);


        newLis = new View.OnClickListener() {
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Project p =(Project)((Spinner) view.findViewById(R.id.a_proj_spinner)).getSelectedItem();
                int id=Integer.parseInt(((EditText)view.findViewById(R.id.a_id_text)).getText().toString());
                String name=((EditText)view.findViewById(R.id.a_name_text)).getText().toString();
                String prior=((EditText)view.findViewById(R.id.a_prior_text)).getText().toString();
                Date startD = null;
                try {
                    startD = dateFormat.parse(((EditText)view.findViewById(R.id.a_start_date_text)).getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date endD= null;
                try {
                    endD = dateFormat.parse(((EditText)view.findViewById(R.id.a_end_date_text)).getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String effort=((EditText)view.findViewById(R.id.a_effort_text)).getText().toString();

                sc.addActivity(new Activity(p.getId(), id, name, prior, startD, endD, effort));
                sc.printAllA();
                log.info("onClick()");
            }
        };
        saveLis = new View.OnClickListener() {
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Project p =(Project)((Spinner) view.findViewById(R.id.a_proj_spinner)).getSelectedItem();
                int id=Integer.parseInt(((EditText)view.findViewById(R.id.a_id_text)).getText().toString());
                String name=((EditText)view.findViewById(R.id.a_name_text)).getText().toString();
                String prior=((EditText)view.findViewById(R.id.a_prior_text)).getText().toString();
                Date startD = null;
                try {
                    startD = dateFormat.parse(((EditText)view.findViewById(R.id.a_start_date_text)).getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date endD= null;
                try {
                    endD = dateFormat.parse(((EditText)view.findViewById(R.id.a_end_date_text)).getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String effort=((EditText)view.findViewById(R.id.a_effort_text)).getText().toString();
                if(sc.sizeA()>0)
                    sc.updateActivity(new Activity(p.getId(), id, name, prior, startD, endD, effort));
                sc.printAllA();
                log.info("onClick()");
            }
        };
        delLis = new View.OnClickListener() {
            public void onClick(View v) {
                int id=Integer.parseInt(((EditText) view.findViewById(R.id.a_id_text)).getText().toString());
                if(sc.sizeA()>0)
                    sc.removeActivityByID(id);
                sc.printAllA();
                log.info("onClick()");
            }
        };

        view.findViewById(R.id.a_newB).setOnClickListener(newLis);
        view.findViewById(R.id.a_saveB).setOnClickListener(saveLis);
        view.findViewById(R.id.a_deleteB).setOnClickListener(delLis);


        ((EditText)view.findViewById(R.id.a_prior_text)).setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString(SettingsActivity.ACTIVITY_PRIORITY, ""));
        log.info("onCreate()");
        return view;
    }
}
