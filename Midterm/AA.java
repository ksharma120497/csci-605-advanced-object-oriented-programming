package Midterm;


class  A {
    static A aA;
    static AA aAA;

    int    aInstanceInt;
    int    aInstanceInt2;

    A()    {
        aInstanceInt  = 1;
        aInstanceInt2 = 11;
    }
    void  aInstanceIntSet(int aInstanceInt) {
        System.out.println("parent");
        this.aInstanceInt =  aInstanceInt;
    }
    int  aInstanceIntGet() {
        return aInstanceInt;
    }
    public String toString()   {
        return "A: " + aInstanceIntGet() + "/" + aInstanceInt2;
    }
}
class  AA extends A {
    int aInstanceInt;

    AA()   {
        aInstanceInt = 3;
    }
    void  aInstanceIntSet(int aInstanceInt) {
        System.out.println("child");
        this.aInstanceInt =  aInstanceInt;
    }
    int  aInstanceIntGet() {
        return aInstanceInt;
    }
    public String toString()   {
        return "AA: " + aInstanceIntGet() + "/" + aInstanceInt2;
    }
    public static void print(String location)  {
        System.out.println(location);
        System.out.println("a. " + aA);
        System.out.println("b. " + aAA);
        System.out.println("c. " + aA.aInstanceInt);
        System.out.println("d. " + aAA.aInstanceInt);
        System.out.println("e. " + aA.aInstanceInt2);
        System.out.println("f. " + aAA.aInstanceInt2);
    }
    public static void main(String args[] )       {
//        aAA = new AA();
//        aA = (A)aAA;
//        aA.aInstanceIntSet(100);
//        print("1: ");
//
//        aA.aInstanceInt   = 42;
//        aAA.aInstanceInt  = 43;
//        aA.aInstanceInt2  = 44;
//        aAA.aInstanceInt2 = 45;
//        print("2: ");

        int x = 10;
        int y = 2;

        A a = new A();
        System.out.println( );

        int result = x < y? (x+y): ((x+y)/2);


        int result1 = x > y? x: y>x?x+y:y;

        int result3 = (x>>y)==3 ? x:y;

        int result4 = (x>y)? ((x%2 == 0) ? x + 10: x* 2):((y%2==0)?y-5:y/2);


        System.out.println("5: "+(10>>2));

    }
}



