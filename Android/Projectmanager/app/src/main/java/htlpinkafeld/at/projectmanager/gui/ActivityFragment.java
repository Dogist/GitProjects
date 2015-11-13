package htlpinkafeld.at.projectmanager.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import htlpinkafeld.at.projectmanager.MainActivity;
import htlpinkafeld.at.projectmanager.R;
import htlpinkafeld.at.projectmanager.pojo.Activity;
import htlpinkafeld.at.projectmanager.service.ServiceClass;

/**
 * Created by User on 05.10.2015.
 */
public class ActivityFragment extends Fragment {

    private static final Logger log = Logger.getLogger(ActivityFragment.class.getName());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.activity_fragment, container, false);

        final ServiceClass sc = ServiceClass.getServiceClass();



        View.OnClickListener newLis = new View.OnClickListener() {
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                int id=Integer.parseInt(view.findViewById(R.id.a_id_text).toString());
                String name=view.findViewById(R.id.a_name_text).toString();
                String prior=view.findViewById(R.id.a_prior_text).toString();
                Date startD = null;
                try {
                    startD = dateFormat.parse(view.findViewById(R.id.p_start_date_text).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date endD= null;
                try {
                    endD = dateFormat.parse(view.findViewById(R.id.p_end_date_text).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String effort=view.findViewById(R.id.a_effort_text).toString();

                sc.addActivity(new Activity(id, name, prior, startD, endD, effort));
                log.info("onClick()");
            }
        };
        View.OnClickListener saveLis = new View.OnClickListener() {
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                int id=Integer.parseInt(view.findViewById(R.id.a_id_text).toString());
                String name=view.findViewById(R.id.a_name_text).toString();
                String prior=view.findViewById(R.id.a_prior_text).toString();
                Date startD = null;
                try {
                    startD = dateFormat.parse(view.findViewById(R.id.p_start_date_text).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date endD= null;
                try {
                    endD = dateFormat.parse(view.findViewById(R.id.p_end_date_text).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String effort=view.findViewById(R.id.a_effort_text).toString();

                sc.updateActivity(sc.sizeA()-1,new Activity(id, name, prior, startD, endD, effort));
                log.info("onClick()");
            }
        };
        View.OnClickListener delLis = new View.OnClickListener() {
            public void onClick(View v) {
                sc.removeActivity(sc.sizeA()-1);
                log.info("onClick()");
            }
        };

        view.findViewById(R.id.a_newB).setOnClickListener(newLis);
        view.findViewById(R.id.a_saveB).setOnClickListener(saveLis);
        view.findViewById(R.id.a_deleteB).setOnClickListener(delLis);

        log.info("onCreate()");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        log.info("onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.info("onCreate()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log.info("onActivityCreated");
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

    @Override
    public void onDetach() {
        super.onDetach();
        log.info("onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log.info("onDestroyView");
    }
}
