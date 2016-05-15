package at.htlpinkafeld.intenttester;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mimeT_text;
    private EditText action_text;
    private EditText data_text;
    private EditText cat1_text;
    private EditText cat2_text;
    private EditText cat3_text;
    private EditText cat4_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mimeT_text = (EditText) findViewById(R.id.mimeT_text);
        action_text = (EditText) findViewById(R.id.action_text);
        data_text = (EditText) findViewById(R.id.data_text);
        cat1_text = (EditText) findViewById(R.id.category1_text);
        cat2_text = (EditText) findViewById(R.id.category2_text);
        cat3_text = (EditText) findViewById(R.id.category3_text);
        cat4_text = (EditText) findViewById(R.id.category4_text);

    }

    public void testIntent(View v){
        Intent testIntent=new Intent();
        testIntent.setAction(action_text.getText().toString());
        if(data_text.getText().length()!=0&&mimeT_text.getText().length()!=0)
            testIntent.setDataAndType(Uri.parse(data_text.getText().toString()), mimeT_text.getText().toString());
        else if(data_text.getText().length()!=0)
            testIntent.setData(Uri.parse(data_text.getText().toString()));
        else if(mimeT_text.getText().length()!=0)
            testIntent.setType(mimeT_text.getText().toString());
        if(cat1_text.getText().length()!=0)
            testIntent.addCategory(cat1_text.getText().toString());
        if(cat2_text.getText().length()!=0)
            testIntent.addCategory(cat2_text.getText().toString());
        if(cat3_text.getText().length()!=0)
            testIntent.addCategory(cat3_text.getText().toString());
        if(cat4_text.getText().length()!=0)
            testIntent.addCategory(cat4_text.getText().toString());
        PackageManager packageManager = getPackageManager();
        ArrayList<ResolveInfo> list = new ArrayList<>(packageManager.queryIntentActivities(testIntent, 0));
        Intent intent =new Intent(this, ActivityList.class);
        intent.putParcelableArrayListExtra("resolvedList", list);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
