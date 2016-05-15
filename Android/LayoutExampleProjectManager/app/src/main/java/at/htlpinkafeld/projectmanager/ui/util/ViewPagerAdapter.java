package at.htlpinkafeld.projectmanager.ui.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import at.htlpinkafeld.projectmanager.ui.ActivitiesMainFragment;
import at.htlpinkafeld.projectmanager.ui.ProjectsMainFragment;
import at.htlpinkafeld.projectmanager.ui.TeamMembersMainFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    Fragment tabs[] = {
            new TeamMembersMainFragment(),
            new ProjectsMainFragment(),
//            new ActivityDetailsFragment()};
            new ActivitiesMainFragment()};

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        return tabs[position];

    }

    // This method return the titles for the Tabs in the FragmentTab Strip
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}