package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncAddFloor;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class add_floor_steps {

    FuncAddFloor obj;
    @Given("Owner wants to add floor")
    public void ownerWantsToAddFloor() {

       obj =new FuncAddFloor();

    }
    @When("Owner adds floor to a building with id {int}")
    public void ownerAddsFloorToABuildingWithId(Integer building_id) throws SQLException {
      obj.addfloor(building_id,2);
    }
    @Then("floor should be added to this building")
    public void floorShouldBeAddedToThisBuilding() {

        assertTrue(obj.addFloorFlag==1);
    }
    @Then("this observation should be added to Observations table")
    public void thisObservationShouldBeAddedToObservationsTable() {
        assertTrue(obj.observationFlag==1);
    }
    @Then("it will be added as a request to advertisment_requests waiting for admin to accept it")
    public void itWillBeAddedAsARequestToAdvertismentRequestsWaitingForAdminToAcceptIt() {
        assertTrue(obj.requestFlag==1);
    }
    @Then("floors number should be incremented in the specific building")
    public void floorsNumberShouldBeIncrementedInTheSpecificBuilding() {
         assertTrue(obj.floorNumFlag==1);
    }


    @Given("selects last floor to get the id")
    public void selectsLastFloorToGetTheId() {
       obj=new FuncAddFloor();
    }
    @When("selecting the floor ordered by desc limit by one")
    public void selectingTheFloorOrderedByDescLimitByOne() throws SQLException {
       obj.addfloor(25,9);
    }
    @Then("last floor should appear from table")
    public void lastFloorShouldAppearFromTable() {

        assertTrue(obj.selectLastFloorFlag==1);
    }


}
