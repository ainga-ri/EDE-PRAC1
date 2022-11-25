package uoc.ds.pr.util;

import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.adt.sequential.DictionaryArrayImpl;
import edu.uoc.ds.exceptions.FullContainerException;

public class DictionaryOrderedVector<K, V> extends DictionaryArrayImpl<K, V> {
    public DictionaryOrderedVector(int n) {
        super(n);
    }
    @Override
    public void put(K key, V value) {
        if (this.isFull()) {
            throw new FullContainerException();
        } else {
            int i = this.n;
            this.dictionary[i] = new KeyValue(key, value);
            ++this.n;
        }
    }
}
