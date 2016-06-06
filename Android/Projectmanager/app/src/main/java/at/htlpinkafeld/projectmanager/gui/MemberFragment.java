package at.htlpinkafeld.projectmanager.gui;


import android.support.v4.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import at.htlpinkafeld.projectmanager.R;
import at.htlpinkafeld.projectmanager.pojo.TeamMember;
import at.htlpinkafeld.projectmanager.service.ServiceClass;

/**
 * Created by User on 14.12.2015.
 */
public class MemberFragment extends Fragment implements ListToDetailListener {

    private ActionMode mActionMode;
    private View memDetFrag;
    private ListFragment memListFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.member_fragment, container, false);

        memDetFrag=view.findViewById(R.id.memDetail_frag);

//        View memListFrag = view.findViewById(R.id.memList_frag);
//        memListFrag.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mActionMode != null) {
//                    return false;
//                }
//
//                mActionMode = getActivity().startActionMode(new ActionBarCallBack());
//                view.setSelected(true);
//                return true;
//            }
//        });
        memDetFrag.findViewById(R.id.m_load_sample_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new LoadSampleMembersTask().execute("http://wewewe.xyz/tmp/sample_members.json");
            }
        });
        memListFrag=(ListFragment) getChildFragmentManager().findFragmentById(R.id.memList_frag);

        return view;
    }

    @Override
    public void onListItemClick(ListView view, int position) {
        MemberDetailFragment.mapObjectToView((TeamMember) view.getAdapter().getItem(position), memDetFrag);
    }

    class ActionBarCallBack implements ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Item Selected");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.newM : memDetFrag.findViewById(R.id.m_newB).callOnClick();
                    break;
                case R.id.saveM : memDetFrag.findViewById(R.id.m_saveB).callOnClick();
                    break;
                case R.id.deleteM : memDetFrag.findViewById(R.id.m_deleteB).callOnClick();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode=null;
        }
    }

    public class LoadSampleMembersTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                JSONArray jsonArray = new JSONArray(readWebsite(params[0]));
                for(int i=0, length =jsonArray.length();i<length;i++){
                    TeamMember mem=new TeamMember(jsonArray.getJSONObject(i));
                    ServiceClass.getServiceClass().addMember(mem);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ((BaseAdapter)memListFrag.getListAdapter()).notifyDataSetChanged();
        }

        private String readWebsite(String urlString) {
            InputStream is = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = null;

                conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is);

                return contentAsString;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        private String readIt(InputStream stream) throws IOException {
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(stream));
            String res="", help;
            while((help=reader.readLine())!=null) {
                res+=help;
            }
            return res;
        }
    }
}
