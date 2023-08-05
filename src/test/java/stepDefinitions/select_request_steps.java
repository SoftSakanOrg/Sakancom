package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.advertismentrequests;
import main.funcSelectRequest;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class select_request_steps {

    advertismentrequests obj;
    funcSelectRequest obj2;




    @Given("the admin is on the requests page")
    public void theAdminIsOnTheRequestsPage() {
        obj= new advertismentrequests();
        obj2=new funcSelectRequest();

    }
    @When("the admin selects a request by ID like {int}")
    public void theAdminSelectsARequestByIDLike(Integer req_ID) throws SQLException {
        obj.setReqId(req_ID);

         obj2.selectRequest(req_ID,2);
    }
    @Then("the request should appear to the admin")
    public void theRequestShouldAppearToTheAdmin() {
        assertTrue(obj2.RequestFlag==1);
    }



}
