package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.Sakan;
import main.Tenant;
import main.featureTestFunc;
import main.myApp;

import java.security.AuthProvider;
import java.sql.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tenant_login_steps {

    Tenant obj=new Tenant();


    @Given("the tenant is on the login page")
    public void theTenantIsOnTheLoginPage() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();

    }

    @When("the tenant enters {string} and {string}")
    public void theTenantEntersAnd(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();

        obj.setEmail(email);
        obj.setPassword(password);


        featureTestFunc.login(obj);


            assertTrue(featureTestFunc.flag==1);
       // assertTrue(featureTestFunc.flag==1);


    }
    @Then("the tenant should be redirected to their dashboard")
    public void theTenantShouldBeRedirectedToTheirDashboard() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
    }
    @Then("the tenant should see a welcome message")
    public void theTenantShouldSeeAWelcomeMessage() {
        // Write code here that turns the phrase above into concrete actions
    //    throw new io.cucumber.java.PendingException();
    }

//     Sakan sk;
//
//
//     public Tenant_login_steps(Sakan sk){
//     super();
//     this.sk=sk;
//     }
//    @Given("the tenant is on the login page")
//    public void theTenantIsOnTheLoginPage() {
//        // Write code here that turns the phrase above into concrete actions
//
//        sk.flaglogin=false;
//    }
//    @When("the tenant enters valid credentials")
//    public void theTenantEntersValidCredentials() {
//         //Sakan obj=new Sakan();
//        // Write code here that turns the phrase above into concrete actions
//
//
//        String Email=sk.T.getEmail();
//        String password=sk.T.getPassword();
//
//
//        try{
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sakan", "root", "");
//            PreparedStatement pst = connection.prepareStatement("SELECT EMAIL,PASSWORD FROM TENANTS WHERE EMAIL = '" + Email + "' AND PASSWORD = '" + password + "'");
//            ResultSet rs = pst.executeQuery();
//
//            if(rs.next()){
//                assert(true);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    @Then("the tenant should be redirected to their dashboard")
//    public void theTenantShouldBeRedirectedToTheirDashboard() {
//        // Write code here that turns the phrase above into concrete actions
//
//    }
//    @Then("the tenant should see a welcome message")
//    public void theTenantShouldSeeAWelcomeMessage() {
//        // Write code here that turns the phrase above into concrete actions
//
//    }
//
//    //-----------------------------------------------------------------------------------------------
//
 //  @Given("the tenant is on the login page")
 //   public void theTenantIsOnTheLoginPage() {
////        // Write code here that turns the phrase above into concrete actions
////        throw new io.cucumber.java.PendingException();
 //  }
   @When("the tenant enters invalid credentials")
   public void theTenantEntersInvalidCredentials() {
//        // Write code here that turns the phrase above into concrete actions
//
   }
   @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
//        // Write code here that turns the phrase above into concrete actions
//
    }
    @Then("the tenant should remain on the login page")
    public void theTenantShouldRemainOnTheLoginPage() {
//        // Write code here that turns the phrase above into concrete actions
//
    }
//
//

}
