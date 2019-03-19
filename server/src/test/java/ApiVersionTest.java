import app.ServerApplication;
import app.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class ApiVersionTest {
    private MainController controller = new MainController();

    private String actualApiVersion = "v1";

    @Test
    public void testGetActualApiVersion() {
        assert controller.getActualApiVersion().getActualApiVersion().equals(actualApiVersion);
    }
}