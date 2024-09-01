package Midterm;

//import java.util.Vector;
//
//class MyStack<T> {
//    Vector<T> myStack = new Vector<T>();
//
//    public void push( T anElement ) {
//        myStack.add(anElement);
//    }
//    public T pop() {
//
//        if ( ! myStack.isEmpty() )	{
//            T anElement = myStack.lastElement();
//            myStack.remove(myStack.size() - 1) ;
//            return anElement;
//        } else  {
//            return null;
//        }
//    }
//    public boolean isEmpty()	{
//        return myStack.isEmpty();
//    }
//}
//
//public class MyStackUse {
//
//    public static void testString()	{
//        MyStack<String> aMyStack = new MyStack<String>();
//        aMyStack.push("a");
//        aMyStack.push("b");
//        while ( ! aMyStack.isEmpty() )	{
//            System.out.println(aMyStack.pop());
//        }
//    }
//    public static void testInteger()	{
//        MyStack<Integer> aMyStack = new MyStack<Integer>();
//        aMyStack.push(1);
//        aMyStack.push(2);
//        while ( ! aMyStack.isEmpty() )	{
//            System.out.println(aMyStack.pop());
//        }
//    }
//    public static void testMyStack()	{
//        MyStack<MyStack> aMyStack = new MyStack<MyStack>();
//        MyStack<MyStack<Integer>> bMyStack = new MyStack<MyStack<Integer>>();
//        aMyStack.push(new MyStack<Integer>());
//        aMyStack.push(new MyStack<Integer>());
//        aMyStack.push(new MyStack<String>());
//        //bMyStack.push(new MyStack<String>());
///*
//MyStackUse.java:27: error: incompatible types: MyStack<String> cannot be converted to MyStack<Integer>
//	bMyStack.push(new MyStack<String>());
//*/
//        // aMyStack.push(new Object() ); // wil not compile
//
//        while ( ! aMyStack.isEmpty() )	{
//            System.out.println(aMyStack.pop());
//        }
//    }
//    public static void main(String args[] )	{
//        for(;;){
//            System.out.println("hello");
//        }
//         //testString();
//        // testInteger();
//        //testMyStack();
//    }
//}

//class OneOfAkind<T> {
//
//    private T thisIsMe;
//    public OneOfAkind(T thisIsMe) {
//        this.thisIsMe = thisIsMe;
//    }
//
//    public void setMe(T thisIsMe) {
//        this.thisIsMe = thisIsMe;
//    }
//    public T getMe()   {
//        return thisIsMe;
//    }
//}
//
//public class MyStackUse<T> {
//
//    public static <T> boolean compare(OneOfAkind<T> first, OneOfAkind<T> second) {
//        return first.getMe() == second.getMe();
//    }
//    public static void main(String[] args)	{
//        OneOfAkind first = new OneOfAkind("me");
//        OneOfAkind<String> second = new OneOfAkind<>("me");
//        boolean same = MyStackUse.compare(first, second);
//        System.out.println("same = " + same );
//    }
//}

public class MyStackUse {

    private int test_1()	 {
        try {
            String aString = "a:";
            aString = null;
            aString.length();
            return 1;
        } catch (NullPointerException e)        {
            System.out.println("catch ");
            return 0;
        } finally	{
            System.out.println("finally");
        }
    }
    private int test_2() throws Exception{
        try {
            String aString = "a:";
            aString = null;
            aString.length();
        } catch (Exception e)	{
            System.out.println("hello");
            throw new Exception("3");
        } finally	{
            System.out.println("finally");
            return 1;
        }

    }
    public static void main(String[] args) {
        System.out.println("Rvalue = " + new MyStackUse().test_1());
        try {
            System.out.println("Rvalue = " + new MyStackUse().test_2());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}