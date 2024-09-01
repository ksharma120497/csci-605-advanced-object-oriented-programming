package HW8;

import java.util.Iterator;

/*
    This is an interface which has all the methods and is implemented
    by both the storage classes
 */
public interface StorageInterface<T> extends Iterable<T> {
    public boolean add(T x) throws StorageHasBeenModifiedException;

    Iterator<T> iterator();
}
