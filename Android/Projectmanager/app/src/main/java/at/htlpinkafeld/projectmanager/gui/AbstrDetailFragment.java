package at.htlpinkafeld.projectmanager.gui;

import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import at.htlpinkafeld.projectmanager.R;


/**
 * Created by User on 15.02.2016.
 */
public abstract class AbstrDetailFragment extends Fragment {

    protected static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    protected View.OnClickListener newLis;
    protected View.OnClickListener saveLis;
    protected View.OnClickListener delLis;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.newM:
                newLis.onClick(this.getView());
                break;
            case R.id.saveM:
                saveLis.onClick(this.getView());
                break;
            case R.id.deleteM:
                delLis.onClick(this.getView());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
