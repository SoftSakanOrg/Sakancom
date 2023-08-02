package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.funcViewBuilding;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class view_building_steps {

    funcViewBuilding obj;

    @Given("the owner is on the owner page")
    public void theOwnerIsOnTheOwnerPage() {
        obj=new funcViewBuilding();
    }
    @When("the owner searches for his buildings by his id like {int}")
    public void theOwnerSearchesForHisBuildingsByHisIdLike(Integer owner_id) throws SQLException {

        obj.viewbuilding(owner_id,2);
    }
    @Then("the owner buildings should appear to him")
    public void theOwnerBuildingsShouldAppearToHim() {

        assertTrue(obj.flagViewBuilding==1);
    }

}
