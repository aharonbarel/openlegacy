package ofir.app.openlegacy.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ofir.app.openlegacy.pojo.Client;

public enum ClientDAOimpl implements ClientDAO{
	
	INSTANCE;
	
	private Map<Integer, Client> clientMock;
	private int fakeId;
	
	private ClientDAOimpl() {
		clientMock = new HashMap<>();
		fakeId = 0;
	}
	@Override
	public Client getClientById(int id) {
		return clientMock.get(id);
	}

	@Override
	public List<Client> getClients() {
		return new ArrayList<Client>(clientMock.values());
	}

	@Override
	public Client createClient(Client client) {
		if (client.getId() != 0) {
			return null;
		}
		else {
			fakeId++;
			client.setId(fakeId);
			clientMock.put(fakeId, client);
			return clientMock.get(fakeId);
		}
	}

	@Override
	public Client updateClient(Client client) {
		Client tempClient = clientMock.get(client.getId());
		if (tempClient == null) {
			return null;
		}
		else {
			clientMock.put(client.getId(), client);
			return clientMock.get(client.getId());
		}
	}

	@Override
	public Client deleteClient(Client client) {
		clientMock.remove(client.getId());
		return clientMock.get(client.getId());
	}
}
