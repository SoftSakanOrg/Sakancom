package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncDeleteObservation;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class delete_observation_steps {

    FuncDeleteObservation obj;

    @Given("the admin is on the main admin page")
    public void theAdminIsOnTheMainAdminPage() {
      obj=new FuncDeleteObservation();
    }
    @When("the admin deletes the observations")
    public void theAdminDeletesTheObservations() throws SQLException {

       obj.deleteObservations(2);

    }
    @Then("the observations table should be empty")
    public void theObservationsTableShouldBeEmpty() {

        assertEquals(obj.flagdeleteobservations ,1);
    }

}
