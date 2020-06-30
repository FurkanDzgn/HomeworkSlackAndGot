package Pages;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }

    @FindBy(id="domain")
    public WebElement signPlace;

    @FindBy(id="submit_team_domain")
    public WebElement continueButton;

    @FindBy(id="email")
    public WebElement email;

    @FindBy(id="password")
    public WebElement password;

    @FindBy(id="signin_btn")
    public WebElement signButton;
}
