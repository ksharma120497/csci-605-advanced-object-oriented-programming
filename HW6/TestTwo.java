
package HW11;

/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application creates a storage functionality that stores data in a sorted manner

    It performs its insertion in the sorted manner so that the list is always sorted at any time

    There are other functions performed on it like addition, deletion, and finding of elements

    There is another storage system that can store duplicate values as well

    Both the storage system implements SortedStorageInterface interface and they both are
    derived from their parent class
 */

/*
    This acts as a parent class for both the storage system
 */

class StorageParent<T>{
    StorageParent(){
    }

    public boolean add(T x) {
        return false;
    }

    public boolean find(T x) {
        return false;
    }

    public boolean delete(T x) {
        return false;
    }

    public boolean includesNull() {
        return false;
    }

    public String toString(String parentStringName){
        return "";
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

class SortedStorageSecond<T> extends StorageParent<T> implements SortedStorageInterface<T>{

    private int count = 0;

    private int nullCounter = 0;

    private T[] storageArray;

    SortedStorageSecond(T[] x){
        this.storageArray = x;
    }

    /*
       This function adds the elements in the storage system and is always sorted

       We first search the array for the right place to keep the element if it finds the
       the right place that keeps the array in sorted order then we insert the element there
    */
    public boolean add(T x){
        if(x==null){
            nullCounter++;
            return false;
        }
        if (count == 0) {
            this.storageArray[0] = x;
            count++;
            return true;
        }
        for (int i = 0; i < count; i++) {
            String dataTypeChangeString = "";
            Integer dataTypeChangeInteger = 0;
            SortedStorage dataTypeChangeSortedStorage;
            if (x instanceof String) {
                String storageArrayElement = (String) this.storageArray[i];
                dataTypeChangeString = (String) x;
                if (Integer.parseInt(storageArrayElement) > Integer.parseInt(dataTypeChangeString)) {
                    storageArray[i + 1] = storageArray[i];
                    storageArray[i] = x;
                    count++;
                    return true;
                }
            }
            if (x instanceof Integer) {
                Integer storageArrayElement = (Integer) this.storageArray[i];
                dataTypeChangeInteger = (Integer) x;
                if (storageArrayElement > dataTypeChangeInteger) {
                    storageArray[i + 1] = storageArray[i];
                    storageArray[i] = x;
                    count++;
                    return true;
                }
            }
            if (x instanceof SortedStorage) {
                SortedStorage storageArrayElement = (SortedStorage) this.storageArray[i];
                dataTypeChangeSortedStorage = (SortedStorage) x;
                if (storageArrayElement.hashCode() < dataTypeChangeSortedStorage.hashCode()) {
                    storageArray[i + 1] = storageArray[i];
                    storageArray[i] = x;
                    count++;
                    return true;
                }
            }

        }
        return false;
    }

    /*
        This function overrides the toString function and defines its own logic
        to display the member variables of the class

        @returns string with all the elements in the array and also the count of the null values
     */
    public String toString(String printStatementsFrom){
        String finalString = "";

        for(T elements: this.storageArray)
            if(elements!=null)
                finalString = finalString + " " + elements;

        finalString = finalString + "\n- Includes so many null values = " + nullCounter;
        return "Values stored" + finalString;
    }

    /*
        This function checks whether the storage functionality has
        a null value or not

        @returns a boolean value
     */
    public boolean includesNull(){
        if (nullCounter > 0) return true;
        return false;
    }

    /*
        This function deletes the element in the given storage
        It assigns that element as null

        @returns a boolean value after successful deletion
     */
    public boolean delete(T x){
        if(x == null && nullCounter>0) {
            nullCounter--;
            return true;
        }
        else if(x!=null){
            for (int i = 0; i < storageArray.length; i++) {
                if (storageArray[i] == x){
                    storageArray[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /*
        This function fins the given element in the storage data structure and
        returns true or false

        @params string to search in the data structure
     */
    public boolean find(T x){
        for(T element: this.storageArray )
            if(element == x)
                return true;
        return false;
    }
}


class SortedStorage<T> extends StorageParent<T> implements SortedStorageInterface<T>{

    private int count = 0;

    private int nullCounter = 0;

    private T[] storageArray;

    SortedStorage(T[] x){
        this.storageArray = x;
    }

    /*
        This function adds the elements in the storage system and is always sorted

        We first search the array for the right place to keep the element if it finds the
        the right place that keeps the array in sorted order then we insert the element there
     */
    public boolean add(T x){
        if(x==null){
            nullCounter++;
            return false;
        }
        if(!find(x)) {
            if (count == 0) {
                this.storageArray[0] = x;
                count++;
                return true;
            }
            for (int i = 0; i < count; i++) {
                String dataTypeChangeString = "";
                Integer dataTypeChangeInteger = 0;
                SortedStorage dataTypeChangeSortedStorage;
                if (x instanceof String) {
                    String storageArrayElement = (String) this.storageArray[i];
                    dataTypeChangeString = (String) x;
                    if (Integer.parseInt(storageArrayElement) > Integer.parseInt(dataTypeChangeString)) {
                        storageArray[i + 1] = storageArray[i];
                        storageArray[i] = x;
                        count++;
                        return true;
                    }
                }
                if (x instanceof Integer) {
                    Integer storageArrayElement = (Integer) this.storageArray[i];
                    dataTypeChangeInteger = (Integer) x;
                    if (storageArrayElement > dataTypeChangeInteger) {
                        storageArray[i + 1] = storageArray[i];
                        storageArray[i] = x;
                        count++;
                        return true;
                    }
                }
                if (x instanceof SortedStorage) {
                    SortedStorage storageArrayElement = (SortedStorage) this.storageArray[i];
                    dataTypeChangeSortedStorage = (SortedStorage) x;
                    if (storageArrayElement.hashCode() < dataTypeChangeSortedStorage.hashCode()) {
                        storageArray[i + 1] = storageArray[i];
                        storageArray[i] = x;
                        count++;
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /*
        This function overrides the toString function and defines its own logic
        to display the member variables of the class

        @returns string with all the elements in the array and also the count of the null values
     */
    public String toString(String printStatementsFrom){
        String finalString = "";

        for(T elements: this.storageArray)
            if(elements!=null)
                finalString = finalString + " " + elements;

        finalString = finalString + "\n- Includes so many null values = " + nullCounter;
        return "Values stored" + finalString;
    }

    /*
        This function checks whether the storage functionality has
        a null value or not

        @returns a boolean value
     */
    public boolean includesNull(){
        if (nullCounter > 0) return true;
        return false;
    }

    /*
        This function deletes the element in the given storage
        It assigns that element as null

        @returns a boolean value after successful deletion
     */
    public boolean delete(T x){
        if(x == null && nullCounter>0) {
            nullCounter--;
            return true;
        }
        else if(x!=null){
            for (int i = 0; i < storageArray.length; i++) {
                if (storageArray[i] == x){
                    storageArray[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /*
        This function fins the given element in the storage data structure and
        returns true or false

        @params string to search in the data structure
     */
    public boolean find(T x){
        for(T element: this.storageArray )
            if(element == x)
                return true;
        return false;
    }

}


public class TestTwo {
    public static void testItWithString(StorageParent aSortedStorage)	{

        String toInsert[] = { "8", null, "1", "2"};
        String toDelete[] = { "1", null, "1"};
        String toFind[]   = { "1", null, "1"};


        for (int index = 0; index < toInsert.length; index ++ )	{
            System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
        }

        System.out.println("- includesNull: "  + aSortedStorage.includesNull());
        System.out.println("- toString: "  + aSortedStorage.toString(""));

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
    public static void testItWithIntegers(StorageParent aSortedStorage)	{

        Integer toInsert[] = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};
        Integer toDelete[] = {  Integer.valueOf(8), null,  Integer.valueOf(1) };
        Integer toFind[]   = {  Integer.valueOf(8), null,  Integer.valueOf(1),  Integer.valueOf(2)};


        for (int index = 0; index < toInsert.length; index ++ )	{
            System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
        }

        System.out.println("- includesNull: "  + aSortedStorage.includesNull());
        System.out.println("- toIntegers: "  + aSortedStorage.toString("toString"));

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
    public static void testItWithSortedStorage(StorageParent aSortedStorage,  StorageParent[] theSortedStorages)	{
        StorageParent toInsert[] = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1], theSortedStorages[0] };
        StorageParent toDelete[] = {  theSortedStorages[0], theSortedStorages[1], null };
        StorageParent toFind[]   = {  theSortedStorages[0], theSortedStorages[1], null, theSortedStorages[1] };

        for (int index = 0; index < toInsert.length; index ++ )	{
            System.out.println("- add(" + index + "): "  + aSortedStorage.add(toInsert[index]));
        }

        System.out.println("- includesNull: "  + aSortedStorage.includesNull());
        System.out.println("- toSortedStorage: "  + aSortedStorage.toString("toIntegers"));

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
    public void test()	{
        SortedStorage<String> aSortedStringStorage = new SortedStorage(new String[1031]);
        testItWithString(aSortedStringStorage);

        SortedStorage<Integer> aSortedIntegerStorage = new SortedStorage(new Integer[1031]);
        testItWithIntegers(aSortedIntegerStorage);

        SortedStorage<SortedStorage> aSortedSortedStorageStorage = new SortedStorage(new SortedStorage[1031]);
        SortedStorage[] theSortedStorages = { aSortedStringStorage, aSortedIntegerStorage, aSortedSortedStorageStorage };
        testItWithSortedStorage(aSortedSortedStorageStorage, theSortedStorages);
    }

    public void testSecond()	{
        SortedStorageSecond<String> aSortedStringStorage = new SortedStorageSecond(new String[1031]);
        testItWithString(aSortedStringStorage);

        SortedStorageSecond<Integer> aSortedIntegerStorage = new SortedStorageSecond(new Integer[1031]);
        testItWithIntegers(aSortedIntegerStorage);

        SortedStorageSecond<SortedStorageSecond> aSortedSortedStorageStorage = new SortedStorageSecond(new SortedStorageSecond[1031]);
        SortedStorageSecond[] theSortedStorages = { aSortedStringStorage, aSortedIntegerStorage, aSortedSortedStorageStorage };
        testItWithSortedStorage(aSortedSortedStorageStorage, theSortedStorages);
    }
    public static void main(String args[] )	{
        new TestTwo().test();
        new TestTwo().testSecond();
    }
}



