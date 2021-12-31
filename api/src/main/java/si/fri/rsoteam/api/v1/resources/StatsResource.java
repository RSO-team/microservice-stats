package si.fri.rsoteam.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
    @Operation(summary = "Returns list of all stats.", description = "Returns list of all stats..")
    @APIResponses({
            @APIResponse(
                    description = "Successfully returned all stats.",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = StatsDto.class)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}

            )
    })
    public Response getStats() {
        return Response.ok(statsBean.getAllStats()).build();
    }

    @GET
    @Path("/{objectId}")
    @Operation(summary = "Gets specific stat by id.", description = "Returns specific stat.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully returned specific stats.",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = StatsDto.class))
            )
    })
    public Response getStatsById(@PathParam("objectId") Integer id) {
        return Response.ok(statsBean.getStats(id)).build();
    }

    @POST
    @Operation(summary = "Create new stat.", description = "Create new stat.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully created new stat.",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = StatsDto.class))
            )
    })
    public Response createStats(StatsDto statsDto) {
        return Response.status(201).entity(statsBean.createStats(statsDto)).build();
    }

    @PUT
    @Path("{objectId}")
    @Operation(summary = "Update specific stat.", description = "Update specific stat by id.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully updated stat.",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = StatsDto.class))
            )
    })
    public Response updateStats(@PathParam("objectId") Integer id, StatsDto eventDto) {
        return Response.status(201).entity(statsBean.updateStats(eventDto, id)).build();
    }

    @DELETE
    @Path("{objectId}")
    @Operation(summary = "Delete specific stat.", description = "Delete specific stat by id.")
    @APIResponses({
            @APIResponse(
                    description = "Successfully deleted stat.",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = StatsDto.class))
            )
    })
    public Response deleteEvent(@PathParam("objectId") Integer id) {
        statsBean.deleteStats(id);
        return Response.status(204).build();
    }
}
