package server;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

@RestController
public class MainController {
    private final String actualApiVersion = "v1";
    // TODO: Try add new/old API version
    private Enumeration<NetworkInterface> networkInterfaces;

    @RequestMapping("/service/version")
    public CustomResponse getApiVersion() {
        return new CustomResponse(actualApiVersion);
    }

    @RequestMapping("/service/" + actualApiVersion + "/interfaces")
    public CustomResponse getInterfaces() {
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
            List<String> listOfNetworkInterfaces = new LinkedList<>();
            for (NetworkInterface netint : Collections.list(networkInterfaces)) {
                listOfNetworkInterfaces.add(netint.getName());
            }
            return new CustomResponse(actualApiVersion, listOfNetworkInterfaces);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return new CustomResponse();
    }

    @RequestMapping("/service/" + actualApiVersion + "/interface/name")
    public CustomResponse getDetailsNetworkInterface(@RequestParam(value="name") String name) {
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
            List<String> listOfNetworkInterfaces = new LinkedList<>();
            List<String> details = new LinkedList<>();
            for (NetworkInterface netint : Collections.list(networkInterfaces)) {
                listOfNetworkInterfaces.add(netint.getName());
                if (netint.getDisplayName().equals(name)) {
                    details.add("name: " + name);
                    try {
                        byte[] mac = netint.getHardwareAddress();
                        StringBuilder sb = new StringBuilder();
                        for (byte b : mac) {
                            if (sb.length() > 0)
                                sb.append(':');
                            sb.append(String.format("%02x", b));
                        }
                        details.add("hw_addr: " + sb.toString());
                        details.add("inet_addr: " + Collections.list(netint.getInetAddresses()).toString());
                        details.add("MTU: " + netint.getMTU());
                    } catch (NullPointerException e) {
                        System.out.println("This interface has not inet/hw");
                        e.printStackTrace();
                    }
                }
            }
            return new CustomResponse(actualApiVersion, listOfNetworkInterfaces, details);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return new CustomResponse();
    }
}
