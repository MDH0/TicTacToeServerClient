package Server;

public class Game {
    private Piece[][] gameField;
    private boolean playerTurn = false; //false = Spieler X, true = Spieler O

    public Game() {
        gameField = new Piece[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameField[i][j] = Piece.E;
            }
        }
    }

    public String placeMarker(int x, int y) {
        if (!gameField[x][y].equals(Piece.E)) {
            return "Feld ist bereits gefüllt";
        }
        if (playerTurn) {
            gameField[x][y] = Piece.O;
            playerTurn = !playerTurn;
            return "O wurde in Position X=" + x + "; Y=" + y + "gesetzt.";
        }
        gameField[x][y] = Piece.X;
        playerTurn = !playerTurn;
        return "X wurde in Position X=" + x + "; Y=" + y + "gesetzt.";
    }

    public String printGameField() {
        return " " + gameField[0][0] + " | " + gameField[0][1] + " | " + gameField[0][2] + "\n"
                + "-----------\n"
                + " " + gameField[1][0] + " | " + gameField[1][1] + " | " + gameField[1][2] + "\n"
                + "-----------\n"
                + " " + gameField[2][0] + " | " + gameField[2][1] + " | " + gameField[2][2] + "\n";
    }
    
    public Piece checkIfSomeoneWon() {
        for (Piece[] field: gameField) {
            if (!field[0].equals(Piece.E) && field[0].equals(field[1]) && field[0].equals(field[2])) {
                if (field[0].equals(Piece.X)) {
                    return Piece.X;
                }
                return Piece.O;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (!gameField[0][i].equals(Piece.E) && gameField[0][i].equals(gameField[1][i]) && gameField[0][i].equals(gameField[2][i])) {
                if (gameField[0][i].equals(Piece.X)) {
                    return Piece.X;
                }
                return Piece.O;
            }
        }
        if (!gameField[0][0].equals(Piece.E) && gameField[0][0].equals(gameField[1][1]) && gameField[0][0].equals(gameField[2][2])) {
            if (gameField[0][0].equals(Piece.X)) {
                return Piece.X;
            }
            return Piece.O;
        }
        if (!gameField[0][2].equals(Piece.E) && gameField[0][2].equals(gameField[1][1]) && gameField[0][2].equals(gameField[2][0])) {
            if (gameField[0][2].equals(Piece.X)) {
                return Piece.X;
            }
            return Piece.O;
        }
        return Piece.E;
    }
}
