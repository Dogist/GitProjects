package at.htlpinkafeld.projectmanager.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.logging.Logger;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.service.EntityServiceAdapter;
import at.htlpinkafeld.projectmanager.ui.util.EntityListAdapter;
import at.htlpinkafeld.projectmanager.ui.util.SelectionManager;

/**
 * Created by tq on 15-11-30.
 */
public abstract class BaseListFragment<E> extends ListFragment {

    private static final Logger log = Logger.getLogger(BaseListFragment.class.getName());

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.master_detail_list, container, false);
//    }

//    /**
//     * The serialization (saved instance state) Bundle key representing the
//     * activated item position. Only used on tablets.
//     */
//    private static final String STATE_ACTIVATED_POSITION = "activated_position";
//    /**
//     * The current activated item position. Only used on tablets.
//     */
//    private int mActivatedPosition = ListView.INVALID_POSITION;


    private EntityServiceAdapter<E> service;
    private EntityListAdapter<E> adapter;

    private SelectionManager<E> selection;
    //    private int selectionMode = ListView.CHOICE_MODE_SINGLE;
    private AbsListView.MultiChoiceModeListener multiModeCallback;

    public BaseListFragment(EntityServiceAdapter<E> service) {
        this.service = service;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new EntityListAdapter(getActivity(), service);

        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        selection = new SelectionManager<>();


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        log.fine("onViewCreated called");

        // Restore the previously serialized activated item position.
//        if (savedInstanceState != null
//                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
//            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
//        }

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        addMultiChoiceModeListener(getListView());

        setOnLongItemClickListener(getListView());


    }

    @Override
    public void onStart() {
        super.onStart();
        if (getListView().getAdapter().getCount() > 0 && getListView().getCheckedItemCount() == 0) {
            listItemSelectionListener.onItemSelected(adapter.getItem(0));
            getListView().setItemChecked(0, true);
        }
    }

    private void setOnLongItemClickListener(final ListView listView) {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                log.fine("onItemLongClick called");
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
//                selectionMode = ListView.CHOICE_MODE_MULTIPLE_MODAL;

                // this is important to show the CAB immediately on longclick
                listView.setItemChecked(position, true);
                return false;
            }
        });
    }


//    private void setActivatedPosition(int position) {
//        if (position == ListView.INVALID_POSITION) {
//            getListView().setItemChecked(mActivatedPosition, false);
//        } else {
//            getListView().setItemChecked(position, true);
//        }
//
//        mActivatedPosition = position;
//    }

    public void updateView() {
        adapter.notifyDataSetChanged();
//        System.out.println("updateView called");
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        log.fine("onListItemClick called, mode: " + l.getChoiceMode());

        // reset the choice mode to single after the action mode was aborted
//        if (selectionMode == ListView.CHOICE_MODE_SINGLE) {
//            l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        }

        listItemSelectionListener.onItemSelected(adapter.getItem(position));
        getListView().setItemChecked(position, true);
    }

    public interface OnListItemSelected<E> {
        void onItemSelected(E m);
    }

    OnListItemSelected<E> listItemSelectionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listItemSelectionListener = (OnListItemSelected) getParentFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement OnListItemSelected");
        }

    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        log.fine("setSelection called");
        getListView().setItemChecked(position, true);
        listItemSelectionListener.onItemSelected(adapter.getItem(position));
    }

    @Override
    public EntityListAdapter<E> getListAdapter() {
        return this.adapter;
    }

    public void addMultiChoiceModeListener(final ListView view) {
//        view.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        this.multiModeCallback = new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                log.fine("onItemCheckedStateChanged called, mode: " + getListView().getChoiceMode());
                selection.toggleSelection(getListAdapter().getItem(position));
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        deleteSelectedItems();
                        selection.clear();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_list_batch, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
                log.fine("onDestroyActionMode called");
                view.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

//                selectionMode = ListView.CHOICE_MODE_SINGLE;
                selection.clear();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        };
        view.setMultiChoiceModeListener(multiModeCallback);
    }

    protected void deleteSelectedItems() {
//        List<E> toDelete = new ArrayList<>();
        // important: first get all elements corresponding to the IDs
//        for (Integer i : selection.getSelectedItems()) {
//            toDelete.add(adapter.getItem(i));
//        }
        // now delete them using the service
//        for (E e : toDelete) {
//            service.delete(e);
//        }
        for (E e : selection.getSelectedItems()) {
            service.delete(e);
        }
    }

}
