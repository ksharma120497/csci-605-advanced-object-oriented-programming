package Finals;

public class X1 extends Thread{
    private String info;
    static Object o = new Object();

    public X1(String info){
        this.info = info;
        synchronized (o){
            if (info.equals("0")){
                new X1("1").start();
            }
        }
    }

    public void run(){
        for(int index = 0; index < 3; index++){
            synchronized (o){
                 System.out.println(info);
                 try{
                     o.notify();
                     o.wait();
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
            }
        }
    }

    public static void main(String args[]){
        new X1("0").start();

    }
}
