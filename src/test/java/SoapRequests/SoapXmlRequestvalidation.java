package SoapRequests;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class SoapXmlRequestvalidation 
{
	
	@Test
	public static void validateSoapXML() throws IOException
	{
		
		File file = new File("./SoapApi/Add.xml");
		if(file.exists()) System.out.println("  >> File Found.. ");
		FileInputStream fis = new FileInputStream(file);
		String requestBody = IOUtils.toString(fis, "UTF-8");
		
		if(fis != null) System.out.println("File Exists"); 

		
		baseURI = "http://dneonline.com/";
		
		given().
			contentType(ContentType.XML).
			contentType("text/xml").accept(ContentType.XML).
			body(requestBody).
		when().
			post("calculator.asmx").
		then().
			statusCode(200).
			assertThat().body("//*:AddResult.text()", equalTo("5")).
		and().log().all();
		
		
		
	}

}
