package commands;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import picocli.CommandLine;
import response_models.ApiVersionInfo;
import response_models.ListOfNetInterfaces;
import response_models.NetworkInterfaceInfo;

@CommandLine.Command(name = "cli_net",
        version = "v1",
        mixinStandardHelpOptions = true,
        subcommands = {
                List.class,
                Show.class,
                Version.class})
public class CliNet implements Runnable {
    @Override
    public void run() {
        System.out.println("Specify a subcommand");
    }
}

@CommandLine.Command(name = "version",
        description = "Prints actual API version")
class Version implements Runnable {
    @CommandLine.Option(names = {"-s", "--server"}, description = "Host address", required = true)
    private String server;

    @CommandLine.Option(names = {"-p", "--port"}, description = "Port", required = true)
    private String port;

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ApiVersionInfo apiVersionInfo = restTemplate.getForObject("http://" + server + ":" + port
                    + "/service/version", ApiVersionInfo.class);
            assert apiVersionInfo != null;
            System.out.println(apiVersionInfo.getActualApiVersion());
        } catch (ResourceAccessException e) {
            System.out.println("Error... Resource is not available");
        }
    }
}


@CommandLine.Command(name = "list",
        version = "v1",
        mixinStandardHelpOptions = true,
        description = "Prints all network interfaces")
class List implements Runnable {
    @CommandLine.Option(names = {"-v"}, defaultValue = "v1", description = "Version of API")
    private String apiVersion;

    @CommandLine.Option(names = {"-s", "--server"}, description = "Host address", required = true)
    private String server;

    @CommandLine.Option(names = {"-p", "--port"}, description = "Port", required = true)
    private String port;

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ListOfNetInterfaces listOfNetInterfaces =
                    restTemplate.getForObject(
                            "http://" + server + ":" + port
                                    + "/service/"
                                    + apiVersion
                                    + "/interfaces",
                            ListOfNetInterfaces.class);
            assert listOfNetInterfaces != null;
            System.out.println(listOfNetInterfaces.toString());
        } catch (ResourceAccessException e) {
            System.out.println("Error... Resource is not available");
        }
    }
}

@CommandLine.Command(name = "show",
        version = "v1",
        mixinStandardHelpOptions = true,
        description = "Prints detail information about selected network interface")
class Show implements Runnable {
    @CommandLine.Option(names = {"-v"}, defaultValue = "v1", description = "Version of API")
    private String apiVersion;

    @CommandLine.Parameters(index = "0", arity = "1..*")
    private String networkInterface;

    @CommandLine.Option(names = {"-s", "--server"}, description = "Host address", required = true)
    private String server;

    @CommandLine.Option(names = {"-p", "--port"}, description = "Port", required = true)
    private String port;

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            NetworkInterfaceInfo networkInterfaceInfo =
                    restTemplate.getForObject(
                            "http://" + server + ":" + port
                                    + "/service/"
                                    + apiVersion
                                    + "/interface/name?name=" + networkInterface,
                            NetworkInterfaceInfo.class);
            assert networkInterfaceInfo != null;
            System.out.println(networkInterfaceInfo.toString());
        } catch (ResourceAccessException e) {
            System.out.println("Error... Resource is not available");
        }
    }
}
