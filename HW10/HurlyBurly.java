package HW10;

public class HurlyBurly extends Thread {

    static int aStaticInt;
    int id;

    HurlyBurly(int id) throws InterruptedException {

        this.id = id;
        aStaticInt = id;
        if ( id == 1 ) {
            new HurlyBurly(2).run();

        }
    }
    public void run()	{

        System.out.println( id + " -----> ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("id/aStaticInt = " + id + "/" + aStaticInt );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println( id + " <----- ");
    }
    public static void main( String[] args ) throws InterruptedException {
        new HurlyBurly(1).start();

    }
}
