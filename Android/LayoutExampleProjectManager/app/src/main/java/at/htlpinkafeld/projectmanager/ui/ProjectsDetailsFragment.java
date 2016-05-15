package at.htlpinkafeld.projectmanager.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.service.ProjectManagerService;


public class ProjectsDetailsFragment extends BaseDetailsFragment {

    EditText id, name, contractor, processmodel, startdate, enddate, description;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_project_details, container, false);

        id = (EditText) v.findViewById(R.id.project_txt_id);
        name = (EditText) v.findViewById(R.id.project_txt_name);
        contractor = (EditText) v.findViewById(R.id.project_txt_contractor);
        processmodel = (EditText) v.findViewById(R.id.project_txt_processmodel);
        startdate = (EditText) v.findViewById(R.id.project_txt_startdate);
        enddate = (EditText) v.findViewById(R.id.project_txt_enddate);
        description = (EditText) v.findViewById(R.id.project_txt_description);


        v.findViewById(R.id.project_btn_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newButtonPressed();
            }
        });
        v.findViewById(R.id.project_btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonPressed();
            }
        });
        v.findViewById(R.id.project_btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonPressed();
            }
        });

        addLongClickListener(v.findViewById(R.id.project_details_parent));

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String defaultProcessModel = sharedPref.getString(getString(R.string.pref_key_default_processmodel), "");

        processmodel.setText(defaultProcessModel);
        return v;
    }


    public void showProject(Project p) {
        id.setText(String.valueOf(p.getId()));
        name.setText(p.getName());
        contractor.setText(p.getContractor());
        processmodel.setText(p.getProcessModel());
        startdate.setText(sdf.format(p.getStartDate()));
        enddate.setText(sdf.format(p.getEndDate()));
        description.setText(p.getDescription());
    }

    public Project getPojo() {
        try {
            int nid = Integer.valueOf(id.getText().toString());
            String sname = name.getText().toString();
            String scontractor = contractor.getText().toString();
            String sprocessmodel = processmodel.getText().toString();
            Date dstartdate = sdf.parse(startdate.getText().toString());
            Date denddate = sdf.parse(enddate.getText().toString());
            String sdescription = description.getText().toString();
            Project p = new Project(nid, sname, scontractor, sprocessmodel, dstartdate, denddate, sdescription);
            return p;
        } catch (ParseException e) {
            Toast.makeText(getActivity(), R.string.invalid_date_format, Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    public void saveButtonPressed() {
        changedListener.onProjectChanged(getPojo());
    }

    @Override
    public void newButtonPressed() {
        createdListener.onProjectCreated(getPojo());
    }

    @Override
    public void deleteButtonPressed() {
        // little bit of a hack
        deletedListener.onProjectDeleted(ProjectManagerService.getInstance().getProject(Integer.valueOf(id.getText().toString())));
    }

    public interface OnProjectCreatedListener {
        void onProjectCreated(Project m);
    }

    private OnProjectCreatedListener createdListener;

    public interface OnProjectChangedListener {
        void onProjectChanged(Project m);
    }

    private OnProjectChangedListener changedListener;

    public interface OnProjectedDeletedListener {
        void onProjectDeleted(Project m);
    }

    private OnProjectedDeletedListener deletedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            createdListener = (OnProjectCreatedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnProjectCreatedListener");
        }
        try {
            changedListener = (OnProjectChangedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnProjectChangedListener");
        }
        try {
            deletedListener = (OnProjectedDeletedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnProjectDeletedListener");
        }
    }

}
