package HW8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
    This is the sorted storage which implements Comparable and SortedStorageInterface along with using generics
    This class implements all the methods of that interface and it can store different types of data such as
    Integer, String, SortedStorage, MusicStorageOfThePast and OldFashionedEmailAddress.
 */
public class SortedStorage<T extends Comparable<T>> implements Comparable<SortedStorage<?>>, StorageInterface<T> {

    private List<T> sortedStorage;

    private static int nullCounter = 0;

    boolean hasNextStarted = false;

    int index = 0;

    SortedStorage() {
        sortedStorage = new ArrayList<>();
    }

    /*
       This function adds the elements in the storage system and is always sorted

       We first search the array for the right place to keep the element if it finds the
        right place that keeps the array in sorted order then we insert the element there
    */
    public boolean add(T x) throws StorageHasBeenModifiedException {
        if (x == null) {
            nullCounter++;
            return true;
        }
        if (!sortedStorage.contains(x)) {
            if (hasNextStarted && !(index == sortedStorage.size()))
                throw new StorageHasBeenModifiedException("StorageHasBeenModifiedException - storage has been modified");
            sortedStorage.add(x);
            Collections.sort(sortedStorage);
            return true;
        }
        throw new StorageHasBeenModifiedException("StorageHasBeenModifiedException - storage has been modified");
    }

    /*
        This function overrides the toString function and defines its own logic
        to display the member variables of the class

        @returns string with all the elements in the array and also the count of the null values
     */
    public String toString() {
        String finalString = "";
        for (T elements : this.sortedStorage) {
            if (elements != null)
                finalString = finalString + " " + elements;
        }
        finalString = finalString + "\n- Includes so many null values = " + nullCounter;
        return "Values stored" + finalString;
    }

    @Override
    public Iterator<T> iterator() {

        Iterator<T> a = new StorageIterator<T>() {


            @Override
            public boolean hasNext() {
                hasNextStarted = true;
                return index < sortedStorage.size();
            }

            @Override
            public T next() {
                T current = sortedStorage.get(index);
                index++;
                return current;
            }

            @Override
            public void remove() {
                sortedStorage.remove(index);
            }
        };

        return a;
    }

    @Override
    public int compareTo(SortedStorage<?> o) {
        return 0;
    }
}
