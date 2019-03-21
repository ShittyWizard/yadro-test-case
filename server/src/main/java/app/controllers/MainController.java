package app.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import app.response_models.ApiVersionInfo;
import app.response_models.ListOfNetInterfaces;
import app.response_models.NetworkInterfaceInfo;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

@RestController
@EnableAutoConfiguration
public class MainController {
    private static ApiVersionInfo apiVersionInfo = new ApiVersionInfo("v1");
    private Enumeration<NetworkInterface> networkInterfaces;

    /**
     * @return - actual version of API;
     */
    @RequestMapping("service/version")
    public ApiVersionInfo getActualApiVersion() {
        return new ApiVersionInfo(apiVersionInfo.getActualApiVersion());
    }

    /**
     * @param apiVersion - version of API;
     * @return - list of all network interfaces;
     * @throws SocketException - exception, if we can't get network interfaces;
     */
    @RequestMapping("/service/{apiVersion}/interfaces")
    public ListOfNetInterfaces getInterfaces(@PathVariable String apiVersion) throws SocketException {
        networkInterfaces = NetworkInterface.getNetworkInterfaces();
        ArrayList<String> namesOfInterfaces = new ArrayList<>();
        if (apiVersion.equals(apiVersionInfo.getActualApiVersion())) {
            for (NetworkInterface netint : Collections.list(networkInterfaces)) {
                namesOfInterfaces.add(netint.getName());
            }
            return new ListOfNetInterfaces(namesOfInterfaces);
        } else {
            return new ListOfNetInterfaces("This API version is not actual. Please, get the actual API version");
        }
    }

    /**
     * @param name       - name of network interface;
     * @param apiVersion - version of API;
     * @return - detailed info about selected network interface;
     * @throws SocketException - exception, if we can't get network interfaces;
     */
    @RequestMapping("/service/{apiVersion}/interface/name")
    public NetworkInterfaceInfo getDetailsNetworkInterface(@RequestParam(value = "name") String name,
                                                           @PathVariable String apiVersion) throws SocketException {
        if (apiVersion.equals(apiVersionInfo.getActualApiVersion())) {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
            try {
                for (NetworkInterface netint : Collections.list(networkInterfaces)) {
                    if (netint.getName().equals(name)) {
                        return new NetworkInterfaceInfo(
                                name,
                                getStringHardwareAddress(netint),
                                Collections.list(netint.getInetAddresses()).toString(),
                                getStringMTU(netint));
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("Can't get one of the details about this interface. NullPointerException");
            }
            return new NetworkInterfaceInfo("Error with getting some of network interface details");
        } else {
            return new NetworkInterfaceInfo("This API version is not actual. Please, get the actual API version");
        }
    }

    /**
     * @param netint - selected network interface;
     * @return -       hardware address of selected network interface;
     */
    private String getStringHardwareAddress(NetworkInterface netint) {
        try {
            byte[] mac = netint.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (byte b : mac) {
                if (sb.length() > 0)
                    sb.append(':');
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (SocketException | NullPointerException e) {
            System.out.println("Can't get hardware address of this network interface.");
        }
        return "-";
    }

    /**
     * @param netint - selected network interface;
     * @return -       MTU;
     */
    private String getStringMTU(NetworkInterface netint) throws SocketException {
//        try {
//            return String.valueOf(netint.getMTU());
//        } catch (SocketException e) {
//            System.out.println("Can't get MTU of this network interface.");
//        }
        return String.valueOf(netint.getMTU());
    }
}
