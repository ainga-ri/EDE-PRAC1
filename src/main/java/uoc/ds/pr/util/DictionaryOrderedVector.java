package uoc.ds.pr.util;

import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.adt.sequential.DictionaryArrayImpl;
import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.exceptions.FullContainerException;

import java.util.Comparator;

public class DictionaryOrderedVector<K, V> extends DictionaryArrayImpl<K, V> implements FiniteContainer<V> {

    private Comparator<V> comparator;
    public  DictionaryOrderedVector(int n, Comparator<V> comparator) {
        super(n);
        this.comparator = comparator;
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

    @Override
    public V get(K key) {
        return super.get(key);
    }
}
