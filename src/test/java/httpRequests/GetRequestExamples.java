package httpRequests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class GetRequestExamples
{
    @Test
    public static void testGet()
    {	
    	//------------------------------------------------------------------------
    	//Getting Multiple Users
    	//-------------------------------------------------------------------------
    	
    	
    	
        //Setting up the Base URI
        baseURI = "https://reqres.in/";

                given().
                get("api/users?page=2").//Giving the EndPoint
                then().
                statusCode(200).//Testing the StatusCode
                        body("data.first_name[0]", equalTo("Michael")).//Matching the Response Payload
                and().log().all();     //Logging All of the response in the console.

    }
    
    @Test
    public static void getSingleUser() 
    {
    	//------------------------------------------------------------------------
    	//Getting Single User : End-point is Changed --> /api/users/2
    	//-------------------------------------------------------------------------
    	
    	  //Setting up the Base URI
        baseURI = "https://reqres.in/";

                given().
                	get("api/users/2").//Giving the EndPoint for Single User
                then().
                	statusCode(200).//Testing the StatusCode
                    body("data.id", equalTo(2)).//Matching the Response Payload
                    body("data.email",equalTo("janet.weaver@reqres.in")).
                    body("data.first_name",equalTo("Janet")).
                    body("data.last_name",equalTo("Weaver")).
                and().
                	log().all();     //Logging All of the response in the console.

    
    }
    
    //Negative Test-Case
   @Test
   public static void getNotAnUser() 
   {
	 //------------------------------------------------------------------------
   	//Getting Not an User : End-point is Changed --> /api/users/23
   	//-------------------------------------------------------------------------
   	
   	  //Setting up the Base URI
       baseURI = "https://reqres.in/";

               given().
               	get("api/users/23").//Giving the EndPoint for Invalid User (Not-an-User)
               then().
               	statusCode(404).//When Data Not Found Status Code should be 404 
               and().
               	log().all();     //Logging All of the response in the console.

   }
   
   @Test
   public static void getListOfUsers() 
   {
	   	//------------------------------------------------------------------------
	   	//Getting List of Users : End-point is Changed --> --> /api/unknown/
	   	//-------------------------------------------------------------------------
	   
	   baseURI = "https://reqres.in/";
	   
	  given().
	  	get("api/unknown").
	  then().
	  	statusCode(200).
	  assertThat().
	  	body("page",equalTo(1)).
	  	body("per_page",equalTo(6)).
	  	body("total",equalTo(12)).
	  	body("total_pages",equalTo(2)).
	  	body("data.name",hasItems("cerulean","fuchsia rose","true red","aqua sky","tigerlily", "blue turquoise")).
	  	body("data.year",hasItems( 2000,2001,2002,2003,2004,2005)).
	  and().
	  	time(lessThan(2000L)).
	  	log().all();
   }
   
   @Test
   public static void getDelayedResponse() 
   {

	   	//------------------------------------------------------------------------
	   	//Getting Delayed Response of 3000ms : End-point is Changed --> --> api/users?delay=3
	   	//-------------------------------------------------------------------------
	   
	   baseURI = "https://reqres.in/";
	   
	   given().
	   		get("api/users?delay=3").
	   then().
	   		statusCode(200).
	   and().
	   		assertThat().time(lessThan(4500L)).// Verifying Response time should be lesser than 4500MilliSeconds
	   and().
	   		log().all();
	   
	   //_______________________________________________________________________________
	   //This GET Request only passes when Response Time is Below 4500 Milliseconds
	   
   }
   
   
}
