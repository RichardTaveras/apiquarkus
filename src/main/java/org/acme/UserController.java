package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    // Endpoint para iniciar sesión
    @POST
    @Path("/login")
    public Response login(@Valid User user) {
        User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return Response.ok("Inicio de sesión exitoso").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
    }

    // Endpoint para registrar un usuario
    @POST
    @Path("/register")
    @Transactional
    public Response register(@Valid User user) {
        try {
            userService.registerUser(user);
            return Response.status(Response.Status.CREATED).entity("Usuario registrado con éxito").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Endpoint para obtener datos de ejemplo
    @GET
    @Path("/data")
    public Response getData() {
        return Response.ok("Datos de ejemplo").build();
    }
}
