package at.htlpinkafeld.projectmanager.ui.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tq on 16-01-17.
 */
public class SelectionManager<T> {

    private Set<T> selectedItems = new HashSet<>();

    public void toggleSelection(T item) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item);
        } else {
            selectedItems.add(item);
        }
    }

    public boolean isSelected(T item) {
        return selectedItems.contains(item);
    }

    public Set<T> getSelectedItems() {
        return selectedItems;
    }

    public void clear() {
        selectedItems.clear();
    }

}
