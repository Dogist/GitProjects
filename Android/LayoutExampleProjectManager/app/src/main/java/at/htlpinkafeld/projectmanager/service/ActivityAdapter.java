package at.htlpinkafeld.projectmanager.service;

import java.util.List;

import at.htlpinkafeld.projectmanager.pojo.Activity;

/**
 * Created by tq on 16-01-25.
 */
public class ActivityAdapter implements EntityServiceAdapter<Activity> {
    @Override
    public List<Activity> getList() {
        return ProjectManagerService.getInstance().getActivities();
    }

    @Override
    public Activity get(int id) {
        return ProjectManagerService.getInstance().getActivity(id);
    }

    @Override
    public void add(Activity activity) {
        ProjectManagerService.getInstance().addActivity(activity);
    }

    @Override
    public void update(Activity activity) {
        ProjectManagerService.getInstance().updateActivity(activity);
    }

    @Override
    public void delete(Activity activity) {
        ProjectManagerService.getInstance().deleteActivity(activity);
    }
}
