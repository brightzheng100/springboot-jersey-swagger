package bright.zheng.poc.api.resources;

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
import org.springframework.stereotype.Component;

import bright.zheng.poc.api.model.Hello;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/v1/hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Hello API - say hello the world", produces = "application/json")
public class HelloResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloResource.class);

	@GET						//JAX-RS Annotation
	@Path("/{name}")	//JAX-RS Annotation
	@ApiOperation(				//Swagger Annotation
			value = "Say hello by providing the name by URI, via standard json header", 
			response = Hello.class)  
	@ApiResponses(value = {		//Swagger Annotation
		@ApiResponse(code = 200, message = "Resource found"),
	    @ApiResponse(code = 404, message = "Resource not found")
	})
	public Response sayHelloByGet(@ApiParam @PathParam("name") String name) {
		LOGGER.info("v1/hello/{} - {}", name, MediaType.APPLICATION_JSON);
		return this.constructHelloResponse(name, MediaType.APPLICATION_JSON);
	}
	
	///////////////////////////////////////////////////////////////

	private Response constructHelloResponse(String name, String via) {
		//for testing how we handle 404
		if ("404".equals(name)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Hello result = new Hello();
		result.setMsg(String.format("Hello %s - %s", name, via));
		return Response.status(Status.OK).entity(result).build();
	}
}