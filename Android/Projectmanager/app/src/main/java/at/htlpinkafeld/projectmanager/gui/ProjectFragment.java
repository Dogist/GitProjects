package at.htlpinkafeld.projectmanager.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.pojo.Project;


public class ProjectFragment extends Fragment implements ListToDetailListener {

    private static final int REQUEST_CODE = 10;

    private ActionMode mActionMode;
    private View prDetView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.project_fragment, container, false);

        prDetView=view.findViewById(R.id.projDetail_frag);
        View prListView = view.findViewById(R.id.projList_frag);
        prListView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false;
                }

                mActionMode = getActivity().startActionMode(new ActionBarCallBack());
                view.setSelected(true);
                return true;
            }
        });

        Button button= (Button) view.findViewById(R.id.start_duration_calc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DurationCalculator.class);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    i.putExtra(DurationCalculator.EXTRA_START, sdf.parse(((EditText) prDetView.findViewById(R.id.p_start_date_text)).getText().toString()));
                    i.putExtra(DurationCalculator.EXTRA_END, sdf.parse(((EditText) prDetView.findViewById(R.id.p_end_date_text)).getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                startActivityForResult(i, REQUEST_CODE);
            }
        });
        return view;
    }

    @Override
    public void onListItemClick(ListView view, int position) {
        ProjectDetailFragment.mapObjectToView((Project) view.getAdapter().getItem(position), prDetView);

    }

    class ActionBarCallBack implements ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Item Selected");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.newM : prDetView.findViewById(R.id.p_newB).callOnClick();
                    break;
                case R.id.saveM : prDetView.findViewById(R.id.p_saveB).callOnClick();
                    break;
                case R.id.deleteM : prDetView.findViewById(R.id.p_deleteB).callOnClick();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode=null;
        }
    }
}
