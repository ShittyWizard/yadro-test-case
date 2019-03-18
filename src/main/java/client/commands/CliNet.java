package client.commands;

import client.response_models.ApiVersionInfo;
import client.response_models.ListOfNetInterfaces;
import client.response_models.NetworkInterfaceInfo;
import org.springframework.web.client.RestTemplate;
import picocli.CommandLine;

@CommandLine.Command(name = "cli_net", subcommands = {
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
    @CommandLine.Option(names = {"-s", "--server"}, required = true)
    private String server;

    @CommandLine.Option(names = {"-p", "--port"}, required = true)
    private String port;

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        ApiVersionInfo apiVersionInfo = restTemplate.getForObject("http://" + server + ":" + port
                + "/service/version1", ApiVersionInfo.class);
        assert apiVersionInfo != null;
        System.out.println(apiVersionInfo.getActualApiVersion());
    }
}


@CommandLine.Command(name = "list",
        description = "Prints all network interfaces")
class List implements Runnable {
    @CommandLine.Option(names = "-v", defaultValue = "v1")
    private String apiVersion;

    @CommandLine.Option(names = {"-s", "--server"}, required = true)
    private String server;

    @CommandLine.Option(names = {"-p", "--port"}, required = true)
    private String port;

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        ListOfNetInterfaces listOfNetInterfaces =
                restTemplate.getForObject(
                        "http://" + server + ":" + port
                                + "/service/"
                                + apiVersion
                                + "/interfaces",
                        ListOfNetInterfaces.class);
        assert listOfNetInterfaces != null;
        System.out.println(listOfNetInterfaces);
    }
}

@CommandLine.Command(name = "show",
        description = "Prints detail information about selected network interface")
class Show implements Runnable {
    @CommandLine.Option(names = "-v", defaultValue = "v1")
    private String apiVersion;

    @CommandLine.Parameters(index = "0", arity = "1..*")
    private String networkInterface;

    @CommandLine.Option(names = {"-s", "--server"}, required = true)
    private String server;

    @CommandLine.Option(names = {"-p", "--port"}, required = true)
    private String port;

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        NetworkInterfaceInfo networkInterfaceInfo =
                restTemplate.getForObject(
                        "http://" + server + ":" + port
                                + "/service/"
                                + apiVersion
                                + "/interface/name?name=" + networkInterface,
                        NetworkInterfaceInfo.class);
        assert networkInterfaceInfo != null;
        System.out.println(networkInterfaceInfo.toString());
    }
}
