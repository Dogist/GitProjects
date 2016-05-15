package at.htlpinkafeld.projectmanager.ui;

import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.service.ProjectManagerService;

/**
 * Created by tq on 16-01-25.
 */
public class ActivitiesMainFragment extends BaseMainFragment<ActivitiesListFragment, ActivitiesDetailsFragment>
        implements ActivitiesDetailsFragment.OnActivityChangedListener, ActivitiesDetailsFragment.OnActivityCreatedListener,
        ActivitiesDetailsFragment.OnActivityDeletedListener, BaseListFragment.OnListItemSelected<Activity> {

    public ActivitiesMainFragment() {
        super(new ActivitiesListFragment(), new ActivitiesDetailsFragment());
    }

    @Override
    public void tabBecameVisible() {
        getListViewFragment().updateView();
    }


    @Override
    public void onItemSelected(Activity p) {
        getDetailsViewFragment().showActivity(p);
    }

    @Override
    public void onActivityCreated(Activity p) {
        ProjectManagerService.getInstance().addActivity(p);
        getListViewFragment().updateView();
        getListViewFragment().setSelection(ProjectManagerService.getInstance().getActivities().indexOf(p));
    }

    @Override
    public void onActivityChanged(Activity p) {
        ProjectManagerService.getInstance().updateActivity(p);
        getListViewFragment().updateView();
    }

    @Override
    public void onActivityDeleted(Activity m) {
        int oldIndex = ProjectManagerService.getInstance().getActivities().indexOf(m);
        ProjectManagerService.getInstance().deleteActivity(m);
        getListViewFragment().updateView();
        if (ProjectManagerService.getInstance().getActivities().size() > 0) {
            if (oldIndex > 0) {
                getListViewFragment().setSelection(oldIndex - 1);
            } else {
                getListViewFragment().setSelection(0);
            }
        }
    }
}
