package httpRequests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestExamples
{
    @Test
    public static void testGet()
    {
        //Setting up the Base URI
        baseURI = "https://reqres.in/";

                given().
                get("api/users?page=2").//Giving the EndPoint
                then().
                statusCode(200).//Testing the StatusCode
                        body("data.first_name[0]", equalTo("Michael")).//Matching the Response Payload
                and().log().all();     //Logging All of the response in the console.

    }
}
