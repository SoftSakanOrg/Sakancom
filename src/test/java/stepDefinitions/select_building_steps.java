package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncSelectBuilding;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class select_building_steps {

    FuncSelectBuilding obj;
    @Given("Owner wants to select one of his buildings")
    public void ownerWantsToSelectOneOfHisBuildings() {
     obj=new FuncSelectBuilding();
    }
    @When("Owner enters building id like {int}")
    public void ownerEntersBuildingIdLike(Integer building_id) throws SQLException {
      obj.selectbuilding(building_id,33);
    }
    @Then("all building info should appear to him")
    public void allBuildingInfoShouldAppearToHim() {

        assertTrue(obj.selectBuildingFlag==1);
    }



    @Given("Owner wants to select one of his buildings but fails to do it")
    public void ownerWantsToSelectOneOfHisBuildingsButFailsToDoIt() {
        obj=new FuncSelectBuilding();
    }
    @When("Owner enters a invalid id like {int}")
    public void ownerEntersAInvalidIdLike(Integer building_id) throws SQLException {
        obj.selectbuilding(building_id,33);
    }
    @Then("an invalid message should appear to him")
    public void anInvalidMessageShouldAppearToHim() {
       assertTrue(obj.selectBuildingFlag==1);
    }


}
