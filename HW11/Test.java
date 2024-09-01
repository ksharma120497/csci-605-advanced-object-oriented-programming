
import java.io.Serializable;
import java.util.*;


/*
    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application creating our own Iterator which overrides the method
    hasNext(), next() and remove() using multithreading
 */

class StorageHasBeenModifiedException extends Exception implements Serializable {

    private static final long serialVersionUID = 7861263817263L;


    StorageHasBeenModifiedException(String errorMessage){
        super(errorMessage);
    }
}


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

/*
    This is an interface which has all the methods and is implemented
    by both the storage classes
 */
interface StorageInterface<T> extends Iterable<T> {
    public boolean add(T x) throws StorageHasBeenModifiedException;

    Iterator<T> iterator();
}

/*
    This is the sorted storage which implements Comparable and SortedStorageInterface along with using generics
    This class implements all the methods of that interface and it can store different types of data such as
    Integer, String, SortedStorage, MusicStorageOfThePast and OldFashionedEmailAddress.
 */
class SortedStorage<T extends Comparable<T>> implements Comparable<SortedStorage<?>>, StorageInterface<T> {

    private List<T> sortedStorage;

    private static int nullCounter=0;

    boolean hasNextStarted = false;

    int index=0;

    SortedStorage(){
        sortedStorage = new ArrayList<>();
    }

    /*
       This function adds the elements in the storage system and is always sorted

       We first search the array for the right place to keep the element if it finds the
        right place that keeps the array in sorted order then we insert the element there
    */
    public synchronized boolean add(T x) throws StorageHasBeenModifiedException {
        if(x == null){
            nullCounter++;
            return true;
        }
        if(!sortedStorage.contains(x)) {
            if(hasNextStarted && !(index==sortedStorage.size()))
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
    public String toString(){
        String finalString = "";
        for(T elements: this.sortedStorage) {
            if (elements != null)
                finalString = finalString + " " + elements;
        }
        finalString = finalString + "\n- Includes so many null values = "+ nullCounter ;
        return "Values stored" + finalString;
    }

    @Override
    public Iterator<T> iterator() {

        Iterator<T> a = new StorageIterator<T>(){


            @Override
            public synchronized boolean hasNext() {
                hasNextStarted = true;
                return index < sortedStorage.size();
            }

            @Override
            public synchronized T next() {
                T current = sortedStorage.get(index);
                index++;
                return current;
            }

            @Override
            public synchronized void remove() {
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

public class Test extends Thread{

    String info;

    Test(String info){
        this.info = info;
    }
    public static void testInteger0(StorageInterface<Integer> aStorageInterfaceInteger) throws StorageHasBeenModifiedException {
        System.out.println("Test 0 ");
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
    public static void testInteger2(StorageInterface<Integer> aStorageInterfaceInteger)	throws StorageHasBeenModifiedException {
        System.out.println("Test 1");
        Iterator<Integer> aIterator =  aStorageInterfaceInteger.iterator();

        aStorageInterfaceInteger.add(Integer.valueOf("1"));
        aStorageInterfaceInteger.add(Integer.valueOf("2"));
        aIterator.hasNext();
        aStorageInterfaceInteger.add(Integer.valueOf("3"));
        System.out.println("in Test: " + aIterator.next());
        System.out.println("This line should not be printed");
    }

    public void run(){
        synchronized (this) {
            StorageInterface<Integer> aStorageInterfaceInteger = new SortedStorage<Integer>();
            System.out.println("Thread "+this.info);
            try {
                testInteger0(aStorageInterfaceInteger);
            } catch (StorageHasBeenModifiedException e) {
                throw new RuntimeException(e);
            }

            aStorageInterfaceInteger = new SortedStorage<Integer>();
            try {
                testInteger1(aStorageInterfaceInteger);
            } catch (StorageHasBeenModifiedException e) {
                throw new RuntimeException(e);
            }

            aStorageInterfaceInteger = new SortedStorage<Integer>();
            try {
                testInteger2(aStorageInterfaceInteger);
            } catch (StorageHasBeenModifiedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String args[] ) throws InterruptedException {
        Test t1 = new Test("1");
        t1.start();
        Test t2 = new Test("2");
        t2.start();

        t1.join();
        t2.join();

    }
}

