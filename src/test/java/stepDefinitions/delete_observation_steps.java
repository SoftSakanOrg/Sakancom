package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.funcDeleteObservation;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class delete_observation_steps {

    funcDeleteObservation obj;

    @Given("the admin is on the main admin page")
    public void theAdminIsOnTheMainAdminPage() {
      obj=new funcDeleteObservation();
    }
    @When("the admin deletes the observations")
    public void theAdminDeletesTheObservations() throws SQLException {

       obj.deleteObservations(2);

    }
    @Then("the observations table should be empty")
    public void theObservationsTableShouldBeEmpty() {

        assertTrue(obj.flagDeleteObservations==1);
    }

}
