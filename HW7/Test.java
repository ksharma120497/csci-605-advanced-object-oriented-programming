package HW7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application creates a storage functionality that stores data in a sorted manner

    It performs its insertion in the sorted manner so that the list is always sorted at any time

    There are other functions performed on it like addition, deletion, and finding of elements

    In the same storage system we should be now able to add objects of two new classes MusicStorageOfThePast
    and OldFashionedEmailAddress
 */

/*
    This is the class of MusicStorageOfThePast which implements Comparable interface so that whenever
    any function or classes does comparison between its two values it will compare it on the bases of
    the year.

    It implements the method compareTo of interface Comparable to do comparison between object of this class
 */

class MusicStorageOfThePast implements Comparable<MusicStorageOfThePast> {
    int year;
    String artist;
    String title;
    float length;
    int tracks;

    public MusicStorageOfThePast(int year, String artist, String title, float length, int tracks){
        this.year = year;;
        this.artist = artist;
        this.title = title;
        this.length = length;
        this.tracks = tracks;
    }

    public MusicStorageOfThePast() {

    }


    @Override
    public int compareTo(MusicStorageOfThePast o) {
        if(this.year == o.year) return 0;
        else if (this.year>o.year) return 1;
        else return -1;
    }
}
/*
    This is the class of OldFashionedEmailAddress which implements Comparable interface so that whenever
    any function or classes does comparison between its two values it will compare it on the bases of
    the houseNumber.

    It implements the method compareTo of interface Comparable to do comparison between object of this class
 */
class OldFashionedEmailAddress implements Comparable<OldFashionedEmailAddress>{
    int houseNumber;
    String streetName;
    String nameOfTown;
    String state;
    int zipCode;

    public OldFashionedEmailAddress(int houseNumber, String streetName, String nameOfTown, String state, int zipCode) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.nameOfTown = nameOfTown;
        this.state = state;
        this.zipCode = zipCode;
    }

    public OldFashionedEmailAddress() {

    }

    @Override
    public int compareTo(OldFashionedEmailAddress o) {
        if(this.houseNumber == o.houseNumber) return 0;
        else if (this.houseNumber>o.houseNumber) return 1;
        else return -1;
    }
}

/*
    This is an interface which has all the methods and is implemented
    by both the storage classes
 */
interface SortedStorageInterface<T>{
    public boolean add(T x);

    public boolean find(T x);

    public boolean delete(T x);

    public boolean includesNull();

}

/*
    This is the sorted storage which implements Comparable and SortedStorageInterface along with using generics
    This class implements all the methods of that interface and it can store different types of data such as
    Integer, String, SortedStorage, MusicStorageOfThePast and OldFashionedEmailAddress.
 */
class SortedStorage<T extends Comparable<T>> implements Comparable<SortedStorage<? extends Comparable>>,SortedStorageInterface<T> {

    private List<T> sortedStorage;

    private static int nullCounter=0;

    SortedStorage(){
        sortedStorage = new ArrayList();
    }

    /*
       This function adds the elements in the storage system and is always sorted

       We first search the array for the right place to keep the element if it finds the
        right place that keeps the array in sorted order then we insert the element there
    */
    public boolean add(T x){
        if(x == null){
            nullCounter++;
            return true;
        }
        if(!sortedStorage.contains(x)) {
            sortedStorage.add(x);
            Collections.sort(sortedStorage);
            return true;
        }
        return false;
    }

    /*
        This function checks whether the storage functionality has
        a null value or not

        @returns a boolean value
     */
    public boolean includesNull(){
        if (nullCounter>0) return true;
        return false;
    }

    /*
        This function overrides the toString function and defines its own logic
        to display the member variables of the class

        @returns string with all the elements in the array and also the count of the null values
     */
    public String toString(T x){

        String finalString = "";
        for(T elements: this.sortedStorage) {
            if(x instanceof MusicStorageOfThePast)
            {
                MusicStorageOfThePast musicStorageOfThePast = (MusicStorageOfThePast) elements;
                finalString = finalString + " " + "{year: "+musicStorageOfThePast.year +"}";
            }
            else if(x instanceof OldFashionedEmailAddress)
            {
                OldFashionedEmailAddress oldFashionedEmailAddress = (OldFashionedEmailAddress) elements;
                finalString = finalString + " " + "{houseNumber: "+oldFashionedEmailAddress.houseNumber +"}";
            }
            else if (elements != null)
                finalString = finalString + " " + elements;
        }

        finalString = finalString + "\n- Includes so many null values = "+ nullCounter ;
        return "Values stored" + finalString;
    }

