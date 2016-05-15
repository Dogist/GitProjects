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
import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.service.ProjectManagerService;

/**
 * Created by tq on 16-01-25.
 */
public class ActivitiesDetailsFragment extends BaseDetailsFragment {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    EditText id, name, priority, startDate, endDate, effort;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity_details, container, false);

        id = (EditText) v.findViewById(R.id.activity_txt_id);
        name = (EditText) v.findViewById(R.id.activity_txt_name);
        priority = (EditText) v.findViewById(R.id.activity_txt_priority);
        startDate = (EditText) v.findViewById(R.id.activity_txt_startdate);
        endDate = (EditText) v.findViewById(R.id.activity_txt_enddate);
        effort = (EditText) v.findViewById(R.id.activity_txt_effort);


        v.findViewById(R.id.activity_btn_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newButtonPressed();
            }
        });
        v.findViewById(R.id.activity_btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonPressed();
            }
        });
        v.findViewById(R.id.activity_btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonPressed();
            }
        });

        addLongClickListener(v.findViewById(R.id.activity_details_parent));

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String defaultPriority = sharedPref.getString(getString(R.string.pref_key_default_priority), "");

        priority.setText(defaultPriority);

        return v;
    }


    public void showActivity(Activity m) {
        id.setText(String.valueOf(m.getId()));
        name.setText(m.getName());
        priority.setText(m.getPriority());
        startDate.setText(sdf.format(m.getStartDate()));
        endDate.setText(sdf.format(m.getEndDate()));
        effort.setText(String.valueOf(m.getEffort()));
    }

    public Activity getPojo() {
        int nid = Integer.valueOf(id.getText().toString());
        String sname = name.getText().toString();
        String sprio = priority.getText().toString();
        Activity a = null;
        try {
            Date dstart = sdf.parse(startDate.getText().toString());
            Date dend = sdf.parse(endDate.getText().toString());
            Double seffort = Double.parseDouble(effort.getText().toString());
            a = new Activity(nid, sname, sprio, dstart, dend, seffort);

        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), R.string.invalid_number_format, Toast.LENGTH_LONG).show();
        } catch (ParseException e) {
            Toast.makeText(getActivity(), R.string.invalid_date_format, Toast.LENGTH_LONG).show();
        }
        return a;
    }

    @Override
    public void saveButtonPressed() {
        changedListener.onActivityChanged(getPojo());
    }

    @Override
    public void newButtonPressed() {
        createdListener.onActivityCreated(getPojo());
    }

    @Override
    public void deleteButtonPressed() {
        // little bit of a hack
        deletedListener.onActivityDeleted(ProjectManagerService.getInstance().getActivity(Integer.valueOf(id.getText().toString())));
    }

    public interface OnActivityCreatedListener {
        void onActivityCreated(Activity m);
    }

    private OnActivityCreatedListener createdListener;

    public interface OnActivityChangedListener {
        void onActivityChanged(Activity m);
    }

    private OnActivityChangedListener changedListener;

    public interface OnActivityDeletedListener {
        void onActivityDeleted(Activity m);
    }

    private OnActivityDeletedListener deletedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            createdListener = (OnActivityCreatedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnActivityCreatedListener");
        }
        try {
            changedListener = (OnActivityChangedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnActivityChangedListener");
        }
        try {
            deletedListener = (OnActivityDeletedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnActivityDeletedListener");
        }
    }
}
