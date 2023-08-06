package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncViewBuildingFunc;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class view_building_func_steps {

    FuncViewBuildingFunc obj;

    @Given("the owner wants to view a specific floor of his building")
    public void theOwnerWantsToViewASpecificFloorOfHisBuilding() {
        obj=new FuncViewBuildingFunc();

    }
    @When("owner enters {string} and building with id {int}")
    public void ownerEntersAndBuildingWithId(String viewType, Integer building_id) throws SQLException {
       obj.viewBuildingFunc(building_id,viewType,2);
    }
    @Then("floor should appear to him from the specific building")
    public void floorShouldAppearToHimFromTheSpecificBuilding() {

        assertTrue(obj.viewfloorflag ==1);
    }


    @Given("the owner failed to view his floor")
    public void theOwnerFailedToViewHisFloor() {
        obj=new FuncViewBuildingFunc();
    }
    @When("owner enters {string} and building id that doesn`t exit like {int}")
    public void ownerEntersAndBuildingIdThatDoesnTExitLike(String viewType, Integer building_id) throws SQLException {
        obj.viewBuildingFunc(building_id,viewType,2);
    }
    @Then("a message should appear telling no floors available")
    public void aMessageShouldAppearTellingNoFloorsAvailable() {
        assertTrue(obj.viewfloorflag ==1);
    }



    @Given("the owner tries to go back to main menu")
    public void theOwnerTriesToGoBackToMainMenu() {
        obj=new FuncViewBuildingFunc();
    }
    @When("owner enters {string}")
    public void ownerEnters(String viewType) throws SQLException {
        obj.viewBuildingFunc(9,viewType,2);
    }
    @Then("owner should transfer back to main menu")
    public void ownerShouldTransferBackToMainMenu() {
        assertTrue(obj.viewfloorflag ==1);
    }



}
