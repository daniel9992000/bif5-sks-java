/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.services;

import at.heli.scada.bl.interfaces.ExecutionResult;
import at.heli.scada.bl.interfaces.IInstallationService;
import at.heli.scada.entities.Measurement;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class MeasureValue
{
    public int typeid;
    public Double value;
}

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("device")
@RequestScoped
public class DeviceResource {
    
    private static final Logger log = Logger.getLogger(DeviceResource.class.getName());


    @Context
    private UriInfo context;
    
    @Inject
    private IInstallationService sbl; 

    /**
     * Creates a new instance of DeviceResource
     */
    public DeviceResource() {
        
    }

    @Path("create/{serialno}")
    @POST
    @Consumes("application/json")
    public Response createMeasure(@PathParam("serialno") String serialno, MeasureValue in)
    {
        
        try {
            log.log(Level.INFO, "Creating measurement for installation with serialno {0}", serialno);
            ExecutionResult<Measurement> result = sbl.createMeasure(in.value, in.typeid, serialno);
            if(result.isSuccess())
            {
                return Response.created(URI.create("/" + in)).build();
            }
            else
            {
                return Response.serverError().build();
            }
        } catch (Exception err) {
            log.log(Level.SEVERE, "error while receiving measure!", err);
            throw new WebApplicationException(err, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
