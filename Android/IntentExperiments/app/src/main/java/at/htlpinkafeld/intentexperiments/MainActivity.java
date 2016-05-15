package at.htlpinkafeld.intentexperiments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int SELECT_IMAGE=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startHomescreenAct(View v){
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Always use string resources for UI text.
        // This says something like "Share this video with"
        String title = getResources().getString(R.string.chooser_title);
        // Create intent to show the chooser dialog
        Intent chooser = Intent.createChooser(intent, title);
        if (intent.resolveActivity(getPackageManager()) != null)
            this.startActivity(chooser);
    }

    public void showPictures(View v){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("image/*");
        try {
            this.startActivityForResult(intent, SELECT_IMAGE);
        } catch (ActivityNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void callNeighbour(View v){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("dial:06646499825"));
        startActivity(callIntent);
    }

    public void selectLocationGoogle(View v){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.17953321,16.42642736?z=15"));

        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE)
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                Intent intent = new Intent(Intent.ACTION_SEND, selectedImage);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM,selectedImage);
                startActivity(intent);
            }
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
