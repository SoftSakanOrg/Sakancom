package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncViewObservation;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class view_observation_steps {

   FuncViewObservation view;

    @Given("the Admin is on the Admin page panel")
    public void theAdminIsOnTheAdminPagePanel() {
        view= new FuncViewObservation();
    }
    @When("there is data in Observations table")
    public void thereIsDataInObservationsTable() throws SQLException {

        view.viewObservations();
    }


    @Then("the Observation should appear to the admin")
    public void theObservationShouldAppearToTheAdmin()  {

        assertTrue(view.viewObservationFlag==1);
    }

    @When("there is no data in Observations table")
    public void thereIsNoDataInObservationsTable() throws SQLException {
        view.viewObservations();
    }
    @Then("a message should appear  telling no Observation is found")
    public void aMessageShouldAppearTellingNoObservationIsFound() {
        assertTrue(view.viewObservationFlag==1);
    }





}
