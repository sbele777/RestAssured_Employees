package com.employee.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_GET_SingleEmployeeData extends TestBase {

	@BeforeClass
	void getSingleEmployee(){
		logger.info("---TC002 Get Single Employee");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		request = RestAssured.given();
		response = request.request(Method.GET,"/employee/"+empID);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("---Checking Status Code---");
		int code = response.getStatusCode();
		logger.info("Status Code: "+code);
		Assert.assertEquals(code, 200);
	}
	
	
	@Test
	void checkResponseBody()
	{
		logger.info("---Checking Response body---");
		String responseBody = response.getBody().asString();
		logger.info(responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("---Checking response Time---");
		long time = response.getTime();
		logger.info("Response Time: "+time);
		Assert.assertTrue(time<3000);
	}
}
