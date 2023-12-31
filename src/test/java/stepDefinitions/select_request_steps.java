package stepDefinitions;

import classes.AdvertismentRequests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncSelectRequest;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class select_request_steps {

    AdvertismentRequests obj;
    FuncSelectRequest obj2;




    @Given("the admin is on the requests page")
    public void theAdminIsOnTheRequestsPage() {
        obj= new AdvertismentRequests();
        obj2=new FuncSelectRequest();

    }
    @When("the admin selects a request by ID like {int}")
    public void theAdminSelectsARequestByIDLike(Integer req_ID) throws SQLException {
        obj.setReqId(req_ID);

         obj2.selectRequest(req_ID);
    }
    @Then("the request should appear to the admin")
    public void theRequestShouldAppearToTheAdmin() {
        assertEquals(1,obj2.getRequestflag() );
    }



}
