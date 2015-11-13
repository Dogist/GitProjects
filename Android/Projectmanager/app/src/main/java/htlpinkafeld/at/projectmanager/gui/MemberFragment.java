package htlpinkafeld.at.projectmanager.gui;/**
 * Created by User on 28.09.2015.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import htlpinkafeld.at.projectmanager.R;
import htlpinkafeld.at.projectmanager.pojo.TeamMember;
import htlpinkafeld.at.projectmanager.service.ServiceClass;

public class MemberFragment extends Fragment {

    private static final Logger log = Logger.getLogger(MemberFragment.class.getName());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.member_fragment, container, false);

        final ServiceClass sc = ServiceClass.getServiceClass();

        View.OnClickListener newLis = new View.OnClickListener() {
            public void onClick(View v) {
                int id=Integer.parseInt(view.findViewById(R.id.m_id_text).toString());
                String title=view.findViewById(R.id.m_title_text).toString();
                String job=view.findViewById(R.id.m_job_text).toString();
                String fname=view.findViewById(R.id.m_fname_text).toString();
                String lname=view.findViewById(R.id.m_lname_text).toString();
                String dept=view.findViewById(R.id.m_dep_text).toString();

                sc.addMember(new TeamMember(id, title, job, fname, lname, dept));

            }
        };
        View.OnClickListener saveLis = new View.OnClickListener() {
            public void onClick(View v) {
                int id=Integer.parseInt(view.findViewById(R.id.m_id_text).toString());
                String title=view.findViewById(R.id.m_title_text).toString();
                String job=view.findViewById(R.id.m_job_text).toString();
                String fname=view.findViewById(R.id.m_fname_text).toString();
                String lname=view.findViewById(R.id.m_lname_text).toString();
                String dept=view.findViewById(R.id.m_dep_text).toString();

                sc.updateMember(sc.sizeM()-11,new TeamMember(id, title, job, fname, lname, dept));
            }
        };
        View.OnClickListener delLis = new View.OnClickListener() {
            public void onClick(View v) {
                sc.removeMember(sc.sizeM() - 1);
            }
        };

        view.findViewById(R.id.m_newB).setOnClickListener(newLis);
        view.findViewById(R.id.m_saveB).setOnClickListener(saveLis);
        view.findViewById(R.id.m_deleteB).setOnClickListener(delLis);


        log.info("onCreateView()");
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
