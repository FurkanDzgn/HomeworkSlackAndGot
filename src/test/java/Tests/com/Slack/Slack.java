package Tests.com.Slack;

import Pages.HomePage;
import Pages.LoginPage;
import Tests.TestBase;
import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Slack extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    @BeforeClass
    public void setUpPage(){
        driver.get("https://app.slack.com/client/TTP3PS9QD/CTCU3ATAM");
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);

    }

    @Test
    public void case1() throws InterruptedException {

        loginPage.signPlace.sendKeys("TechtorialBatch4");
        loginPage.continueButton.click();

        Thread.sleep(500);
        loginPage.email.sendKeys("furkanduzgunpa@gmail.com");
        loginPage.password.sendKeys("08241Fd.");
        loginPage.signButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage.apiChannelButton.click();
        Assert.assertTrue(homePage.message.getText().contains("student B"));
    }
}
