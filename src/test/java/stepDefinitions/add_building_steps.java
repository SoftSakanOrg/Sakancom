package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncAddBuilding;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class add_building_steps {

    FuncAddBuilding obj;

    @Given("selecting contact info by owner id")
    public void selectingContactInfoByOwnerId() {
        obj= new FuncAddBuilding();
    }
    @When("selecting  contact info by owner id like {int}")
    public void selectingContactInfoByOwnerIdLike(Integer owner_id) throws SQLException {
     obj.addbuildingfunc(owner_id,2);
    }
    @Then("contact info should appear")
    public void contactInfoShouldAppear() {
        assertEquals(1,obj.getSelectinfoflag());
    }



    @Given("owner wants a new building to be added")
    public void ownerWantsANewBuildingToBeAdded() {
        obj= new FuncAddBuilding();
    }
    @When("owner inserts all info to building")
    public void ownerInsertsAllInfoToBuilding() throws SQLException {
        obj.addbuildingfunc(33,2);
    }
    @Then("new building should appear to the table")
    public void newBuildingShouldAppearToTheTable() {
        assertEquals(1,obj.getAddbuildingflag() );
    }



    @Given("the Observation should appear to admin")
    public void theObservationShouldAppearToAdmin() {
        obj= new FuncAddBuilding();
    }
    @When("owner adds the building")
    public void ownerAddsTheBuilding() throws SQLException {
        obj.addbuildingfunc(33,2);
    }
    @Then("the adding observation will appear to admin")
    public void theAddingObservationWillAppearToAdmin() {
        assertEquals(1,obj.getAddobservationflag() );
    }


}
