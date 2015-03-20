package net.thucydides.showcase.jbehave.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import net.thucydides.showcase.jbehave.model.ListingItem;
import net.thucydides.showcase.jbehave.steps.serenity.BuyerSteps;
import static net.thucydides.showcase.jbehave.model.SessionVariables.SELECTED_LISTING;

public class DisplayProductDetailsScenarioSteps {

    @Steps
    BuyerSteps buyer;

    @Given("I have searched for '(.*)'")
    public void givenIHaveSearchedFor(String searchTerm) {
        buyer.opens_home_page();
        buyer.searches_by_keyword(searchTerm);
    }

    @When("I (?:have selected|select) item (\\d+)")
    public void whenISelectListingItem(int number) {
        ListingItem selectedListingItem = buyer.selects_listing(number);
        Serenity.setSessionVariable(SELECTED_LISTING).to(selectedListingItem);
    }

    @Then("I should see product description and price on the details page")
    public void thenIShouldSeeProductDescriptionAndPriceOnTheDetailsPage() {
         ListingItem selectedListingItem = (ListingItem) Serenity.sessionVariableCalled(SELECTED_LISTING);
         buyer.should_see_product_details_for(selectedListingItem);
    }

    @Then("I should see a product rating")
    public void shouldSeeProductRating() {
        buyer.should_see_product_rating();
    }

    @Then("I should see social media links")
    public void shouldSeeSocialMediaLinks() {
        buyer.should_see_facebook_link();
        buyer.should_see_twitter_link();
        buyer.should_see_tumblr_link();
    }

    @When("I view the currency options")
    public void viewCurrencyOptions() {
        buyer.view_currency_options();
    }


    @Then("I should see a list of possible currencies")
    public void shouldSeePossibleCurrencies() {
        buyer.currency_options_include("AUD", "USD", "GBP", "EUR");
    }
}