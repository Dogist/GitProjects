package at.htlpinkafeld.projectmanager.ui;

import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import at.htlpinkafeld.projectmanager.R;

/**
 * Created by tq on 16-01-09.
 */
public abstract class BaseDetailsFragment extends Fragment {

    public abstract void saveButtonPressed();

    public abstract void newButtonPressed();

    public abstract void deleteButtonPressed();

    protected ActionMode mActionMode = null;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_details_actions, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_new:
                    newButtonPressed();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.action_save:
                    saveButtonPressed();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.action_delete:
                    deleteButtonPressed();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };


    public void addLongClickListener(View v) {
        if (v != null) {
//            v.setLongClickable(true);
            v.setOnLongClickListener(new View.OnLongClickListener() {
                // Called when the user long-clicks on someView
                public boolean onLongClick(View view) {
                    if (mActionMode != null) {
                        return false;
                    }

                    // Start the CAB using the ActionMode.Callback defined above
                    mActionMode = getActivity().startActionMode(mActionModeCallback);
                    view.setSelected(true);
                    return true;
                }
            });
        }
    }

}
