package at.htlpinkafeld.projectmanager.ui;

import at.htlpinkafeld.projectmanager.pojo.Project;
import at.htlpinkafeld.projectmanager.service.ProjectManagerService;

/**
 * Created by tq on 16-01-09.
 */
public class ProjectsMainFragment extends BaseMainFragment<BaseListFragment, ProjectsDetailsFragment>
        implements ProjectsDetailsFragment.OnProjectChangedListener, ProjectsDetailsFragment.OnProjectCreatedListener,
        ProjectsDetailsFragment.OnProjectedDeletedListener, BaseListFragment.OnListItemSelected<Project> {

    public ProjectsMainFragment() {
        super(new ProjectsListFragment(), new ProjectsDetailsFragment());
    }

    @Override
    public void onItemSelected(Project p) {
        getDetailsViewFragment().showProject(p);
        ProjectManagerService.getInstance().setSelectedProject(p);
    }

    @Override
    public void onProjectCreated(Project p) {
        ProjectManagerService.getInstance().addProject(p);
        getListViewFragment().updateView();
        getListViewFragment().setSelection(ProjectManagerService.getInstance().getProjects().indexOf(p));
    }

    @Override
    public void onProjectChanged(Project p) {
        ProjectManagerService.getInstance().updateProject(p);
        getListViewFragment().updateView();
    }

    @Override
    public void onProjectDeleted(Project m) {
        int oldIndex = ProjectManagerService.getInstance().getProjects().indexOf(m);
        ProjectManagerService.getInstance().deleteProject(m);
        getListViewFragment().updateView();
        if (ProjectManagerService.getInstance().getProjects().size() > 0) {
            if (oldIndex > 0) {
                getListViewFragment().setSelection(oldIndex - 1);
            } else {
                getListViewFragment().setSelection(0);
            }
        }
    }
}
