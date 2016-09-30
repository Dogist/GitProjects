package at.htlpinkafeld.projectmanager.gui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import at.htlpinkafeld.projectmanager.service.MemberListAdapter;


/**
 * Created by User on 14.12.2015.
 */
public class MemberListFragment extends ListFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemberListAdapter memListAdapt = new MemberListAdapter(getActivity());
        setListAdapter(memListAdapt);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MemberFragment memFrag = (MemberFragment) this.getParentFragment();
        memFrag.onListItemClick(l, position);

    }

}
