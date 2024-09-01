package HW13;

import java.io.IOException;

public class PlayerOne {
    public static void main(String args[]) throws IOException {
        ClientThread clientThread1 = new ClientThread("Player 1");
        clientThread1.run();

    }
}
