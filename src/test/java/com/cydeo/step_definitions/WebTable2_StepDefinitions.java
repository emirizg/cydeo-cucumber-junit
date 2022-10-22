package com.cydeo.step_definitions;

import com.cydeo.pages.WebTableLoginPage2;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class WebTable2_StepDefinitions {

    WebTableLoginPage2 webTableLoginPage2 = new WebTableLoginPage2();

    @Given("User is on the login page of Web table app")
    public void user_is_on_the_login_page_of_web_table_app() {
        Driver.getDriver().get("https://web-table-2.cydeo.com/login");
    }
    @When("User enters below credentialss")
    public void user_enters_below_credentialss(Map<String, String> credentials) {

        webTableLoginPage2.usernameInput.sendKeys(credentials.get("username"));
        webTableLoginPage2.passwordInput.sendKeys(credentials.get("password"));
        webTableLoginPage2.loginButton.click();

    }
    @Then("User should see url contains orders")
    public void user_should_see_url_contains_orders() {

        String expectedInUrl = "orders";
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().endsWith(expectedInUrl));

    }


}
