package API;

import Pages.HomePage;
import Pages.LoginPage;
import Tests.TestBase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class slackHomework extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    @BeforeClass
    public void setUpPage(){
        driver.get("https://app.slack.com/client/TTP3PS9QD/CTCU3ATAM");
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);

    }

    @Test
    public void caseApi() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("slack.com");
        uriBuilder.setPath("api/conversations.history");
        uriBuilder.setCustomQuery("channel=C0164SXRETU");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        httpGet.setHeader("Authorization","Bearer xoxb-941125893829-1209222336674-ME8ZoaxUnsymEwiQLGxowKe4");
        HttpResponse httpResponse=httpClient.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode(),"There is status code");
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> parseUser=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});
//        System.out.println(parseUser.get("ok"));

        List<Map<String,Object>> list= (List<Map<String, Object>>) parseUser.get("messages");
 //       System.out.println(list.get(0).get("user"));
        System.out.println(list.size());
        for(Map<String, Object> ll:list){
            System.out.println(ll.get("user"));
        }

        loginPage.signPlace.sendKeys("TechtorialBatch4");
        loginPage.continueButton.click();

        Thread.sleep(500);
        loginPage.email.sendKeys("furkanduzgunpa@gmail.com");
        loginPage.password.sendKeys("08241Fd.");
        loginPage.signButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage.apiChannelButton.click();
        Assert.assertTrue(homePage.message.getText().contains("student B"));

        Assert.assertEquals(list.get(0).get("text"),homePage.message.getText());
    }
}
