package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[.='api_channel']")
    public WebElement apiChannelButton;

    @FindBy(xpath = "//span[.='Text from nobody.....!!??']")
    public WebElement message;

    @FindBy(id="undefined")
    public WebElement sendButton;



}
