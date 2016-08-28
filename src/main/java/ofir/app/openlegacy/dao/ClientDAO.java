package ofir.app.openlegacy.dao;

import java.util.List;

import ofir.app.openlegacy.pojo.Client;

public interface ClientDAO {
	
	Client getClientById(int id);
	List<Client> getClients();
	Client createClient(Client client);
	Client updateClient(Client client);
	Client deleteClient(Client client);

}
