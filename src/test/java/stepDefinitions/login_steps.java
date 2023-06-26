package stepDefinitions;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import mainpkg.myApp;

public class login_steps {
	
	myApp objApp;
	
	
	public login_steps(myApp objApp) { //it will give error for flaglogin
		                              //because objApp isnt ready to be used
		                              //so we make this constructor for it
		super();
		this.objApp = objApp;
	}

	@Given("that the administrator is not logged in")
	public void thatTheAdministratorIsNotLoggedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		objApp.flag_login=false;
	}

	@Given("the password is {string}")
	public void thePasswordIs(String passCuc) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		assertTrue(objApp.pass.equals(passCuc));
	}

	@Then("the administrator login succeeds")
	public void theAdministratorLoginSucceeds() {
	    // Write code here that turns the phrase above into concrete actions
	  //  throw new io.cucumber.java.PendingException();
	}

	@Then("the administrator is logged in")
	public void theAdministratorIsLoggedIn() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}


}
