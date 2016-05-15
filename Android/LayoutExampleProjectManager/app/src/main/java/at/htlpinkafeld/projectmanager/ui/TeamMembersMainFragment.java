package at.htlpinkafeld.projectmanager.ui;

import at.htlpinkafeld.projectmanager.pojo.TeamMember;
import at.htlpinkafeld.projectmanager.service.ProjectManagerService;

/**
 * Created by tq on 16-01-09.
 */
public class TeamMembersMainFragment extends BaseMainFragment<BaseListFragment, TeamMembersDetailsFragment>
        implements TeamMembersDetailsFragment.OnMemberCreatedListener, TeamMembersDetailsFragment.OnMemberChangedListener,
        TeamMembersDetailsFragment.OnMemberDeletedListener, BaseListFragment.OnListItemSelected<TeamMember> {

    public TeamMembersMainFragment() {
        super(new TeamMembersListFragment(), new TeamMembersDetailsFragment());
    }

    @Override
    public void onItemSelected(TeamMember m) {
        getDetailsViewFragment().showMember(m);
    }

    @Override
    public void OnMemberCreated(TeamMember m) {
        ProjectManagerService.getInstance().addTeamMember(m);
        getListViewFragment().updateView();
        getListViewFragment().setSelection(ProjectManagerService.getInstance().getTeamMembers().indexOf(m));
    }

    @Override
    public void onMemberChanged(TeamMember m) {
        ProjectManagerService.getInstance().updateTeamMember(m);
        getListViewFragment().updateView();
    }

    @Override
    public void onMemberDeleted(TeamMember m) {
        int oldIndex = ProjectManagerService.getInstance().getTeamMembers().indexOf(m);
        ProjectManagerService.getInstance().deleteTeamMember(m);
        getListViewFragment().updateView();
        if (ProjectManagerService.getInstance().getTeamMembers().size() > 0) {
            if (oldIndex > 0) {
                getListViewFragment().setSelection(oldIndex - 1);
            } else {
                getListViewFragment().setSelection(0);
            }
        }
    }
}
