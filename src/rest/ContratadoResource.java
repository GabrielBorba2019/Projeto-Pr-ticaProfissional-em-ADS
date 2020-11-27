package rest;

import dao.ContratadoDao;
import entities.Contratado;
import java.util.List;
import dao.DaoException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;

public class ContratadoResource {

    private ContratadoDao dao;
    private long proximoId;

    public ContratadoResource(ContratadoDao dao) {
        this.dao = dao;
    }

    @POST
    public Contratado create(Contratado l) {
        Contratado resp;
        try {
            long id = dao.create(l);
            l.setId(id);
            resp = l;
        } catch (DaoException ex) {
            ex.printStackTrace();
            resp = null;
        }
        return resp;
    }

    @GET
    public List<Contratado> read() {
        List<Contratado> contratados;
        try {
            contratados = dao.read();
        } catch (DaoException ex) {
            ex.printStackTrace();
            contratados = null;
        }
        return contratados;
    }

    @PUT
    @Path("{id}")
    public Contratado update(@PathParam("id") LongParam id, Contratado l) {
        Contratado resp;
        try {
            l.setId(id.get());
            dao.update(l);
            resp = l;
        } catch (DaoException ex) {
            ex.printStackTrace();
            resp = null;
        }
        return l;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id) {
        Contratado l;
        try {
            l = dao.readById(id.get());
        } catch (DaoException ex) {
            ex.printStackTrace();
            throw new WebApplicationException("Erro ao buscar Contratado com id="
                    + id.get(), 500);
        }
        if (l != null) {
            try {
                dao.delete(id.get());
            } catch (DaoException ex) {
                ex.printStackTrace();
                throw new WebApplicationException("Erro ao tentar apagar Contratado com id="
                        + id.get(), 500);
            }
        } else {
            throw new WebApplicationException("Contratado com id=" + id.get()
                    + " não encontrado!", 404);
        }
        return Response.ok().build();

    }
}
