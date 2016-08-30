package ofir.app.openlegacy.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ofir.app.openlegacy.dao.ClientDAO;
import ofir.app.openlegacy.dao.ClientDAOimpl;
import ofir.app.openlegacy.pojo.Client;

@Path("client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientService {

    private ClientDAO dao = ClientDAOimpl.INSTANCE;

    @POST
    public Client createClient(Client client) {
        return dao.createClient(client);
    }

    @GET
    public List<Client> getClients() {
        return dao.getClients();
    }

    @GET
    @Path("{id}")
    public Client getClientById(@PathParam("id") int id) {
        return dao.getClientById(id);
    }

    @PUT
    public Client updateClient(Client client) {
        return dao.updateClient(client);
    }

    @DELETE
    public Client deleteClient(Client client) {
        return dao.deleteClient(client);
    }
}
