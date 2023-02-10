package Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Game game = new Game();
        Server server = new Server(5050, game);
        server.acceptConnection();
    }
}
