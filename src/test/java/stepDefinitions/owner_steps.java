package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.FuncOwner;
import main.OwnerFuncParam;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class owner_steps {

    FuncOwner obj;

    @Given("Owner is on owners panel")
    public void ownerIsOnOwnersPanel() {
      obj=new FuncOwner();
    }
    @When("Owner enters {string} to choose a function")
    public void ownerEntersToChooseAFunction(String functype) throws SQLException {
      if(functype.equalsIgnoreCase("A")){
          obj.ownerfunc(new OwnerFuncParam("OWNERS", 9, functype, 2));
      }
      else if(functype.equalsIgnoreCase("E")){
          obj.ownerfunc(new OwnerFuncParam("OWNERS", 9, functype, 2));
      }
    }
    @Then("a  specific function will be called depending on functype")
    public void aSpecificFunctionWillBeCalledDependingOnFunctype() {
      assertTrue(obj.ownerFlag==1);
    }

}
