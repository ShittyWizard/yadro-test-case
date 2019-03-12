package server;

import java.util.List;

public class CustomResponse {
    private final String apiVersion;
    private List<String> networkInterfaces;
    private List<String> details;

    CustomResponse() {
        apiVersion = null;
    }

    CustomResponse(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    CustomResponse(String apiVersion, List<String> networkInterfaces) {
        this.apiVersion = apiVersion;
        this.networkInterfaces = networkInterfaces;
    }


    CustomResponse(String apiVersion, List<String> networkInterfaces, List<String> details) {
        this.apiVersion = apiVersion;
        this.networkInterfaces = networkInterfaces;
        this.details = details;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<String> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public List<String> getDetails() {
        return details;
    }
}
