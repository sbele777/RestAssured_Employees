package com.employee.TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC005_DELETERecord extends TestBase {


	@BeforeClass
	void postEmployeeRecord()
	{
		logger.info("---TC005 Delete Employee Record---");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		request = RestAssured.given();
		
		
		
		response = request.request(Method.DELETE,"/delete/2");
		
	}
	
	@Test 
	void checkStatusCode()
	{
		logger.info("---Checking Status Code---");
		int code = response.getStatusCode();
		logger.info("Status code: "+code);
		Assert.assertEquals(code, 200);
	}
	
	@Test 
	void checkStatusLine()
	{
		logger.info("---Checking Status Code---");
		String statusLine = response.getStatusLine();
		logger.info("Status Line: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test 
	void checkResponseBody()
	{
		logger.info("---Checking Status body---");
		String body = response.getBody().asString();
		logger.info("Body: "+body);
		Assert.assertEquals(body.contains("Successfully! Record has been deleted"), true);
		
	}
}
