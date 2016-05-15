package at.htlpinkafeld.projectmanager.ui.util;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ArrayAdapterWithHint<E> extends ArrayAdapter<E> {

    public ArrayAdapterWithHint(Context theContext, int theLayoutResId, E[] objects) {
        super(theContext, theLayoutResId, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}