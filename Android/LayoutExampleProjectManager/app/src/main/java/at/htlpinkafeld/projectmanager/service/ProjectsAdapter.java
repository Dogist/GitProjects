package at.htlpinkafeld.projectmanager.service;

import java.util.List;

import at.htlpinkafeld.projectmanager.pojo.Project;

/**
 * Created by tq on 16-01-09.
 */
public class ProjectsAdapter implements EntityServiceAdapter<Project> {
    @Override
    public List<Project> getList() {
        return ProjectManagerService.getInstance().getProjects();
    }

    @Override
    public Project get(int id) {
        return ProjectManagerService.getInstance().getProject(id);
    }

    @Override
    public void add(Project project) {
        ProjectManagerService.getInstance().addProject(project);
    }

    @Override
    public void update(Project project) {
        ProjectManagerService.getInstance().updateProject(project);
    }

    @Override
    public void delete(Project project) {
        ProjectManagerService.getInstance().deleteProject(project);
    }
}
