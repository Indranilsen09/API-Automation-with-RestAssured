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
	
	//This is Second Type of Creating an JSONObject / Request Payload without Map/HashMap
	
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
			header("Connection","keep-alive"). //Testing Out Header Config
			contentType(ContentType.JSON). //Giving Out header(Content-Type:application/JSON) 
			accept(ContentType.JSON). //Accepting header(Content-Type:application/JSON) 
			body(req.toJSONString()).
		when().
			post("/api/users").
		then().
			statusCode(201).assertThat().body("id", matchesRegex("\\d+")). //One more type: InstanceOf(Long.class)
		and().
			log().all();
	}
	
	
	@Test
	public static void postRegister() 
	{
		//-----------------------------------------------------------------------------------
		// Registering an User with Email password and getting an output with id and token
		//-----------------------------------------------------------------------------------
		
		baseURI= "https://reqres.in/";
		
		JSONObject request = new JSONObject();
		request.put("email","eve.holt@reqres.in");
		request.put("password", "pistol");
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/api/register").
		then().
			assertThat().
			statusCode(200).
		and().
			body("id", equalTo(4)).
			body("token",matchesRegex("[a-zA-Z0-9]+")).
			log().all();
	}
	
	
	//Negative Test Case
	@Test
	public static void unsuccessfulRegister() 
	{
		//-----------------------------------------------------------------------------------
		// Registering an User with Email & without password and getting an error : Missing Password
		//-----------------------------------------------------------------------------------
		
		baseURI= "https://reqres.in/";
		
		JSONObject request = new JSONObject();
		request.put("email","eve.holt@reqres.in");
		
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/api/register").
		then().
			assertThat().
			statusCode(400).
		and().
			body("error",equalTo("Missing password")).
			log().all();
	}
	
	@Test
	public static void login() 
	{
		//-----------------------------------------------------------------------------------------
		// Login an User with Email & password and getting Token Output 
		//--------------------------------------------------------------------------------------
				
		
		baseURI= "https://reqres.in/";
		
		JSONObject request = new JSONObject();
		request.put("email","eve.holt@reqres.in");
		request.put("password","cityslicka");
		
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/api/login").
		then().
			assertThat().
			statusCode(200).
		and().
			body("token",matchesRegex("[a-zA-Z0-9]+")).
			log().all();
	}
	
	@Test
	public static void invalidLogin() 
	{
		//-----------------------------------------------------------------------------------
		// Login an User with Email & without password and getting an error : Missing Password
		//-----------------------------------------------------------------------------------
				
		
		baseURI= "https://reqres.in/";
		
		JSONObject request = new JSONObject();
		request.put("email","eve.holt@reqres.in");
		//request.put("password","cityslicka");
		
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/api/login").
		then().
			assertThat().
			statusCode(200).
		and().
			body("error",equalTo("Missing password")).
			log().all();
	}
}
