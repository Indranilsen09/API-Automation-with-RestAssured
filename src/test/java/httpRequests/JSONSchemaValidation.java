package httpRequests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class JSONSchemaValidation 
{
	@Test
    public static void testGet()
    {	
    	//-----------------------------------------------------------------------------------------
		
    	/*Validating JSON Response against JSON Schema
		In Order to Validate JSON Response against JSON Schema- we need to have JSON Schema
		Taken JSON Response and converted it into JSON Schema from Online JSON-JSON Schema converter,
		Then Kept it in classpath i.e; "user.dir/target/classes/schema.json"
		schema.json is the json schema that we'll validate our response to*/
		
    	//-------------------------------------------------------------------------------------------
	    
        //Setting up the Base URI
        baseURI = "https://reqres.in/";

        given().
        	get("api/users?page=2").//Giving the EndPoint
        then().
        	assertThat().body(matchesJsonSchemaInClasspath("schema.json")).//Validating JSON Response against JSON-Schema in classpath
        	statusCode(200).
        	body("data[0].first_name", equalTo("Michael"));
        
        

    }

}
