public class D extends Thread	{

    private String info;
    static Object aKey = new Object();	//
    static int counter = 0;

    public D (String info) {
        this.info = info;
    }
    public void run () {
        while ( true )
            synchronized ( aKey )	{
                try {
                    System.out.println(info);
                    	  // 1
                    aKey.notify(); // 2
                    aKey.wait();
                } catch ( Exception e ) { }
            }
    }
    public static void main (String args []) {
        new D("1").start();
        new D("2").start();
    }
}