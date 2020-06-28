package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameOfThrones {

    @Test
    public void got() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("api.got.show");
        uriBuilder.setPath("api/map/characters/byId/5cc0743504e71a0010b852d9");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        HttpResponse httpResponse=httpClient.execute(httpGet);
        if(httpResponse.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
            Assert.fail();
        }

        ObjectMapper objectMapper=new ObjectMapper();

        Map<String,Object>  parsedResponse=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {});

        System.out.println(parsedResponse.get("message"));
        Map<String, Object> parsed2= (Map<String, Object>) parsedResponse.get("data");
        System.out.println(parsed2.get("titles"));
        List<String> listBooks= (List<String>) parsed2.get("books");
        for(String lB:listBooks){
            System.out.println(lB);
        }
        System.out.println(parsed2.get("_id"));
        System.out.println(parsed2.get("dateOfBirth"));

        Set<String> set=parsed2.keySet();
 //       System.out.println(set);

        for(String s:set){
            System.out.println(parsed2.get(s));
        }




    }
}
