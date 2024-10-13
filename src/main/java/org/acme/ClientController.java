package org.acme;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientController {

    @Inject
    ClientService clientService;

    @GET
    public List<Client> getAllClients() {
        return clientService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getClientById(@PathParam("id") Long id) {
        Client client = clientService.findById(id);
        if (client != null) {
            return Response.ok(client).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createClient(@Valid Client client) {
        clientService.create(client);
        return Response.status(Response.Status.CREATED).entity(client).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateClient(@PathParam("id") Long id, @Valid Client client) {
        clientService.update(id, client);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClient(@PathParam("id") Long id) {
        clientService.delete(id);
        return Response.noContent().build();
    }
}
