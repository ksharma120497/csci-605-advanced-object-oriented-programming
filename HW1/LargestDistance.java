package HW1;


public class LargestDistance {
    static int MAXIMUM = 50;
    static int MINIMUM = 1;
    private static boolean testProperty(int a, int b, int c ){
        if((a*a*a)+(b*b*b)==(c*c)) return true;
        return false;
    }
    private static void findAllNumbers() {
        for(int i=MINIMUM; i<=MAXIMUM; i++){
            for(int j=MINIMUM; j<=MAXIMUM; j++){
                for(int k=MINIMUM; k<=MAXIMUM; k++){
                    if(testProperty(i,j,k)){
                        System.out.println(i+"^3 + "+j+"^3 == "+k+"^2");
                        System.out.println(i*i*i + "+" +j*j*j + " == " + k*k);
                    }
                }
            }
        }
    }

    public static void main( String[] args ) {
        findAllNumbers();
    }
}
