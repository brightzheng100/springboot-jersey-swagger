package bright.zheng.poc.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bright.zheng.poc.api.model.Student;
import bright.zheng.poc.api.repository.StudentJdbcRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/v1/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Student API - it's all about student services", produces = "application/json")
public class StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	StudentJdbcRepository repository;

	@GET							//JAX-RS Annotation
	@Path("/{id}")				//JAX-RS Annotation
	@ApiOperation(				//Swagger Annotation
			value = "Find student by id", 
			response = Student.class)  
	@ApiResponses(value = {		//Swagger Annotation
		@ApiResponse(code = 200, message = "Resource found"),
	    @ApiResponse(code = 404, message = "Resource not found")
	})
	public Response findStudentById(@ApiParam @PathParam("id") long id) {
		LOGGER.info("v1/student/{} - {}", id, MediaType.APPLICATION_JSON);
		Student student = this.repository.findById(id);
		if (student == null) return Response.status(Status.NOT_FOUND).build();
			
		return Response.status(Status.OK).entity(student).build();
	}
	
}