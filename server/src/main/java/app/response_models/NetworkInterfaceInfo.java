package app.response_models;

public class NetworkInterfaceInfo {
    private String name;
    private String hw_addr;
    private String inet_addr;
    private String MTU;

    public NetworkInterfaceInfo() {
    }

    public NetworkInterfaceInfo(String name, String hw_addr, String inet_addr, String MTU) {
        this.name = name;
        this.hw_addr = hw_addr;
        this.inet_addr = inet_addr;
        this.MTU = MTU;
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

}
