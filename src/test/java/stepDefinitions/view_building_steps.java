package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncViewBuilding;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class view_building_steps {

    FuncViewBuilding obj;

    @Given("the owner is on the owner page")
    public void theOwnerIsOnTheOwnerPage() {
        obj=new FuncViewBuilding();
    }
    @When("the owner searches for his buildings by his id like {int}")
    public void theOwnerSearchesForHisBuildingsByHisIdLike(Integer owner_id) throws SQLException {

        obj.viewbuilding(owner_id);
    }
    @Then("the owner buildings should appear to him")
    public void theOwnerBuildingsShouldAppearToHim() {

        assertTrue(obj.flagViewBuilding==1);
    }

}
