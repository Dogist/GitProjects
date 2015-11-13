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

import htlpinkafeld.at.projectmanager.R;
import htlpinkafeld.at.projectmanager.pojo.Project;
import htlpinkafeld.at.projectmanager.service.ServiceClass;

/**
 * Created by User on 28.09.2015.
 */
public class ProjectFragment extends Fragment {

    private static final Logger log = Logger.getLogger(ProjectFragment.class.getName());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =inflater.inflate(R.layout.project_fragment, container, false);

        final ServiceClass sc = ServiceClass.getServiceClass();

        View.OnClickListener newLis = new View.OnClickListener() {
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                int id=Integer.parseInt(view.findViewById(R.id.p_id_text).toString());
                String name=view.findViewById(R.id.p_name_text).toString();
                String contr=view.findViewById(R.id.p_contractor_text).toString();
                String procMod=view.findViewById(R.id.p_process_model_text).toString();
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
                String desc=view.findViewById(R.id.p_desc_text).toString();

                sc.addProject(new Project(id,name,contr,procMod,startD,endD,desc));
                log.info("onClick()");
            }
        };
        View.OnClickListener saveLis = new View.OnClickListener() {
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                int id=Integer.parseInt(view.findViewById(R.id.p_id_text).toString());
                String name=view.findViewById(R.id.p_name_text).toString();
                String contr=view.findViewById(R.id.p_contractor_text).toString();
                String procMod=view.findViewById(R.id.p_process_model_text).toString();
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
                String desc=view.findViewById(R.id.p_desc_text).toString();

                sc.updateProject(sc.sizeP()-1,new Project(id,name,contr,procMod,startD,endD,desc));
                log.info("onClick()");
            }
        };
        View.OnClickListener delLis = new View.OnClickListener() {
            public void onClick(View v) {
                sc.removeProject(sc.sizeP()-1);
                log.info("onClick()");
            }
        };

        view.findViewById(R.id.p_newB).setOnClickListener(newLis);
        view.findViewById(R.id.p_saveB).setOnClickListener(saveLis);
        view.findViewById(R.id.p_deleteB).setOnClickListener(delLis);

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
