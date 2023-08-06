package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.AdvertismentRequests;
import main.FuncRequestAction;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Request_action_steps {


    AdvertismentRequests obj;
    FuncRequestAction obj2;


    @Given("the admin is on the requests section")
    public void theAdminIsOnTheRequestsSection() {
        obj= new AdvertismentRequests();
        obj2=new FuncRequestAction();
    }
    @When("the admin selects a requestID like {int}")
    public void theAdminSelectsARequestIDLike(Integer req_ID) throws SQLException {
        obj.setReqId(req_ID);

        obj2.requestAction(req_ID,"",2);
    }
    @Then("the floorID should be received by the admin")
    public void theFloorIDShouldBeReceivedByTheAdmin() {
        assertEquals(1,obj2.requestflag );
    }



   @Given("the admin selected a request")
   public void theAdminSelectedARequest() {
        obj2=new FuncRequestAction();
    }



    @When("the admin enters {string}")
    public void theAdminEnters(String ans) throws SQLException {

        if(ans.equalsIgnoreCase("A")){
            obj2.requestAction(5,ans,2);

        } else if(ans.equalsIgnoreCase("B")){
            obj2.requestAction(5,ans,2);

        } else if(ans.equalsIgnoreCase("C")){
            obj2.requestAction(5,ans,2);

        }

    }
    @Then("the request will be accepted or rejected or admin will be redirected to main menu")
    public void theRequestWillBeAcceptedOrRejectedOrAdminWillBeRedirectedToMainMenu() {
        assertEquals(1,obj2.requestflag );

    }





















//    @When("the admin enters {string}")
//    public void theAdminEnters(String ans) throws SQLException {
//
//        if(ans.equalsIgnoreCase("A")){
//
//            obj2.requestAction(5,ans,2);
//        }
//
//    }
//    @Then("the request will be accepted")
//    public void theRequestWillBeAccepted() {
//        assertTrue(obj2.RequestFlag==1);
//    }
//
//
//// 1
//    @Then("the request will be rejected")
//    public void theRequestWillBeRejected() {
//
//        assertTrue(obj2.RequestFlag2==1);
//
//    }
//
//// 2
//    @Then("the admin will be redirected to main menu")
//    public void theAdminWillBeRedirectedToMainMenu() {
//
//        assertTrue(obj2.RequestFlag3==1);
//
//    }




// 1
//    @Given("the admin selected a request")
//    public void theAdminSelectedARequest() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//    @When("the admin enters {string}")
//    public void theAdminEnters(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }




// 2
//    @Given("the admin selected a request")
//    public void theAdminSelectedARequest() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//    @When("the admin enters {string}")
//    public void theAdminEnters(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//


}
