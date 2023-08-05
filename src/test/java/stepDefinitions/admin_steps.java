package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.adminpara;
import main.funcAdmin;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class admin_steps {

    funcAdmin obj;

    @Given("admin is on admin panel")
    public void adminIsOnAdminPanel() {
     obj=new funcAdmin();
    }
    @When("admin enters {string} to choose a function")
    public void adminEntersToChooseAFunction(String functype) throws SQLException {

        if(functype.equalsIgnoreCase("A")){
           obj.adminfunc(new adminpara("ADMINS", 30, functype, 2));

        } else if(functype.equalsIgnoreCase("B")){
            obj.adminfunc(new adminpara("ADMINS", 30, functype, 2));

        } else if(functype.equalsIgnoreCase("C")){
            obj.adminfunc(new adminpara("ADMINS", 30, functype, 2));

        }
        else if(functype.equalsIgnoreCase("D")){
            obj.adminfunc(new adminpara("ADMINS", 30, functype, 2));

        }
        else if(functype.equalsIgnoreCase("E")){
            obj.adminfunc(new adminpara("ADMINS", 30, functype, 2));

        }else
            obj.adminfunc(new adminpara("ADMINS", 30, functype, 2));


    }
    @Then("a  specific function will be called")
    public void aSpecificFunctionWillBeCalled() {

        assertTrue(obj.adminflag==1);
    }




}
