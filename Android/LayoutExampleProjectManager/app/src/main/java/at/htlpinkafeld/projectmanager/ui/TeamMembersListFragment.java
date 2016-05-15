package at.htlpinkafeld.projectmanager.ui;

import at.htlpinkafeld.projectmanager.pojo.TeamMember;
import at.htlpinkafeld.projectmanager.service.TeamMembersAdapter;

/**
 * Created by tq on 16-01-09.
 */
public class TeamMembersListFragment extends BaseListFragment<TeamMember> {

    public TeamMembersListFragment() {
        super(new TeamMembersAdapter());
    }

}
