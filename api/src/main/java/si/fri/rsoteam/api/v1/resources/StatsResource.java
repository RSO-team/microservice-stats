package si.fri.rsoteam.api.v1.resources;

import si.fri.rsoteam.lib.dtos.StatsDto;
import si.fri.rsoteam.services.beans.StatsBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/stats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatsResource {

    private Logger log = Logger.getLogger(StatsResource.class.getName());

    @Inject
    private StatsBean statsBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getStats() {
        return Response.ok(statsBean.getAllStats()).build();
    }

    @GET
    @Path("/{objectId}")
    public Response getStatsById(@PathParam("objectId") Integer id) {
        return Response.ok(statsBean.getStats(id)).build();
    }

    @POST
    public Response createStats(StatsDto statsDto) {
        return Response.status(201).entity(statsBean.createStats(statsDto)).build();
    }

    @PUT
    @Path("{objectId}")
    public Response updateStats(@PathParam("objectId") Integer id, StatsDto eventDto) {
        return Response.status(201).entity(statsBean.updateStats(eventDto, id)).build();
    }

    @DELETE
    @Path("{objectId}")
    public Response deleteEvent(@PathParam("objectId") Integer id) {
        statsBean.deleteStats(id);
        return Response.status(204).build();
    }
}
