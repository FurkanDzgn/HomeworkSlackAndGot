package Tests;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static SoftAssert softAssert;

    @Parameters("driverNm")
    @BeforeTest(alwaysRun = true)
    public static void setUp(String driverName){
        driver= Driver.getDriver(driverName);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        softAssert=new SoftAssert();
    }
}
