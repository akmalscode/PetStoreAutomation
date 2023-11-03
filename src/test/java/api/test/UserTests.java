package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker facker;
	User userPayload;
	
	
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
		
		
	}
	
	@Test(priority = 1)
 	public void testPostUser() { 
		Response response= UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);	
	}
	
	@Test(priority = 2)
	public void testGetUserByname() {
		
		Response response= UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);	
		
	}

	@Test(priority = 3)
	public void updatUserByNBame() {
		//update data using payLoad
		userPayload.setFirstName(facker.name().firstName());
		userPayload.setLastName(facker.name().lastName());
		userPayload.setEmail(facker.internet().safeEmailAddress());
		
		
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log();
		
		Assert.assertEquals(response.getStatusCode(),200);
		  
		//Checking data after update
		Response responseAfterUpdate=UserEndPoints.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
			
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
}