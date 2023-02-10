package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Writer extends Thread {
    private DataOutputStream outputStream;
    private Scanner scanner = new Scanner(System.in);

    public Writer (DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run()
    {
        while (true) {
            try
            {
                outputStream.writeUTF(scanner.nextLine());
            } catch (IOException e)
            {
                return;
            }
        }
    }

    public void stopWriter() {
        this.interrupt();
    }
}
