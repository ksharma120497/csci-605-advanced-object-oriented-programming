package HW4;

/*

    @Author Kapil Sharma ks4643
    @Author Chetan Chandane cc5831

    This application creates a storage functionality that stores data in a sorted manner

    It performs its insertion in the sorted manner so that the list is always sorted at any time

    There are other functions performed on it like addition, deletion, and finding of elements

 */

 class SortedStorageTest {

    private static int count = 0;

    private static int nullCounter = 0;

    private String[] storageArray = new String[10231];


    /*
        This function overrides the toString function and defines its own logic
        to display the member variables of the class

        @returns string with all the elements in the array and also the count of the null values
     */

    public String toString(){
        String finalString = "";
        for(String elements: this.storageArray)
            if(elements!=null)
                finalString = finalString + " " + elements;

        finalString = finalString + "\nIncludes so many null values = " + nullCounter;
        return "Values stored" + finalString;
    }

    /*
        This function adds the elements in the storage system and is always sorted

        We first search the array for the right place to keep the element if it finds the
        the right place that keeps the array in sorted order then we insert the element there
     */

    public boolean add(String x){
        if(count == 0){
            storageArray[0] = x;
            count++;
            return true;
        }
        for(int i=0; i<count; i++) {
            if (x != null && storageArray[i]!="*")  {
                if (Integer.parseInt(x) < Integer.parseInt(storageArray[i])) {
                    storageArray[i + 1] = storageArray[i];
                    storageArray[i] = x;
                    storageArray[count] = "*";
                    count++;
                    return true;
                }
            }
            else{
                storageArray[count]= "*";
                nullCounter++;
                count++;
                return true;
            }
        }
        return false;
    }

    /*
        This function fins the given element in the storage data structure and
        returns true or false

        @params string to search in the data structure
     */
    public boolean find(String x){
        for(String element: storageArray)
            if(element == x)
                return true;
        return false;
    }

    /*
        This function checks whether the storage functionality has
        a null value or not

        @returns a boolean value
     */
    public boolean includesNull(){
        for(String element: storageArray)
            if(element == null)
                return true;
        return false;
    }


    /*
        This function deletes the element in the given storage
        It assigns that element as null

        @returns a boolean value after successful deletion
     */
    public boolean delete(String x){
        if (x == null) {
            for (int i = 0; i < storageArray.length; i++) {
                if (storageArray[i] == "*"){
                    storageArray[i] = null;
                    return true;
                }
            }
        }
       else
           for(int i=0; i<storageArray.length;i++) {
            if (storageArray[i] == x) {
                storageArray[i] = null;
                return true;
            }
        }
        return false;
    }
}

 class SortedStorage{
    public static void testIt(SortedStorageTest aSortedStorage)	{

        String toInsert[] = { "8",null,null,"1","2"};
        String toDelete[] = { null, null, null};
        String toFind[]   = { "1", null, "1"};


        for (int index = 0; index < toInsert.length; index ++ )	{
            System.out.println("- add(" + toInsert[index] + "): "  + aSortedStorage.add(toInsert[index]));
        }

       System.out.println("- includesNull: "  + aSortedStorage.includesNull());
          System.out.println("- toString: "  + aSortedStorage.toString());

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
    public static void main(String args[] )	{
        SortedStorageTest aSortedStorage = new SortedStorageTest();
        testIt(aSortedStorage);
    }
}

