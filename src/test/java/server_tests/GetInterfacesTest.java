package server_tests;

import client.response_models.ListOfNetInterfaces;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import server.ServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class GetInterfacesTest {

    private RestTemplate restTemplate = new RestTemplate();

    private String actualApiVersion = "v1";
    private String server = "localhost";
    private String port = "8080";

    @BeforeClass
    public static void startServer(){
        ServerApplication.main(new String[]{""});
    }

    @Test
    public void test200StatusCodeGetInterfaces() {
        ResponseEntity response =
                restTemplate.getForEntity(
                        "http://" + server + ":" + port
                                + "/service/"
                                + actualApiVersion
                                + "/interfaces",
                        ListOfNetInterfaces.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }
}
