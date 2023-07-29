package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.funcViewRequest;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class View_request_steps {
    funcViewRequest view ;
    @Given("the Admin is on the Admin page")
    public void theAdminIsOnTheAdminPage() {
         }
    @When("there is data in requests table")
    public void thereIsDataInRequestsTable() throws SQLException {
     view= new funcViewRequest();
    }
    @Then("the requests should appear to the admin")
    public void theRequestsShouldAppearToTheAdmin() throws SQLException {
        view.viewRequests();
        assertTrue(view.ViewRequestFlag==1);

    }




    @When("there is no data in requests table")
    public void thereIsNoDataInRequestsTable() {
      view = new funcViewRequest();
    }
    @Then("a message should appear {string} telling no data is found")
    public void aMessageShouldAppearTellingNoDataIsFound(String message) throws SQLException {
        String expected = message;
        view.viewRequests();
        assertEquals(expected,view.output);
       }


}
