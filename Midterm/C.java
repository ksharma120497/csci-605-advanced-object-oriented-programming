package Midterm;

public class C {
    public static void main(String[] args) {
        DDC aDDC = new DDC();
        System.out.println("__________");
        CCC aCCC = new CCC();
        System.out.println("__________");
        aDDC = new DDC("" + 1);
        System.out.println("__________");
        aCCC = new CCC(1);
        System.out.println("__________");
    }
}
class CC extends C {
    CC() {
        System.out.println("DEFAULT CC");
    }
}
class DC extends C {
    DC() {
        System.out.println("DEFAULT DC");
    }
}
class CCC extends CC {
    CCC() {
        System.out.println("DEFAULT CCC");
    }
    CCC(int aInt) {
        System.out.println("INT CCC");
    }
    CCC(String aString) {
        System.out.println("STRING CCC");
    }
}
class DDC extends DC {
    DDC() {
        System.out.println(" DEF DDC");

    }
    DDC(int aInt) {
        System.out.println("INT DDC");

    }
    DDC(String aString) {
        System.out.println("STRING DDC");
    }
}