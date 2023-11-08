package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority = 1, dataProvider = "data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email,
			String Password, String Phone) {

		User userPayload = new User();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);

		Response response = UserEndPoints.createUser(userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
	public void testDeletUserByName(String UserName) {
		Response response= UserEndPoints.deleteUser(UserName);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	}

}
