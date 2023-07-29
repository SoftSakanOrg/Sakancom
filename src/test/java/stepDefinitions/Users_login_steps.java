package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.Users;
import main.featureTestFunc;
import main.usersLoginFunc;

import static org.junit.Assert.assertTrue;

public class Users_login_steps {


      Users obj=new Users();

    @Given("the Users is on the login page")
    public void theUsersIsOnTheLoginPage() {

    }
    @When("the Users enters {string} and {string}")
    public void theUsersEntersAnd(String email, String password) {
        obj.setUsertype("ADMIN");
        obj.setEmail(email);
        obj.setPassword(password);


        usersLoginFunc.Login(obj.getUsertype(),obj.getEmail(),obj.getPassword());


        assertTrue(featureTestFunc.flag==1);


    }
    @Then("the Users should be redirected to their dashboard")
    public void theUsersShouldBeRedirectedToTheirDashboard() {

    }


//*****************************
    @Then("an invalid message should be printed")
    public void anInvalidMessageShouldBePrinted() {

    }






}
