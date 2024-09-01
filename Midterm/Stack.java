package Midterm;

//class FirstStack<T> extends Stack<T>{
//
//}
//
//class StackExtendedStack<T> extends Stack<T>{
//
//}
//
//public class Stack<T extends  FirstStack>{
//    public static void main(String args[]){
//        Stack<Stack> aStack = new Stack<Stack>();
//        Stack<FirstStack> bStack = new Stack<FirstStack>();
//        Stack<FirstStack> cStack = new Stack<FirstStack>();
//
//
//    }
//}


public class Stack{
    public static void main(String args[]){
        try{
            try{
                int a = 1/0;
                System.out.println("1");
            }
            catch(Exception e){
                System.out.println("2");
            }finally{
                int b = 1/0;
                System.out.println("3");
            }
        }catch(ArithmeticException e){
            System.out.println("4");
        }
    }
}