package client_tests;

import client.ClientApplication;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import server.ServerApplication;
import server.controllers.MainController;

import java.net.SocketException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class ParsingCommandLineTest {
    private String server = "localhost";
    private String port = "8080";
    private String actualApiVersion = "v1";

    @Autowired
    private MainController controller;

    @BeforeClass
    public static void startServer() {
        ServerApplication.main(new String[]{""});
    }

    public static void startClientApplication(String[] args) {
        ClientApplication.main(args);
    }

    @Test
    public void testVersionParsing() {
        startClientApplication(new String[]{"version", "-s", server, "-p", port});
        String response = controller.getActualApiVersion().getActualApiVersion();
        System.out.println(response);
        Assert.assertEquals(actualApiVersion, response);
    }

    // Testing for "lo"
    @Test
    public void testInterfaceInfoParsing() throws SocketException {
        String name = "lo";
        startClientApplication(new String[]{"show", name, "-s", server, "-p", port});
        String responseNameOfInterface = controller.getDetailsNetworkInterface("lo", actualApiVersion).getName();
        Assert.assertEquals(name, responseNameOfInterface);
    }

    // Testing contains "lo" into list of interfaces
    @Test
    public void testListOfInterfacesParsing() throws SocketException {
        String name = "lo";
        startClientApplication(new String[]{"list", "-s", server, "-p", port});
        assert controller.getInterfaces(actualApiVersion).getNamesOfInterfaces().contains(name);
    }
}
