package com.axonactive.companyjavaee.rest;

import com.axonactive.companyjavaee.rest.request.DepartmentRequest;
import com.axonactive.companyjavaee.service.DepartmentService;
import com.axonactive.companyjavaee.service.dao.DepartmentDAO;
import com.axonactive.companyjavaee.service.dto.DepartmentDto;
import com.axonactive.companyjavaee.service.mapper.DepartmentMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("departments")
public class DepartmentResource {
    @EJB
    DepartmentService departmentService;

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
    public Response addDepartment(DepartmentRequest departmentRequest) {
        departmentService.save(departmentRequest);
        return Response.status(Response.Status.CREATED).build();
    }
}
