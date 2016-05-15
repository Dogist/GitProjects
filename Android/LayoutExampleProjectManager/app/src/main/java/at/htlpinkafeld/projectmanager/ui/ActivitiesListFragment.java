package at.htlpinkafeld.projectmanager.ui;

import at.htlpinkafeld.projectmanager.pojo.Activity;
import at.htlpinkafeld.projectmanager.service.ActivityAdapter;

/**
 * Created by tq on 16-01-25.
 */
public class ActivitiesListFragment extends BaseListFragment<Activity> {

    public ActivitiesListFragment() {
        super(new ActivityAdapter());
    }


}
