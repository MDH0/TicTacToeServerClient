package Client;

import java.io.DataInputStream;
import java.io.IOException;

public class Reader extends Thread {
    private DataInputStream inputStream;
    private Writer writer;

    public Reader (DataInputStream inputStream, Writer writer) {
        this.inputStream = inputStream;
        this.writer = writer;
    }

    @Override
    public void run()
    {
        while (true) {
            try
            {
                System.out.println(inputStream.readUTF());
            } catch (IOException e)
            {
                System.out.println("Server wurde geschlossen.");
                writer.stopWriter();
                return;
            }
        }
    }
}
