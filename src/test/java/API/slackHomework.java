package API;

import Pages.HomePage;
import Pages.LoginPage;
import Tests.TestBase;
import Utils.PayLoadUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @Test(enabled = false)
    public void caseApi() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("slack.com");
        uriBuilder.setPath("api/conversations.history");
        uriBuilder.setCustomQuery("channel=C0164SXRETU");

        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        httpGet.setHeader("Authorization","Bearer xoxb-941125893829-1209222336674-V71HpFW2Xr33KoX1tBuZecRV");

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

    @Test(enabled = false)
    public void caseApi2() throws URISyntaxException, IOException {

        HttpClient httpClient=HttpClientBuilder.create().build();

        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("slack.com").setPath("api/chat.postMessage");

        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Authorization","Bearer xoxb-941125893829-1209222336674-V71HpFW2Xr33KoX1tBuZecRV");

        HttpEntity httpEntity=new StringEntity(PayLoadUtils.PostMethod("Text from nobody....."));
        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse=httpClient.execute(httpPost);

        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode());

        ObjectMapper objectMapper=new ObjectMapper();

        Map<String,Object> parsedMethod=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
        });

        Map<String,Object> textPOST= (Map<String, Object>) parsedMethod.get("message");
     //   System.out.println(textPOST.get("text"));

        // https://slack.com/api/conversations.history?channel=C0164SXRETU
        uriBuilder.setScheme("https").setHost("slack.com").setPath("api/conversations.history").setCustomQuery("channel=C0164SXRETU");

        HttpGet httpGet=new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept","application/json");
        httpGet.setHeader("Authorization","Bearer xoxb-941125893829-1209222336674-V71HpFW2Xr33KoX1tBuZecRV");

        httpResponse=httpClient.execute(httpGet);

        parsedMethod=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        List<Map<String,Object>> textGET= (List<Map<String, Object>>) parsedMethod.get("messages");
   //     System.out.println(textGET.get(0).get("text"));
        if(!textPOST.get("text").toString().equals(textGET.get(0).get("text").toString())){
            Assert.fail();
        }

    }

    @Test(enabled = false)
    public void case3() throws URISyntaxException, IOException, InterruptedException {

        HttpClient httpClient=HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("slack.com").setPath("api/chat.postMessage");

        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Authorization","Bearer xoxb-941125893829-1209222336674-V71HpFW2Xr33KoX1tBuZecRV");

        HttpEntity httpEntity=new StringEntity(PayLoadUtils.PostMethod("Text from nobody.....!!??"));
        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse=httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode());

        ObjectMapper objectMapper=new ObjectMapper();

        Map<String,Object> parsedMap=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        Map<String,Object> messageUser= (Map<String, Object>) parsedMap.get("message");

        loginPage.signPlace.sendKeys("TechtorialBatch4");
        loginPage.continueButton.click();

        Thread.sleep(500);
        loginPage.email.sendKeys("furkanduzgunpa@gmail.com");
        loginPage.password.sendKeys("08241Fd.");
        loginPage.signButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage.apiChannelButton.click();

        Assert.assertEquals(messageUser.get("text"),homePage.message.getText());

    }

    @Test(enabled = false)
    public void case4() throws InterruptedException {
        loginPage.signPlace.sendKeys("TechtorialBatch4");
        loginPage.continueButton.click();

        Thread.sleep(500);
        loginPage.email.sendKeys("furkanduzgunpa@gmail.com");
        loginPage.password.sendKeys("08241Fd.");
        loginPage.signButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage.apiChannelButton.click();

        String message="messages from student!!!!!!";
        homePage.sendButton.sendKeys(message);

        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
        Assert.assertEquals(message,homePage.message.getText());
    }

    @Test(enabled = true)
    public void case5() throws URISyntaxException, IOException {
        HttpClient httpClient=HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("slack.com").setPath("api/chat.postMessage");

        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Authorization","Bearer xoxb-941125893829-1209222336674-V71HpFW2Xr33KoX1tBuZecRV");

        HttpEntity httpEntity=new StringEntity(PayLoadUtils.PostMethod("Text from nobody.....!!??"));
        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse=httpClient.execute(httpPost);

        if(httpResponse.getStatusLine().getStatusCode()!=200){
            Assert.fail();
        }
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> parsed=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        Map<String,Object> text= (Map<String, Object>) parsed.get("message");
     //   System.out.println(text.get("text"));
        String ts= (String) text.get("ts");


        uriBuilder.setPath("api/conversations.history").setCustomQuery("channel=C0164SXRETU");

        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        httpGet.setHeader("Authorization","Bearer xoxb-941125893829-1209222336674-V71HpFW2Xr33KoX1tBuZecRV");

        httpResponse=httpClient.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode(),"There is status code");

        objectMapper=new ObjectMapper();
        Map<String,Object> parseUser=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});
//        System.out.println(parseUser.get("ok"));

        List<Map<String,Object>> list= (List<Map<String, Object>>) parseUser.get("messages");

        String tsExpected= (String) list.get(0).get("ts");

         // https://slack.com/api/chat.delete?channel=C0164SXRETU&ts=1593493656.066900
        uriBuilder.setPath("api/chat.delete").setCustomQuery("channel=C0164SXRETU&ts="+ts);

        HttpDelete httpDelete=new HttpDelete(uriBuilder.build());
        httpDelete.setHeader("Authorization","Bearer xoxb-941125893829-1209222336674-V71HpFW2Xr33KoX1tBuZecRV");

        httpResponse=httpClient.execute(httpDelete);
        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode());

        parsed=objectMapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        String tsActual= (String) parsed.get("ts");
        Assert.assertEquals(tsExpected,tsActual);








    }
}
