package Steps;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.RedBusHomePage;

public class RedBusStep {

    RedBusHomePage home;


    public void setup() {
        DriverFactory.initDriver();
        home = new RedBusHomePage(DriverFactory.getDriver());
    }
    @Given("user opens the browser")
    public void user_opens_the_browser() {
        setup();
    }
    @When("user opens redBus application")
    public void user_opens_red_bus_application() {
        home.openRedBus();
    }
    @When("user enters source {string}")
    public void user_enters_source(String source) {
        home.enterSource(source);
    }
    @When("user enters destination {string}")
    public void user_enters_destination(String dest) {
        home.enterDestination(dest);
    }
    @When("user selects journey date")
    public void user_selects_journey_date() {
        home.selectDate();
    }
    @When("user clicks on search")
    public void user_clicks_on_search() {
        home.clickSearch();
    }
    @When("user selects a bus")
    public void user_selects_a_bus() {
        home.chooseBus();
    }
    @Then("user selects a seat")
    public void user_selects_a_seat() {
        home.selectSeat();
        tearDown();
    }

    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
