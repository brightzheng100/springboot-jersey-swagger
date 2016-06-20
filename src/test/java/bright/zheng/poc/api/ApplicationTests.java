package bright.zheng.poc.api;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.restassured.RestAssured;

/**
 * Application level testing
 * 
 * @author bright.zheng
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
@WebIntegrationTest({"server.port=0"})
public class ApplicationTests {
	
	@Value("${local.server.port}")
    private int port;
	
	@Before
    public void setupURL() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/";
    }
  
    /**
     * 
     * {"app":{"name":"springboot-jersey-swagger"},"build":{"version":"1.0.0-SNAPSHOT"}}
     * 
     * @throws Exception
     */
    @Test
    public void springroot_info() throws Exception {
    	expect().
	    	body("app.name", equalTo("springboot-jersey-swagger")).
	    	body("build.version", equalTo("1.0.0-SNAPSHOT")).
	    when().
	    	get("/info");
    }
    
    /**
     * {"msg":"Hello Bright - application/json"}
     * 
     * @throws Exception
     */
    @Test
    public void hello_get() {
    	expect().
    		body("msg", containsString("Hello Bright")).
        when().
        	get("/api/v1/hello/Bright");
    }

}
