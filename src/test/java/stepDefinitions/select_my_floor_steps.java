package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncSelectmyfloorfunc;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class select_my_floor_steps {

    FuncSelectmyfloorfunc obj;

    @Given("the owner selected a specific building")
    public void theOwnerSelectedASpecificBuilding() {
       obj=new FuncSelectmyfloorfunc();
    }
    @When("owner selects the floor of one of his buildings by entering floor id like {int}")
    public void ownerSelectsTheFloorOfOneOfHisBuildingsByEnteringFloorIdLike(Integer floor_id) throws SQLException {
      obj.selectmyfloor(9,floor_id);
    }
    @Then("the data of this floor should appear to him")
    public void theDataOfThisFloorShouldAppearToHim() {
        assertEquals(1,obj.myFloorFlag);
    }
    @When("owner enters floor id that doesnt exist like {int}")
    public void ownerEntersFloorIdThatDoesntExistLike(Integer floor_id) throws SQLException {
        obj.selectmyfloor(9,floor_id);
    }
    @Then("a message should appear telliung him to enter a valid one")
    public void aMessageShouldAppearTelliungHimToEnterAValidOne() {
        assertEquals(1,obj.myFloorFlag);
    }

}
