/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.rest;

import at.heli.scada.bl.InstallationService;
import at.heli.scada.bl.interfaces.IInstallationService;
import at.heli.scada.dal.interfaces.InstallationRepository;
import at.heli.scada.dal.interfaces.MeasurementRepository;
import at.heli.scada.dal.interfaces.TypeRepository;
import at.heli.scada.dal.qualifier.DbInstallationQualifier;
import at.heli.scada.dal.qualifier.DbMeasurementQualifier;
import at.heli.scada.dal.qualifier.DbTypeQualifier;
import java.net.URI;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("device")
@RequestScoped
public class DeviceResource {

    @Context
    private UriInfo context;
    
    @Inject @DbMeasurementQualifier
    MeasurementRepository mrepo;
    
    @Inject @DbInstallationQualifier
    InstallationRepository irepo;
    
    @Inject @DbTypeQualifier
    TypeRepository trepo;
    
    IInstallationService sbl; 

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
        sbl = new InstallationService(mrepo, irepo, trepo);
        try {
            sbl.createMeasure(in.value, in.typeid, serialno);
            return Response.created(URI.create("/" + in)).build();
        } catch (Exception e) {
          throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}

/**
 *
 * @author daniel
 */
@XmlRootElement
class MeasureValue
{
    public int typeid;
    public Double value;
}
