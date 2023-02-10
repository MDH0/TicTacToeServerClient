package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;

    private Reader reader;
    private Writer writer;

    public Client(String ipAdress, int port) throws IOException
    {
        socket = new Socket(ipAdress, port);
        writer = new Writer(new DataOutputStream(socket.getOutputStream()));
        reader = new Reader(new DataInputStream(socket.getInputStream()), writer);
        reader.start();
        writer.start();
    }
}
