package at.htlpinkafeld.projectmanager.gui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;

/**
 * Created by User on 14.12.2015.
 */
public class MemberFragment extends Fragment implements ListToDetailListener {

    private ActionMode mActionMode;
    private View memDetFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.member_fragment, container, false);

        memDetFrag=view.findViewById(R.id.memDetail_frag);

//        View memListFrag = view.findViewById(R.id.memList_frag);
//        memListFrag.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mActionMode != null) {
//                    return false;
//                }
//
//                mActionMode = getActivity().startActionMode(new ActionBarCallBack());
//                view.setSelected(true);
//                return true;
//            }
//        });

        return view;
    }

    @Override
    public void onListItemClick(ListView view, int position) {
        MemberDetailFragment.mapObjectToView((TeamMember) view.getAdapter().getItem(position), memDetFrag);
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
                case R.id.newM : memDetFrag.findViewById(R.id.m_newB).callOnClick();
                    break;
                case R.id.saveM : memDetFrag.findViewById(R.id.m_saveB).callOnClick();
                    break;
                case R.id.deleteM : memDetFrag.findViewById(R.id.m_deleteB).callOnClick();
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
