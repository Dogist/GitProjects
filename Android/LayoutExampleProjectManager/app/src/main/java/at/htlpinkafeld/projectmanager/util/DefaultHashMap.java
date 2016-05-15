package at.htlpinkafeld.projectmanager.util;

import java.util.HashMap;

/**
 * Created by tq on 16-01-25.
 * Stolen from http://stackoverflow.com/a/7519422/349776
 */
public abstract class DefaultHashMap<K, V> extends HashMap<K, V> {

    public abstract V getDefaultValue();

    @Override
    public V get(Object k) {
        if (!containsKey(k)) {
            put((K) k, getDefaultValue());
        }
        return super.get(k);

    }
}