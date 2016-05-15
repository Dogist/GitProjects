package at.htlpinkafeld.intenttester;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ActivityList extends AppCompatActivity {

    private ListView lv;
    private ResolveInfo ri;
    private List<ResolveInfo> resL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);
        resL= getIntent().getParcelableArrayListExtra("resolvedList");
        lv= (ListView) findViewById(R.id.intentList);
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return resL.size();
            }

            @Override
            public Object getItem(int position) {
                return resL.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView t = new TextView(getBaseContext());
                t.setTextColor(Color.BLACK);
                t.setText(((ResolveInfo) getItem(position)).activityInfo.toString());
                return t;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ri =resL.get(position);
            }
        });
    }

    public void runIntent(View v){
        if(ri!=null) {
            Intent intent = new Intent();
            intent.setClassName(ri.activityInfo.applicationInfo.packageName, ri.activityInfo.name);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
