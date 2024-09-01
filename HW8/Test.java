package HW8;

import java.io.Serializable;
import java.util.*;


/*
    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application creating our own Iterator which overrides the method
    hasNext(), next() and remove()
 */


class StorageIterator<T> implements Iterator<T> {


    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}

public class Test {
    public static void testInteger0(StorageInterface<Integer> aStorageInterfaceInteger) throws StorageHasBeenModifiedException	{
        System.out.println("Test 0");
        Iterator<Integer> aIterator =  aStorageInterfaceInteger.iterator();
        aStorageInterfaceInteger.add(null);
        aStorageInterfaceInteger.add(Integer.valueOf("2"));
        aStorageInterfaceInteger.add(Integer.valueOf("3"));
        aStorageInterfaceInteger.add(Integer.valueOf("4"));
        System.out.println("3: " + aStorageInterfaceInteger);
        while ( aIterator.hasNext() )
            System.out.println("in Test: " + aIterator.next());
        aStorageInterfaceInteger.add(Integer.valueOf("5"));
    }
    public static void testInteger1(StorageInterface<Integer> aStorageInterfaceInteger) throws StorageHasBeenModifiedException {
        System.out.println("Test 2");
        Iterator<Integer> aIterator =  aStorageInterfaceInteger.iterator();

        aStorageInterfaceInteger.add(Integer.valueOf("1"));
        aStorageInterfaceInteger.add(Integer.valueOf("2"));
        aStorageInterfaceInteger.add(Integer.valueOf("3"));
        aIterator.hasNext();
        aIterator.remove();
        System.out.println("in Test removed: " + aIterator.next());
        while ( aIterator.hasNext() )
            System.out.println("in Test: " + aIterator.next());
    }
    public static void testInteger2(StorageInterface<Integer> aStorageInterfaceInteger)	throws StorageHasBeenModifiedException{
        System.out.println("Test 1");
        Iterator<Integer> aIterator =  aStorageInterfaceInteger.iterator();

        aStorageInterfaceInteger.add(Integer.valueOf("1"));
        aStorageInterfaceInteger.add(Integer.valueOf("2"));
        aIterator.hasNext();
        aStorageInterfaceInteger.add(Integer.valueOf("3"));
        System.out.println("in Test: " + aIterator.next());
        System.out.println("This line should not be printed");
    }
    public static void main(String args[] )	{
        try {
            StorageInterface<Integer> aStorageInterfaceInteger = new SortedStorage<Integer>();
            testInteger0(aStorageInterfaceInteger);

            aStorageInterfaceInteger = new SortedStorage<Integer>();
            testInteger1(aStorageInterfaceInteger);

            aStorageInterfaceInteger = new SortedStorage<Integer>();
            testInteger2(aStorageInterfaceInteger);
        } catch ( StorageHasBeenModifiedException e )	{
            System.out.println(e);
        }
    }
}
