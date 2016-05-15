package at.htlpinkafeld.projectmanager.gui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import at.htlpinkafeld.projectmanager.service.ProjectListAdapter;


/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 */
public class ProjectListFragment extends ListFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectListAdapter projListAdapt = new ProjectListAdapter(getActivity());
        setListAdapter(projListAdapt);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ProjectFragment frag= (ProjectFragment) this.getParentFragment();
        frag.onListItemClick(l,position);
    }

}
