package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.Users;

public class Admin_login_steps {


      Users obj=new Users();

    @Given("the Admin is on the login page")
    public void theAdminIsOnTheLoginPage() {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
    }
    @When("the Admin enters {string} and {string}")
    public void theAdminEntersAnd(String email, String password) {

        obj.setEmail(email);
        obj.setPassword(password);

    }
    @Then("the Admin should be redirected to their dashboard")
    public void theAdminShouldBeRedirectedToTheirDashboard() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    //************************************8


    @Then("an invalid message should be printed")
    public void anInvalidMessageShouldBePrinted() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


//    @Given("the Admin is on the login page")
//    public void theAdminIsOnTheLoginPage() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//    @When("the Admin enters {string} and {string}")
//    public void theAdminEntersAnd(String string, String string2) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }



}
