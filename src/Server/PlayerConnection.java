package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerConnection {
    private Socket connection;

    private DataInputStream reader;
    private DataOutputStream writer;

    public PlayerConnection(Socket socket) throws IOException
    {
        connection = socket;
        writer = new DataOutputStream(socket.getOutputStream());
        reader = new DataInputStream(socket.getInputStream());
    }

    public void write(String message)
    {
        try
        {
            writer.writeUTF(message);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String read() {
        try
        {
            return reader.readUTF();
        }
        catch (IOException e) {
            return e.getMessage();
        }
    }
}
