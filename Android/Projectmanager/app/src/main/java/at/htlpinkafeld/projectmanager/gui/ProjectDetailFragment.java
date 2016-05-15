package at.htlpinkafeld.projectmanager.gui;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.service.ServiceClass;


/**
 * Created by User on 28.09.2015.
 */
public class ProjectDetailFragment extends AbstrDetailFragment {

    private static final Logger log = Logger.getLogger(ActivityFragment.class.getName());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =inflater.inflate(R.layout.project_detail_fragment, container, false);

        final ServiceClass sc = ServiceClass.getServiceClass();

        this.newLis = new View.OnClickListener() {
            public void onClick(View v) {

                int id=Integer.parseInt(((EditText)view.findViewById(R.id.p_id_text)).getText().toString());
                String name=((EditText)view.findViewById(R.id.p_name_text)).getText().toString();
                String contr=((EditText)view.findViewById(R.id.p_contractor_text)).getText().toString();
                String procMod=((EditText)view.findViewById(R.id.p_process_model_text)).getText().toString();
                Date startD = null;
                try {
                    startD = dateFormat.parse(((EditText)view.findViewById(R.id.p_start_date_text)).getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date endD= null;
                try {
                    endD = dateFormat.parse(((EditText)view.findViewById(R.id.p_end_date_text)).getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String desc=((EditText)view.findViewById(R.id.p_desc_text)).getText().toString();

                sc.addProject(new Project(id,name,contr,procMod,startD,endD,desc));
                sc.printAllP();
                log.info("onClick()");
            }
        };

        saveLis = new View.OnClickListener() {
            public void onClick(View v) {

                int id=Integer.parseInt(((EditText)view.findViewById(R.id.p_id_text)).getText().toString());
                String name=((EditText)view.findViewById(R.id.p_name_text)).getText().toString();
                String contr=((EditText)view.findViewById(R.id.p_contractor_text)).getText().toString();
                String procMod=((EditText)view.findViewById(R.id.p_process_model_text)).getText().toString();
                Date startD = null;
                try {
                    startD = dateFormat.parse(((EditText)view.findViewById(R.id.p_start_date_text)).getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date endD= null;
                try {
                    endD = dateFormat.parse(((EditText)view.findViewById(R.id.p_end_date_text)).getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String desc=((EditText)view.findViewById(R.id.p_desc_text)).getText().toString();

                sc.updateProject(new Project(id, name, contr, procMod, startD, endD, desc));
                sc.printAllP();
                log.info("onClick()");
            }
        };
        delLis = new View.OnClickListener() {
            public void onClick(View v) {
                int id = Integer.parseInt(((EditText) view.findViewById(R.id.p_id_text)).getText().toString());
                if(sc.sizeP()>0) {
                    sc.removeProjectByID(id);
                }
                sc.printAllP();
                log.info("onClick()");
            }
        };

        view.findViewById(R.id.p_newB).setOnClickListener(newLis);
        view.findViewById(R.id.p_saveB).setOnClickListener(saveLis);
        view.findViewById(R.id.p_deleteB).setOnClickListener(delLis);

        ((EditText)view.findViewById(R.id.p_process_model_text)).setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString(SettingsActivity.PROCESS_MODEL, ""));

        log.info("onCreate()");
        return view;
    }



    public static void mapObjectToView(Project project, View view){
        ((EditText)view.findViewById(R.id.p_id_text)).setText(Integer.toString(project.getId()));
        ((EditText)view.findViewById(R.id.p_name_text)).setText(project.getName());
        ((EditText)view.findViewById(R.id.p_process_model_text)).setText(project.getProcMod());
        ((EditText)view.findViewById(R.id.p_contractor_text)).setText(project.getContr());
        ((EditText)view.findViewById(R.id.p_start_date_text)).setText(dateFormat.format(project.getStartD()));
        ((EditText)view.findViewById(R.id.p_end_date_text)).setText(dateFormat.format(project.getEndD()));
    }

    public static void mapViewToObject(View view, Project project){
        try {
            project.setId(Integer.parseInt(((EditText) view.findViewById(R.id.p_id_text)).getText().toString()));
            project.setName(((EditText) view.findViewById(R.id.p_name_text)).getText().toString());
            project.setProcMod(((EditText) view.findViewById(R.id.p_process_model_text)).getText().toString());
            project.setContr(((EditText) view.findViewById(R.id.p_contractor_text)).getText().toString());
            project.setStartD(dateFormat.parse(((EditText) view.findViewById(R.id.p_start_date_text)).getText().toString()));
            project.setEndD(dateFormat.parse(((EditText) view.findViewById(R.id.p_end_date_text)).getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.newM:
                newLis.onClick(this.getView());
                break;
            case R.id.saveM:
                saveLis.onClick(this.getView());
                break;
            case R.id.deleteM:
                delLis.onClick(this.getView());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
