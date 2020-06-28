package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;
    private Driver(){}
    public static WebDriver getDriver(String driverName){
        if(driver==null){
            switch (driverName){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case"InternetExplorer":
                    WebDriverManager.iedriver().setup();
                    driver=new InternetExplorerDriver();
                    break;
            }
        }
        driver.manage().window().maximize();

        return driver;
    }
}
