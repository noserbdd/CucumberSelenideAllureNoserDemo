package cucumber.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.codeborne.selenide.SelenideElement;


public class NoserGoogleSuche {
	//Zum Vergleich: So sieht es mit 'purem' Selenium aus 
	//@FindBy(name="q")
	//private WebElement querySearchField;
	private SelenideElement querySearchField = $(By.name("q"));

	
	@Given("^Die Google-Suchmaske ist sichtbar$")
	public void chekBrowserOpen() {
		querySearchField.shouldBe(visible);
	}
	
	@When("^Der Google-Nutzer gibt '(.*)' ein$")
	public void enterSearchWord(String searchWord) {
		querySearchField.sendKeys(searchWord + Keys.ENTER);
	}
	
	@Then("^Der Google-Nutzer bekommt die Noser Anzeige mit dem Text '(.*)' angezeigt$")
	public void verifyCreditProposal(String expectedAddResultText) {
		//$x("//span[text()='Anzeige']//preceding::div[contains(text(), 'Noser Engineering AG - Fit für die Zukunft?')]")
		SelenideElement querySearchResults = $(By.xpath("//span[text()='Anzeige']//preceding::div[contains(text(), '" + expectedAddResultText + "')]"));
		querySearchResults.shouldBe(visible);
		
	}
}
