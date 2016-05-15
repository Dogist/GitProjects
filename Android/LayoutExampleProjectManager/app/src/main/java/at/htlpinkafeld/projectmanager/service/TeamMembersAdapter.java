package at.htlpinkafeld.projectmanager.service;

import java.util.List;

import at.htlpinkafeld.projectmanager.pojo.TeamMember;

/**
 * Created by tq on 16-01-09.
 */
public class TeamMembersAdapter implements EntityServiceAdapter<TeamMember> {
    @Override
    public List<TeamMember> getList() {
        return ProjectManagerService.getInstance().getTeamMembers();
    }

    @Override
    public TeamMember get(int id) {
        return ProjectManagerService.getInstance().getMember(id);
    }

    @Override
    public void add(TeamMember teamMember) {
        ProjectManagerService.getInstance().addTeamMember(teamMember);
    }

    @Override
    public void update(TeamMember teamMember) {
        ProjectManagerService.getInstance().updateTeamMember(teamMember);
    }

    @Override
    public void delete(TeamMember teamMember) {
        ProjectManagerService.getInstance().deleteTeamMember(teamMember);
    }
}
