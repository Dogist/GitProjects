package at.htlpinkafeld.projectmanager.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;
import at.htlpinkafeld.projectmanager.service.ProjectManagerService;


public class TeamMembersDetailsFragment extends BaseDetailsFragment {

    EditText id, title, jobtitle, firstname, lastname, department;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_details, container, false);

//        Spinner spinner = (Spinner) v.findViewById(R.id.member_title);
        // Create an ArrayAdapter using the string array and a default spinner layout
//        String[] titles = new String[]{"Mr.", "Ms.", "Miss", "Mrs.", "Select title"};
//        ArrayAdapterWithHint<String> adapter = new ArrayAdapterWithHint<>(getActivity(),
//                android.R.layout.simple_spinner_item, titles);

        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

//        spinner.setAdapter(adapter);
//        spinner.setSelection(adapter.getCount());

        id = (EditText) v.findViewById(R.id.member_id);
        title = (EditText) v.findViewById(R.id.member_title);
        jobtitle = (EditText) v.findViewById(R.id.member_jobtitle);
        firstname = (EditText) v.findViewById(R.id.member_firstname);
        lastname = (EditText) v.findViewById(R.id.member_lastname);
        department = (EditText) v.findViewById(R.id.member_department);

        v.findViewById(R.id.member_btn_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newButtonPressed();
            }
        });
        v.findViewById(R.id.member_btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonPressed();
            }
        });
        v.findViewById(R.id.member_btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonPressed();
            }
        });
        // not the best solution, apparently it does not work on the fragment directly?
        addLongClickListener(v.findViewById(R.id.member_details_parent));
        return v;
    }

    public void showMember(TeamMember m) {
        id.setText(String.valueOf(m.getId()));
        title.setText(m.getTitle());
        jobtitle.setText(m.getJobtitle());
        firstname.setText(m.getFirstname());
        lastname.setText(m.getLastname());
        department.setText(m.getDepartment());
    }

    public TeamMember getPojo() {
        int nid = Integer.valueOf(id.getText().toString());
        String stitle = title.getText().toString();
        String sjobtitle = jobtitle.getText().toString();
        String sfirstname = firstname.getText().toString();
        String slastname = lastname.getText().toString();
        String sdepartment = department.getText().toString();
        TeamMember m = new TeamMember(nid, stitle, sjobtitle, sfirstname, slastname, sdepartment);
        return m;
    }

    @Override
    public void saveButtonPressed() {
        changedListener.onMemberChanged(getPojo());
    }

    @Override
    public void newButtonPressed() {
        createdListener.OnMemberCreated(getPojo());
        clearInputs();
    }

    private void clearInputs() {
        id.setText("");
        title.setText("");
        jobtitle.setText("");
        firstname.setText("");
        lastname.setText("");
        department.setText("");
    }

    @Override
    public void deleteButtonPressed() {
        // little bit of a hack
        deletedListener.onMemberDeleted(ProjectManagerService.getInstance().getMember(Integer.valueOf(id.getText().toString())));
    }

    public interface OnMemberCreatedListener {
        void OnMemberCreated(TeamMember m);
    }

    private OnMemberCreatedListener createdListener;

    public interface OnMemberChangedListener {
        void onMemberChanged(TeamMember m);
    }

    private OnMemberChangedListener changedListener;

    public interface OnMemberDeletedListener {
        void onMemberDeleted(TeamMember m);
    }

    private OnMemberDeletedListener deletedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            createdListener = (OnMemberCreatedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnMemberCreatedListener");
        }
        try {
            changedListener = (OnMemberChangedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnMemberChangedListener");
        }
        try {
            deletedListener = (OnMemberDeletedListener) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnMemberDeletedListener");
        }
    }

}