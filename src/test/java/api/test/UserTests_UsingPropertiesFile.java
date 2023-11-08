package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints_UsingPropertiesFile;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests_UsingPropertiesFile {

	Faker facker;
	User userPayload;
	public Logger logger;
	
	
	@BeforeClass
	public void setupData() {
		facker=new Faker();
		userPayload=new User();
		
		userPayload.setId(facker.idNumber().hashCode());
		userPayload.setUsername(facker.name().username());
		userPayload.setFirstName(facker.name().firstName());
		userPayload.setLastName(facker.name().lastName());
		userPayload.setEmail(facker.internet().safeEmailAddress());
		userPayload.setPassword(facker.internet().password(5,7));
		userPayload.setPhone(facker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
 	public void testPostUser() { 
		logger.info("****** Creating Use Info *********");
		Response response= UserEndPoints_UsingPropertiesFile.createUser(userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);	
	}
	
	@Test(priority = 2)
	public void testGetUserByname() {
		
		logger.info("****** Reading User Info *********");
		
		Response response= UserEndPoints_UsingPropertiesFile.readUser(this.userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.statusCode(), 200);
		
	}

	@Test(priority = 3)
	public void updatUserByNBame() {
		
		logger.info("****** Updated User Info *********");
		
		//update data using payLoad
		userPayload.setFirstName(facker.name().firstName());
		userPayload.setLastName(facker.name().lastName());
		userPayload.setEmail(facker.internet().safeEmailAddress());
		
		
		Response response=UserEndPoints_UsingPropertiesFile.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log();
		
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		  
		//Checking data after update
		Response responseAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
		
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(),200);
			
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("****** Deleted User Info *********");
		
		Response response=UserEndPoints_UsingPropertiesFile.deleteUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
}