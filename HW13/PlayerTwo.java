package HW13;

public class PlayerTwo {
    public static void main(String args[]){
        ClientThread clientThread1 = new ClientThread("Player 2");
        clientThread1.start();
    }
}