     /*
        This function fins the given element in the storage data structure and
        returns true or false

        @params string to search in the data structure
     */
    public boolean find(T x){
        if(x == null && nullCounter>0)
           return true;
        if(sortedStorage.contains(x)) return true;
        return false;
    }

    /*
        This function deletes the element in the given storage
        It assigns that element as null

        @returns a boolean value after successful deletion
     */
    public boolean delete(T x){
        if(x == null && nullCounter>0){
            nullCounter--;
            return true;
        }
        else if(x!=null && sortedStorage.contains(x)){
            sortedStorage.remove(x);
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(SortedStorage<? extends Comparable> o) {
        return 0;
    }
}

public class Test {

    public static void testItWithObjects(SortedStorage aSortedStorage) {
        MusicStorageOfThePast musicStorageOfThePast1 = new MusicStorageOfThePast(1979,"Pink Floyd","Another Brick in the Wall", 3.58f, 10);
        MusicStorageOfThePast musicStorageOfThePast2 = new MusicStorageOfThePast(1973,"Pink Floyd","Brain Damage", 3.46f, 11);
        MusicStorageOfThePast toInsert[] = {musicStorageOfThePast1, null, musicStorageOfThePast2};
        MusicStorageOfThePast toDelete[] = {musicStorageOfThePast1, null, musicStorageOfThePast1};
        MusicStorageOfThePast toFind[] = {musicStorageOfThePast1, null, musicStorageOfThePast2};


        for (int index = 0; index < toInsert.length; index++) {
            System.out.println("- add(" + toInsert[index] + "): " + aSortedStorage.add(toInsert[index]));
        }

        System.out.println("- includesNull: " + aSortedStorage.includesNull());
        System.out.println("- toMusicStorageOfThePast: " + aSortedStorage.toString(new MusicStorageOfThePast()));

        for (int index = 0; index < toFind.length; index++) {
            System.out.println("- find(" + toFind[index] + "): " + aSortedStorage.find(toFind[index]));
        }
        for (int index = 0; index < toDelete.length; index++) {
            System.out.println("- delete(" + toDelete[index] + "): " + aSortedStorage.delete(toDelete[index]));
        }
        for (int index = 0; index < toDelete.length; index++) {
            System.out.println("- delete(" + toDelete[index] + "): " + aSortedStorage.delete(toDelete[index]));
        }
    }

    public static void testItWithObjectsTwo(SortedStorage aSortedStorage) {
        OldFashionedEmailAddress oldFashionedEmailAddress1 = new OldFashionedEmailAddress(60,"Clay Road","Monroe", "NY", 14623);
        OldFashionedEmailAddress oldFashionedEmailAddress2 = new OldFashionedEmailAddress(30,"Clay Road","Monroe", "NY", 14623);
        OldFashionedEmailAddress toInsert[] = {oldFashionedEmailAddress1, null, oldFashionedEmailAddress2};
        OldFashionedEmailAddress toDelete[] = {oldFashionedEmailAddress1, null, oldFashionedEmailAddress1};
        OldFashionedEmailAddress toFind[] = {oldFashionedEmailAddress1, null, oldFashionedEmailAddress2};


        for (int index = 0; index < toInsert.length; index++) {
            System.out.println("- add(" + toInsert[index] + "): " + aSortedStorage.add(toInsert[index]));
        }

        System.out.println("- includesNull: " + aSortedStorage.includesNull());
        System.out.println("- toOldFashionedEmailAddress: " + aSortedStorage.toString(new OldFashionedEmailAddress()));

        for (int index = 0; index < toFind.length; index++) {
            System.out.println("- find(" + toFind[index] + "): " + aSortedStorage.find(toFind[index]));
        }
        for (int index = 0; index < toDelete.length; index++) {
            System.out.println("- delete(" + toDelete[index] + "): " + aSortedStorage.delete(toDelete[index]));
        }
        for (int index = 0; index < toDelete.length; index++) {
            System.out.println("- delete(" + toDelete[index] + "): " + aSortedStorage.delete(toDelete[index]));
        }
    }
    public static void testItWithString(SortedStorage aSortedStorage) {

        String toInsert[] = {"8", null, "1", "2", "1", "1"};
        String toDelete[] = {"1", null, "1"};
        String toFind[] = {"1", null, "1"};


        for (int index = 0; index < toInsert.length; index++) {
            System.out.println("- add(" + toInsert[index] + "): " + aSortedStorage.add(toInsert[index]));
        }

        System.out.println("- includesNull: " + aSortedStorage.includesNull());
        System.out.println("- toString: " + aSortedStorage.toString(""));

        for (int index = 0; index < toFind.length; index++) {
            System.out.println("- find(" + toFind[index] + "): " + aSortedStorage.find(toFind[index]));
        }
        for (int index = 0; index < toDelete.length; index++) {
            System.out.println("- delete(" + toDelete[index] + "): " + aSortedStorage.delete(toDelete[index]));
        }
        for (int index = 0; index < toDelete.length; index++) {
            System.out.println("- delete(" + toDelete[index] + "): " + aSortedStorage.delete(toDelete[index]));
        }
    }

        public static void testItWithIntegers(SortedStorage aSortedStorage)	{

        Integer toInsert[] = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};
        Integer toDelete[] = {  Integer.valueOf(8), null,  Integer.valueOf(1) };
        Integer toFind[]   = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};


        for (int index = 0; index < toInsert.length; index ++ )	{
            System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
        }

        System.out.println("- includesNull: "  + aSortedStorage.includesNull());
        System.out.println("- toIntegers: "  + aSortedStorage.toString(""));

        for (int index = 0; index < toFind.length; index ++ )	{
            System.out.println("- find(" + toFind[index] + "): "  + aSortedStorage.find(toFind[index]));
        }
        for (int index = 0; index < toDelete.length; index ++ )	{
            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
        }
        for (int index = 0; index < toDelete.length; index ++ )	{
            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
        }
    }
    public static void testItWithSortedStorage(SortedStorage aSortedStorage,  SortedStorage[] theSortedStorages)	{
        SortedStorage toInsert[] = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1], theSortedStorages[0] };
        SortedStorage toDelete[] = {  theSortedStorages[0], theSortedStorages[1], null };
        SortedStorage toFind[]   = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1] };

        for (int index = 0; index < toInsert.length; index ++ )	{
            System.out.println("- add(" + index + "): "  + aSortedStorage.add(toInsert[index]));
        }

        System.out.println("- includesNull: "  + aSortedStorage.includesNull());
        System.out.println("- toSortedStorage: "  + aSortedStorage.toString(""));

        for (int index = 0; index < toFind.length; index ++ )	{
            System.out.println("- find(" + toFind[index] + "): "  + aSortedStorage.find(toFind[index]));
        }
        for (int index = 0; index < toDelete.length; index ++ )	{
            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
        }
        for (int index = 0; index < toDelete.length; index ++ )	{
            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
        }
       }
    public void test() {
        SortedStorage<MusicStorageOfThePast> aSortedObjectStorage = new SortedStorage();
        testItWithObjects(aSortedObjectStorage);

        SortedStorage<OldFashionedEmailAddress> aSortedObjectStorageTwo = new SortedStorage();
        testItWithObjectsTwo(aSortedObjectStorageTwo);

        SortedStorage<String> aSortedStringStorage = new SortedStorage();
        testItWithString(aSortedStringStorage);

        SortedStorage<Integer> aSortedIntegerStorage = new SortedStorage();
        testItWithIntegers(aSortedIntegerStorage);

        SortedStorage<SortedStorage<? extends Comparable>> aSortedSortedStorageStorage = new SortedStorage();
        SortedStorage[] theSortedStorages = { aSortedStringStorage, aSortedIntegerStorage, aSortedSortedStorageStorage };
        testItWithSortedStorage(aSortedSortedStorageStorage, theSortedStorages);
    }

    public static void main(String args[]) {
        new Test().test();
    }
}
