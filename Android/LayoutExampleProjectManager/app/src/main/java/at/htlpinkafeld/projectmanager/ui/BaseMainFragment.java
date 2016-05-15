package at.htlpinkafeld.projectmanager.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import at.htlpinkafeld.projectmanager.R;

/**
 * Created by tq on 15-11-30.
 */
public class BaseMainFragment<LV extends Fragment, DV extends Fragment> extends Fragment implements FragmentTab {

    private String MEMBER_LIST_VIEW_TAG = "member_list_view_tag";

    private LV listViewFragment;
    private DV detailsViewFragment;

    private FragmentManager retainedChildFragmentManager;

    public BaseMainFragment(LV listViewFragment, DV detailsViewFragment) {
        this.listViewFragment = listViewFragment;
        this.detailsViewFragment = detailsViewFragment;
    }

    public LV getListViewFragment() {
        return listViewFragment;
    }

    public DV getDetailsViewFragment() {
        return detailsViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.master_detail_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getChildFragmentManager().findFragmentByTag(MEMBER_LIST_VIEW_TAG) == null) {
            getChildFragmentManager().beginTransaction()
                    .add(R.id.master_detail_list, this.listViewFragment, MEMBER_LIST_VIEW_TAG)
                    .add(R.id.master_detail_detail, this.detailsViewFragment)
                    .commit();
        }

    }

    @Override
    public void tabBecameVisible() {
    }



    /**
     * Use this instead of getFragmentManager, because support library from 20+,
     * has a bug that doesn't retain instance of nested fragments!!!!
     *
     * @return a retained childFragmentManager or a new one if it doesn’t exist yet
     */
    protected FragmentManager childFragmentManager() {
        if (retainedChildFragmentManager == null) {
            retainedChildFragmentManager = getChildFragmentManager();
        }
        return retainedChildFragmentManager;
    }


    /**
     * onAttach runs as the first lifecycle method of a Fragment
     * Here we restore the last retained child fragment manager
     * to the new created fragment,
     * because support library from 20+, has a bug
     * that doesn't retain instance of nested fragments
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (retainedChildFragmentManager != null) {
            try {
                Field childFMField = Fragment.class.getDeclaredField("mChildFragmentManager");
                childFMField.setAccessible(true);
                childFMField.set(this, retainedChildFragmentManager);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }



}
