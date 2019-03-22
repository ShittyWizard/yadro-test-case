package response_models;

import java.util.Arrays;

public class NetworkInterfaceInfo {
    private String name;
    private String hw_addr;
    private String inet_addr;
    private String MTU;

    public NetworkInterfaceInfo() {
    }

    public String getName() {
        return name;
    }

    public String getHw_addr() {
        return hw_addr;
    }

    public String getInet_addr() {
        return inet_addr;
    }

    public String getMTU() {
        return MTU;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        char[] charArray = new char[name.length() + 4];
        Arrays.fill(charArray, ' ');
        sb.append(name).append(":   ").append(hw_addr).append("\n");
        sb.append(charArray).append(inet_addr).append("\n");
        sb.append(charArray).append(MTU);
        return sb.toString();
    }
}
