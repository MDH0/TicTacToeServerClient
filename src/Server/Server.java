package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private PlayerConnection connectionWithPlayer1;
    private PlayerConnection connectionWithPlayer2;

    private Game game;

    public Server(int port, Game game) throws IOException
    {
        server = new ServerSocket(port);
        this.game = game;
    }

    public void acceptConnection() throws IOException
    {
        System.out.println("Versuche Verbindung mit Spieler 1 aufzubauen");
        Socket s = server.accept();
        System.out.println("Verbindung mit Spieler 1 erfolgreich");
        connectionWithPlayer1 = new PlayerConnection(s);
        connectionWithPlayer1.write("Du bist Spieler X");
        System.out.println("Versuche Verbindung mit Spieler 2 aufzubauen");
        s = server.accept();
        System.out.println("Verbindung mit Spieler 2 erfolgreich");
        connectionWithPlayer2 = new PlayerConnection(s);
        connectionWithPlayer2.write("Du bist Spieler O");
        System.out.println("Starte das Spiel.");
        game();
    }

    private void game() {
        writeGameField();
        while (true)
        {
            connectionWithPlayer2.write("Spieler X ist am Zug.");
            placePlayerMarker(connectionWithPlayer1);
            connectionWithPlayer2.write("Spieler X hat seinen Zug getätigt.");
            writeGameField();
            if (game.checkIfSomeoneWon() == Piece.X) {
                writeWinner(Piece.X);
                return;
            }
            else if (game.checkIfSomeoneWon() == Piece.O) {
                writeWinner(Piece.O);
                return;
            }
            connectionWithPlayer1.write("Spieler O ist am Zug");
            placePlayerMarker(connectionWithPlayer2);
            connectionWithPlayer1.write("Spieler O hat seinen Zug getätigt.");
            writeGameField();
            if (game.checkIfSomeoneWon() == Piece.X) {
                writeWinner(Piece.X);
                return;
            }
            else if (game.checkIfSomeoneWon() == Piece.O) {
                writeWinner(Piece.O);
                return;
            }
        }
    }

    private void writeGameField() {
        connectionWithPlayer1.write(game.printGameField());
        connectionWithPlayer2.write(game.printGameField());
    }

    private void writeWinner(Piece player) {
        connectionWithPlayer1.write(player + " hat gewonnen.");
        connectionWithPlayer2.write(player + " hat gewonnen.");
    }

    private void placePlayerMarker(PlayerConnection player) {
        int x;
        int y;
        while (true) {
            player.write("Gib die Zeilennummer an (0-indexed):");
            try {
                x = Integer.parseInt(player.read());
            } catch (NumberFormatException e) {
                player.write("Dies ist keine normale Zahl. Versuche es erneut");
                continue;
            }
            if (x < 0 || x > 2) {
                player.write("Die Zahl ist nicht gültig, versuche es nochmal.");
                continue;
            }
            break;
        }
        while (true) {
            player.write("Gib die Spaltennummer an (0-indexed):");
            try {
                y = Integer.parseInt(player.read());
            } catch (NumberFormatException e) {
                player.write("Dies ist keine normale Zahl. Versuche es erneut");
                continue;
            }
            if (y < 0 || y > 2) {
                player.write("Die Zahl ist nicht gültig, versuche es nochmal.");
                continue;
            }
            break;
        }
        String result = game.placeMarker(x, y);
        if (result.equals("Feld ist bereits gefüllt")) {
            player.write(result);
            placePlayerMarker(player);
            return;
        }
        player.write(result);
    }
}
