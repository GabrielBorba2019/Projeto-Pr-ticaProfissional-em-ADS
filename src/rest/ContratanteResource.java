
package rest;

import dao.ContratanteDao;
import java.util.List;
import dao.DaoException;
import entities.Contratante;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;

public class ContratanteResource {
    private ContratanteDao dao;
    private long proximoId;
    
    public ContratanteResource(ContratanteDao dao) {
        this.dao = dao;
    }

    @POST
    public Contratante create(Contratante l) {
        Contratante resp;
        try {
            long id = dao.create(l);
            l.setId(id);
            resp = l;
        } catch(DaoException ex) {
            ex.printStackTrace();
            resp = null;
        }
        return resp;
    }
    
    @GET
    public List<Contratante> read() {
        List<Contratante> contratantes;
        try { contratantes = dao.read(); }
        catch(DaoException ex) { 
            ex.printStackTrace();
            contratantes = null; 
        }
        return contratantes;
    }

    @PUT
    @Path("{id}")
    public Contratante update(@PathParam("id") LongParam id, Contratante l) {
        Contratante resp;
        try {
            l.setId(id.get());
            dao.update(l);
            resp = l;
        } catch(DaoException ex) {
            ex.printStackTrace();
            resp = null;
        }
        return l;
    }    

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id) {
        Contratante l;
        try {
            l = dao.readById(id.get());
        } catch(DaoException ex) {
            ex.printStackTrace();
            throw new WebApplicationException("Erro ao buscar Contratante com id="
                                                + id.get(), 500);  
        }
        if (l != null) { 
            try{ 
                dao.delete(id.get()); 
            } catch(DaoException ex) {
                ex.printStackTrace();
                throw new WebApplicationException("Erro ao tentar apagar Contratante com id="
                                                  + id.get(), 500);                
            }
        }
        else {
            throw new WebApplicationException("Contratante com id=" + id.get() 
                                              + " não encontrado!", 404);
        }
        return Response.ok().build();
    }
}
