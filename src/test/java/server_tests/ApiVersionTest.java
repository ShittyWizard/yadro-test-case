package server_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import server.ServerApplication;
import server.controllers.MainController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class ApiVersionTest {
    @Autowired
    private MainController controller;

    private String actualApiVersion = "v12";

    @Test
    public void testGetActualApiVersion() {
        assert controller.getActualApiVersion().getActualApiVersion().equals(actualApiVersion);
    }
}
