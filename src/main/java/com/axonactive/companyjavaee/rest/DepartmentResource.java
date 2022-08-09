package com.axonactive.companyjavaee.rest;

import com.axonactive.companyjavaee.rest.request.DepartmentRequest;
import com.axonactive.companyjavaee.service.DepartmentService;
import com.axonactive.companyjavaee.service.dao.DepartmentDAO;
import com.axonactive.companyjavaee.service.dto.DepartmentDto;
import com.axonactive.companyjavaee.service.mapper.DepartmentMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Stateless
@Path(DepartmentResource.PATH)
public class DepartmentResource {

    public static final String PATH = "departments";
    @Inject
    DepartmentService departmentService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<DepartmentDto> getAll() {
        return departmentService.getAll();
    }

    @GET
    @Path("{DepartmentId}")
    @Produces({MediaType.APPLICATION_JSON})
    public DepartmentDto findById(@PathParam("DepartmentId") Integer departmentId) {
        return departmentService.findById(departmentId);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addDepartment( DepartmentRequest departmentRequest) {
        DepartmentDto createdDept = departmentService.save(departmentRequest);
        System.out.println(Response.created(appendCurrentUriWith(createdDept.getDepartmentId().toString())).build());
        return Response.ok().entity(createdDept).status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{DepartmentId}")
    public Response delete(@PathParam("DepartmentId") Integer departmentId) {
        departmentService.delete(departmentId);
        return Response.ok().build();
    }

    @PUT
    @Path("{DepartmentId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})

    public Response updateDepartment(@PathParam("DepartmentId") Integer departmentId, DepartmentRequest departmentRequest) {
        DepartmentDto updatedDepartment = departmentService.update(departmentId, departmentRequest);
        return Response.ok().entity(updatedDepartment).build();
    }

    private URI appendCurrentUriWith(String fragment) {
        return uriInfo.getAbsolutePathBuilder().path(fragment).build();
    }
}
