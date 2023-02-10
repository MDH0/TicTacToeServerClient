package Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        if (args.length != 2) {
            System.out.println("Invalide Argumente.");
            System.exit(1);
        }
        Client client = new Client(args[0], Integer.parseInt(args[1]));
    }
}
