package cucumber.stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class Hooks {
	
	private WebDriver webDriver;
	
	@Before
	public void setUP(){
		//System.setProperty("selenide.browser", "firefox");
		
		open("https://www.google.ch");
		webDriver = WebDriverRunner.getWebDriver();
		acceptConsentOptionally(true);
	}
	
	@After
	public void tearDown(){

		webDriver.quit();
		System.out.println("Closed the browser");
	}
	
	private void acceptConsentOptionally(Boolean consentOptional) {
		
		WebDriverWait wait =  new WebDriverWait(webDriver, 4);;
		
		By myByStatement = By.xpath("//iframe[contains(@src,'consent')]");
		SelenideElement agreeButton = $(By.id("introAgreeButton"));
		WebElement iframe = null;
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(myByStatement));
			iframe = webDriver.findElement(myByStatement);
		}
		catch(Exception e){
			webDriver.quit();
		}

		
		if(!consentOptional && iframe == null) {
			System.out.println("Die Zustimmung war nicht optional, aber nicht sichtbar.");
		}
		
		switchFrame(agreeButton, iframe);
	}

	private void switchFrame(SelenideElement agreeButton, WebElement iframe) {
		webDriver.switchTo().frame(iframe);
		agreeButton.click();
		webDriver.switchTo().defaultContent();
	}

}
