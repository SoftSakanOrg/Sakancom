package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.funcViewObservation;
import main.funcViewRequest;
import org.junit.Ignore;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class view_observation_steps {

   funcViewObservation view;

    @Given("the Admin is on the Admin page panel")
    public void theAdminIsOnTheAdminPagePanel() {

    }
    @When("there is data in Observations table")
    public void thereIsDataInObservationsTable() {
     view= new funcViewObservation();
    }


    @Then("the Observation should appear to the admin")
    public void theObservationShouldAppearToTheAdmin() throws SQLException {
        view.viewObservations();
        assertTrue(view.ViewObservationFlag==1);
    }


    @When("there is no data in Observations table")
    public void thereIsNoDataInObservationsTable() {
        view = new funcViewObservation();

    }
    @Then("a message should appear {string} telling no Observation is found")
    public void aMessageShouldAppearTellingNoObservationIsFound(String message) throws SQLException {
        String expected = message;
        view.viewObservations();

        assertEquals(expected,view.output);

    }


}
