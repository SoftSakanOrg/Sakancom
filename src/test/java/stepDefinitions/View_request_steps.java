package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncViewRequest;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class View_request_steps {
    FuncViewRequest view ;
    @Given("the Admin is on the Admin page")
    public void theAdminIsOnTheAdminPage() {
        view= new FuncViewRequest();
         }
    @When("there is data in requests table")
    public void thereIsDataInRequestsTable() throws SQLException {
    view.viewRequests();
    }
    @Then("the requests should appear to the admin")
    public void theRequestsShouldAppearToTheAdmin() throws SQLException {

        assertEquals(view.viewrequestflag ,1);

    }


    @When("there is no data in requests table")
    public void thereIsNoDataInRequestsTable() throws SQLException {
        view.viewRequests();
    }
    @Then("a message should appear  telling no data is found")
    public void aMessageShouldAppearTellingNoDataIsFound() {
        assertEquals(view.viewrequestflag ,1);
    }






}
