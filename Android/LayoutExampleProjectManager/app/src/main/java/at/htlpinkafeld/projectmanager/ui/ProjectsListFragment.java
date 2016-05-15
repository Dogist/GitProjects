package at.htlpinkafeld.projectmanager.ui;

import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.service.ProjectsAdapter;

/**
 * Created by tq on 16-01-09.
 */
public class ProjectsListFragment extends BaseListFragment<Project> {

    public ProjectsListFragment() {
        super(new ProjectsAdapter());
    }

}
