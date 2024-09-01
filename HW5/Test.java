//package HW5;
//
///*
//
//    @Author Kapil Sharma ks4643
//    @Author Chetan Chandane cc5831
//
//    This application creates a storage functionality that stores data in a sorted manner
//
//    It performs its insertion in the sorted manner so that the list is always sorted at any time
//
//    There are other functions performed on it like addition, deletion, and finding of elements
//
// */
//
//interface SortedStorageInterface<T>{
//    public boolean add(Integer x);
//    public boolean add(String x);
//    public boolean add(SortedStorage x);
//
//    public boolean find(T x);
//
//    public boolean delete(String x);
//
//    public boolean delete(Integer x);
//
//    public boolean delete(SortedStorage x);
//
//    public boolean includesNull();
//
//}
//
//
//class SortedStorage<T> implements SortedStorageInterface<T>{
//
//    private static int count = 0;
//
//    private static int countInteger = 0;
//
//    private static int nullCounter = 0;
//    private T[] storageArray;
//
////    private Integer[] storageArrayInteger = new Integer[1031];
////
////    private SortedStorage[] sortedStorageArray = new SortedStorage[1031];
//
//
//
//
//    /*
//        This function overrides the toString function and defines its own logic
//        to display the member variables of the class
//
//        @returns string with all the elements in the array and also the count of the null values
//     */
//
//    public String toString(){
//        String finalString = "";
//
//        for(Object elements: this.storageArrayInteger)
//            if(elements!=null)
//                finalString = finalString + " " + elements;
//
//        finalString = finalString + "\nIncludes so many null values = " + nullCounter;
//        return "Values stored" + finalString;
//    }
//
//    /*
//        This function adds the elements in the storage system and is always sorted
//
//        We first search the array for the right place to keep the element if it finds the
//        the right place that keeps the array in sorted order then we insert the element there
//     */
//
//    public boolean add(Object x){
//        if(count == 0){
//            storageArray[0] = (String)x;
//            count++;
//            return true;
//        }
//        for(int i=0; i<count; i++) {
//            if (x != null && storageArray[i]!=null)  {
//                if( x instanceof String || x instanceof Integer)
//
//                if (String.valueOf(x)<=String.valueOf(storageArray[i])) {
//                    storageArrayInteger[i + 1] = storageArrayInteger[i];
//                    storageArrayInteger[i] = x;
//                    storageArrayInteger[count] = null;
//                    count++;
//                    return true;
//                }
//            }
//            else{
//                storageArrayInteger[count]= null;
//                nullCounter++;
//                count++;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean add(String x){
//        if(count == 0){
//            storageArray[0] = x;
//            count++;
//            return true;
//        }
//        for(int i=0; i<count; i++) {
//            if (x != null && storageArray[i]!=null)  {
//                if (Integer.parseInt(x) <= Integer.parseInt(storageArray[i])) {
//                    storageArray[i + 1] = storageArray[i];
//                    storageArray[i] = x;
//                    storageArray[count] = null;
//                    count++;
//                    return true;
//                }
//            }
//            else{
//                storageArray[count]= null;
//                nullCounter++;
//                count++;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean add(SortedStorage x){
//        if(count == 0){
//            sortedStorageArray[0] = x;
//            count++;
//            return true;
//        }
//        for(int i=0; i<count; i++) {
//            if (x != null && sortedStorageArray[i]!=null)  {
//                if (x.equals(sortedStorageArray[i])) {
//                    sortedStorageArray[i + 1] = sortedStorageArray[i];
//                    sortedStorageArray[i] = x;
//                    sortedStorageArray[count] = null;
//                    count++;
//                    return true;
//                }
//            }
//            else{
//                sortedStorageArray[count]= null;
//                nullCounter++;
//                count++;
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    /*
//        This function fins the given element in the storage data structure and
//        returns true or false
//
//        @params string to search in the data structure
//     */
//    public boolean find(T x){
//        for(String element: storageArray)
//            if(element == x)
//                return true;
//        return false;
//    }
//
//    /*
//        This function checks whether the storage functionality has
//        a null value or not
//
//        @returns a boolean value
//     */
//    public boolean includesNull(){
//        for(String element: storageArray)
//            if(element == null)
//                return true;
//        return false;
//    }
//
//
//    /*
//        This function deletes the element in the given storage
//        It assigns that element as null
//
//        @returns a boolean value after successful deletion
//     */
//    public boolean delete(String x){
//        if (x == null) {
//            for (int i = 0; i < storageArray.length; i++) {
//                if (storageArray[i] == "*" || storageArray[i]==null){
//                    storageArray[i] = null;
//                    return true;
//                }
//            }
//        }
//        else
//            for(int i=0; i<storageArray.length;i++) {
//                if (storageArray[i] == x) {
//                    storageArray[i] = null;
//                    return true;
//                }
//            }
//        return false;
//    }
//
//    public boolean delete(Integer x){
//        if (x == null) {
//            for (int i = 0; i < storageArrayInteger.length; i++) {
//                if (storageArrayInteger[i]==null){
//                    storageArrayInteger[i] = null;
//                    return true;
//                }
//            }
//        }
//        else
//            for(int i=0; i<storageArrayInteger.length;i++) {
//                if (storageArrayInteger[i] == x) {
//                    storageArrayInteger[i] = null;
//                    return true;
//                }
//            }
//        return false;
//    }
//
//    public boolean delete(SortedStorage x){
//        if (x == null) {
//            for (int i = 0; i < sortedStorageArray.length; i++) {
//                if (sortedStorageArray[i]==null){
//                    sortedStorageArray[i] = null;
//                    return true;
//                }
//            }
//        }
//        else
//            for(int i=0; i<sortedStorageArray.length;i++) {
//                if (sortedStorageArray[i] == x) {
//                    sortedStorageArray[i] = null;
//                    return true;
//                }
//            }
//        return false;
//    }
//}
//
//
//public class Test {
//    public static void testItWithString(SortedStorage aSortedStorage)	{
//
//        String toInsert[] = { "8", null, "1", "2"};
//        String toDelete[] = { "1", null, "1"};
//        String toFind[]   = { "1", null, "1"};
//
//
//        for (int index = 0; index < toInsert.length; index ++ )	{
//            System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
//        }
//
//        System.out.println("- includesNull: "  + aSortedStorage.includesNull());
//        System.out.println("- toString: "  + aSortedStorage.toString());
//
//        for (int index = 0; index < toFind.length; index ++ )	{
//            System.out.println("- find(" + toFind[index] + "): "  + aSortedStorage.find(toFind[index]));
//        }
//        for (int index = 0; index < toDelete.length; index ++ )	{
//            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
//        }
//        for (int index = 0; index < toDelete.length; index ++ )	{
//            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
//        }
//    }
//    public static void testItWithIntegers(SortedStorage aSortedStorage)	{
//
//        Integer toInsert[] = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};
//        Integer toDelete[] = {  Integer.valueOf(8), null,  Integer.valueOf(1) };
//        Integer toFind[]   = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};
//
//
//        for (int index = 0; index < toInsert.length; index ++ )	{
//            System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
//        }
//
//        System.out.println("- includesNull: "  + aSortedStorage.includesNull());
//        System.out.println("- toIntegers: "  + aSortedStorage.toString());
//
//        for (int index = 0; index < toFind.length; index ++ )	{
//            System.out.println("- find(" + toFind[index] + "): "  + aSortedStorage.find(toFind[index]));
//        }
//        for (int index = 0; index < toDelete.length; index ++ )	{
//            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
//        }
//        for (int index = 0; index < toDelete.length; index ++ )	{
//            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
//        }
//    }
//    public static void testItWithSortedStorage(SortedStorage aSortedStorage,  SortedStorage[] theSortedStorages)	{
//        SortedStorage toInsert[] = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1] };
//        SortedStorage toDelete[] = {  theSortedStorages[0], theSortedStorages[1], null };
//        SortedStorage toFind[]   = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1] };
//
//        for (int index = 0; index < toInsert.length; index ++ )	{
//            System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
//        }
//
//        System.out.println("- includesNull: "  + aSortedStorage.includesNull());
//        System.out.println("- toIntegers: "  + aSortedStorage.toString());
//
//        for (int index = 0; index < toFind.length; index ++ )	{
//            System.out.println("- find(" + toFind[index] + "): "  + aSortedStorage.find(toFind[index]));
//        }
//        for (int index = 0; index < toDelete.length; index ++ )	{
//            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
//        }
//        for (int index = 0; index < toDelete.length; index ++ )	{
//            System.out.println("- delete(" + toDelete[index] + "): "  + aSortedStorage.delete(toDelete[index]));
//        }
//    }
//    public void test()	{
//        SortedStorage aSortedStringStorage = new SortedStorage();
//        testItWithString(aSortedStringStorage);
//
//        SortedStorage aSortedIntegerStorage = new SortedStorage();
//        testItWithIntegers(aSortedIntegerStorage);
//
//        SortedStorage aSortedSortedStorageStorage = new SortedStorage();
//        SortedStorage[] theSortedStorages = { aSortedStringStorage, aSortedIntegerStorage, aSortedSortedStorageStorage };
//        testItWithSortedStorage(aSortedSortedStorageStorage, theSortedStorages);
//    }
//    public static void main(String args[] )	{
//        new Test().test();
//    }
//}
//
//
