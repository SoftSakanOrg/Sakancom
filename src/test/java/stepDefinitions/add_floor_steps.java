package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncAddFloor;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
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

        assertEquals(1,obj.addFloorFlag);
    }
    @Then("this observation should be added to Observations table")
    public void thisObservationShouldBeAddedToObservationsTable() {
        assertEquals(1,obj.observationFlag);
    }
    @Then("it will be added as a request to advertisment_requests waiting for admin to accept it")
    public void itWillBeAddedAsARequestToAdvertismentRequestsWaitingForAdminToAcceptIt() {
        assertEquals(1,obj.requestFlag);
    }
    @Then("floors number should be incremented in the specific building")
    public void floorsNumberShouldBeIncrementedInTheSpecificBuilding() {
        assertEquals(1,obj.floorNumFlag);
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

        assertEquals(1,obj.selectLastFloorFlag);
    }


}
