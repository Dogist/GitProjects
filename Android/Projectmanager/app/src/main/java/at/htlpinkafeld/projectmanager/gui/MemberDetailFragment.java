package at.htlpinkafeld.projectmanager.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;
import at.htlpinkafeld.projectmanager.service.ServiceClass;


public class MemberDetailFragment extends AbstrDetailFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.member_detailfragment, container, false);

        final ServiceClass sc = at.htlpinkafeld.projectmanager.service.ServiceClass.getServiceClass();

        newLis = new View.OnClickListener() {
            public void onClick(View v) {
                int id=Integer.parseInt(((EditText) view.findViewById(R.id.m_id_text)).getText().toString());
                String title=((EditText)view.findViewById(R.id.m_title_text)).getText().toString();
                String job=((EditText)view.findViewById(R.id.m_job_text)).getText().toString();
                String fname=((EditText)view.findViewById(R.id.m_fname_text)).getText().toString();
                String lname=((EditText)view.findViewById(R.id.m_lname_text)).getText().toString();
                String dept=((EditText)view.findViewById(R.id.m_dep_text)).getText().toString();

                sc.addMember(new TeamMember(id, title, job, fname, lname, dept));

            }
        };
        saveLis = new View.OnClickListener() {
            public void onClick(View v) {
                TeamMember mem = new TeamMember();
                mapViewToObject(view, mem);

                sc.updateMember(mem);
            }
        };
        delLis = new View.OnClickListener() {
            public void onClick(View v) {
                int id=Integer.parseInt(((EditText) view.findViewById(R.id.m_id_text)).getText().toString());
                if(sc.sizeM()>0)
                    sc.removeMemberByID(id);
                sc.printAllP();
            }
        };

        view.findViewById(R.id.m_newB).setOnClickListener(newLis);
        view.findViewById(R.id.m_saveB).setOnClickListener(saveLis);
        view.findViewById(R.id.m_deleteB).setOnClickListener(delLis);

        return view;
    }


    public static void mapObjectToView(TeamMember teamMember, View view){
        ((EditText)view.findViewById(R.id.m_id_text)).setText(Integer.toString(teamMember.getId()));
        ((EditText)view.findViewById(R.id.m_title_text)).setText(teamMember.getTitle());
        ((EditText)view.findViewById(R.id.m_job_text)).setText(teamMember.getJob());
        ((EditText)view.findViewById(R.id.m_fname_text)).setText(teamMember.getFname());
        ((EditText)view.findViewById(R.id.m_lname_text)).setText(teamMember.getLname());
        ((EditText)view.findViewById(R.id.m_dep_text)).setText(teamMember.getDept());
    }

    public static void mapViewToObject(View view, TeamMember teamMember){
        teamMember.setId(Integer.parseInt(((EditText) view.findViewById(R.id.m_id_text)).getText().toString()));
        teamMember.setTitle(((EditText) view.findViewById(R.id.m_title_text)).getText().toString());
        teamMember.setJob(((EditText) view.findViewById(R.id.m_job_text)).getText().toString());
        teamMember.setFname(((EditText)view.findViewById(R.id.m_fname_text)).getText().toString());
        teamMember.setLname(((EditText)view.findViewById(R.id.m_lname_text)).getText().toString());
        teamMember.setDept(((EditText) view.findViewById(R.id.m_dep_text)).getText().toString());
    }

}
