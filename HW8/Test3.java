package HW8;

import java.util.*;

/*
    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application implements MyList, MyComparator and MyCollections where we have to implement
    our own Collections sorting function
 */
class MyList<T extends Comparable<T>> implements List<T>, Comparable<MyList<?>> {

    List<T> sortedStorage;

     public MyList(){
        this.sortedStorage = new ArrayList<>();
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T x){
        if(x == null){
            return true;
        }
        sortedStorage.add(x);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }


    public String toString(){
         String totalString="";
         int count = 0;
         for(T elements: sortedStorage){
             count++;
             totalString+=elements+" ";
         }
        return "MyList[ "+ totalString +"]/"+count;
    }

    @Override
    public int compareTo(MyList<?> o) {
        return 0;
    }
}

/*
    This class implements a Comparator class and overrides the compare method
 */
class MyComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return -o1.compareTo(o2);
    }
}


/*
    This class has two sort functions specific to the requirement
 */

class MyCollections<T> {
    public static void sort(MyList<String> aList) {
          Collections.sort(aList.sortedStorage);
    }

    public static void sort(MyList<String> aList, Comparator<String> myComparator) {
        Collections.sort(aList.sortedStorage, myComparator);
    }
}

public class Test3 {
    MyList<String> aList = new MyList<String>();
    public void init()	{
        String[] theWords = { "a", "c", "b" };
        for ( int index = 0; index < theWords.length; index ++ )
            aList.add(theWords[index]);
    }
    public void testNatural()	{
        System.out.println("natural before sort - aList: " + aList );
        MyCollections.sort(aList);
        System.out.println("natural after  sort - aList: " + aList );
    }
    public void testReverse()	{
        System.out.println("reverse aList: " + aList );
        Comparator<String> myComparator = new MyComparator();
        MyCollections.sort(aList, myComparator);
        System.out.println("reverse aList: " + aList );
    }
    public static void main(String args[]) {
        Test3 natural = new Test3();
        natural.init();
        natural.testNatural();
        Test3 reverse = new Test3();
        reverse.init();
        reverse.testReverse();
    }

}
