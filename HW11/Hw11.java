package HW11;

/*
    HW11.1 code.
    the given code would not terminate due to potential reasons. As before, we were not certain about the order
    in which the threads are being executed. In order to resolve that, below code have additional lines to it.

    @author: Kapil Sharma
             Chetan Chandane

 */

public class Hw11 extends Thread {

    private String info;
    static Object o = new Object();
    static boolean should_notify = false;

    public Hw11(String info) {
        this.info = info;
    }

    public void run() {
        synchronized (o) {
            System.out.println(info);
            should_notify = true;
            o.notify();
            try {
                //shouldNotify is used to ensure that the notify() is called before the other thread
                // starts waiting. The while (!should_Notify) loop ensures that the waiting thread does
                // not proceed until the condition is true, avoiding potential infinite waits.
                while (!should_notify) {
                    o.wait();
                }
                //the thread which will be waiting will only proceed when the condition will be true in the above
                //while statement, and not before that.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * Since, we are creating two different threads we need to be certain about which thread acquires the object o
     * first. that was a potential reason for the code to not terminate. */
    public static void main(String args[]) {
        Hw11 obj1 = new Hw11("0");
        Hw11 obj2 = new Hw11("1");
        /*
         * Here, we are forcing the threads into an order of execution unlike before.*/
        obj1.start();
        obj2.start();
    }
}
