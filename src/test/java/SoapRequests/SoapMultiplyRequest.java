package SoapRequests;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SoapMultiplyRequest
{
	@Test
	public static void validateMultiplication() throws IOException
	{
		
		baseURI = "http://www.dneonline.com/";

		File file = new File("./SoapApi/Multiply.xml");
		if(file.exists()) System.out.println("  >> File Found.. ");
		FileInputStream fis = new FileInputStream(file);
		String requestBody = IOUtils.toString(fis, "UTF-8");
		
		if(fis != null) System.out.println("File Exists"); 

		
		given().
		contentType(ContentType.XML).
		contentType("text/xml").accept(ContentType.XML).
		body(requestBody).
	when().
		post("calculator.asmx").
	then().
		statusCode(200).
		assertThat().body("//*:MultiplyResult.text()",equalTo("8")).
	and().log().all();
	}

}
