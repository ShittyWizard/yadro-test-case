package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;


public class ClientApplication {
    private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();
        while (sc.hasNext()) {
            String next = sc.next();
            if (next.equals("cli_net_list")) {
                ListOfNetInterfaces listOfNetInterfaces =
                        restTemplate.getForObject(
                                "http://localhost:8080/service/v1/interfaces",
                                ListOfNetInterfaces.class);
                System.out.println(listOfNetInterfaces.toString());
            } else if(next.equals("cli_net_show")) {
                NetworkInterfaceInfo networkInterfaceInfo =
                        restTemplate.getForObject(
                                "http://localhost:8080/service/v1/interface/name?name=lo",
                                NetworkInterfaceInfo.class);
                System.out.println(networkInterfaceInfo.toString());
            }
        }

    }
}
