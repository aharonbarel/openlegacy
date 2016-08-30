package ofir.app.openlegacy;

import ofir.app.openlegacy.pojo.Client;
import ofir.app.openlegacy.ws.ClientService;

public class Main {
    public static void main(String args[]) {
        ClientService ws = new ClientService();

        Client temp = new Client("Ofir", "HaBanim 7", "0547611022");
        Client temp2 = new Client("Ori", "Tel Aviv", "SomeNum");

        ws.createClient(temp);
        System.out.println();

    }
}
