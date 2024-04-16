package httpRequests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;


public class PostRequestExamples
{
	
	//This is First Type of Method of Creating an JSONObject / Request Payload
	
	@Test
	public static void testPost() 
	{
		baseURI= "https://reqres.in/";
		
		//For Post Request We Need to Create the Payload : Request payload
		//For that Purpose we will use JSON Simple Dependency to create JSON Object
		
		
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("name", "Indranil");
		request.put("job", "Automation Tester");
		//Once we Created this Map we will feed it into JSONObject
		
		
		JSONObject req = new JSONObject(request);
		
		given().
			contentType(ContentType.JSON). //Giving Out header(Content-Type:application/JSON) 
			accept(ContentType.JSON). //Accepting header(Content-Type:application/JSON) 
			body(req.toJSONString()).
		when().
			post("/api/users").
		then().
			statusCode(201).assertThat().body("id", matchesRegex("\\d+")).
		and().
			log().all();
		
	}
	
	@Test
	public static void testPOST() 
	{
		
		baseURI= "https://reqres.in/";
		//For Post Request We Need to Create the Payload : Request payload
		//For that Purpose we will use JSON Simple Dependency to create JSON Object
		
		JSONObject req = new JSONObject();
		req.put("name","Indranil");
		req.put("job", "SDET");
		
		given().
			header("Connection","keep-alive").
			contentType(ContentType.JSON). //Giving Out header(Content-Type:application/JSON) 
			accept(ContentType.JSON). //Accepting header(Content-Type:application/JSON) 
			body(req.toJSONString()).
		when().
			post("/api/users").
		then().
			statusCode(201).assertThat().body("id", matchesRegex("\\d+")).
		and().
			log().all();
	}

}
