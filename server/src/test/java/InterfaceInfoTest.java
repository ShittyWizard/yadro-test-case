import app.ServerApplication;
import app.controllers.MainController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import app.response_models.NetworkInterfaceInfo;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class InterfaceInfoTest {
    private MainController controller = new MainController();

    private RestTemplate restTemplate = new RestTemplate();

    private String actualApiVersion = "v1";
    private String server = "localhost";
    private String port = "8080";

    // Testing for "lo" network interface
    @Test
    public void test200StatusCodeGetInterfaceInfo() {
        ResponseEntity response =
                restTemplate.getForEntity(
                        "http://" + server + ":" + port
                                + "/service/"
                                + actualApiVersion
                                + "/interface/name?name=lo",
                        NetworkInterfaceInfo.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCorrectNameOfInterface() throws SocketException {
        Random random = new Random();
        ArrayList<String> interfaces;
        interfaces = controller.getInterfaces(actualApiVersion).getNamesOfInterfaces();
        String networkInterface = interfaces.get(random.nextInt(interfaces.size()));
        NetworkInterfaceInfo networkInterfaceInfo = restTemplate.getForObject(
                "http://" + server + ":" + port
                        + "/service/"
                        + actualApiVersion
                        + "/interface/name?name=" + networkInterface,
                NetworkInterfaceInfo.class);
        assert networkInterfaceInfo != null;
        Assert.assertEquals(networkInterface, networkInterfaceInfo.getName());
    }
}
