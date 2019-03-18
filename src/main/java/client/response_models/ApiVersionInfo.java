package client.response_models;

public class ApiVersionInfo {
    private String actualApiVersion;

    public ApiVersionInfo() {
    }

    public String getActualApiVersion() {
        return actualApiVersion;
    }

    public void setActualApiVersion(String actualApiVersion) {
        this.actualApiVersion = actualApiVersion;
    }

    @Override
    public String toString() {
        return actualApiVersion;
    }
}
