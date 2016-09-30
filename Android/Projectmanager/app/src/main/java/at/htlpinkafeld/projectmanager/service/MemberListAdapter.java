package at.htlpinkafeld.projectmanager.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;


/**
 * Created by User on 30.11.2015.
 */
public class MemberListAdapter extends BaseAdapter implements ListAdapter {


    private final Context context;
    private ServiceClass sc;
    public MemberListAdapter(Context context) {
        this.sc = ServiceClass.getServiceClass();
        this.context = context;
    }

    @Override
    public int getCount() {
        return sc.sizeM();
    }

    @Override
    public TeamMember getItem(int position) {
        return sc.getMember(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView memView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.member_list_entry, parent, false);
            memView = (TextView) convertView.findViewById(R.id.memberlist_item);
            convertView.setTag(new ViewHolder(memView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        TeamMember m = getItem((int) getItemId(position));
        memView = viewHolder.memV;
        memView.setText(m.getTitle() + " " + m.getFname() + " " + m.getLname());

        return convertView;
    }

    private static class ViewHolder {
        public final TextView memV;

        private ViewHolder(TextView memV) {
            this.memV = memV;
        }
    }


}
