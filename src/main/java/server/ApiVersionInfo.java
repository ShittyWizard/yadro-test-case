package server;

import java.util.ArrayList;

public class ApiVersionInfo {
    private String actualApiVersion;
    private ArrayList<String> previousApiVersions;

    ApiVersionInfo(String actualApiVersion) {
        this.actualApiVersion = actualApiVersion;
    }

    public String getActualApiVersion() {
        return actualApiVersion;
    }

    public ArrayList<String> getPreviousApiVersions() {
        return previousApiVersions;
    }

    /**It helps changing and maintaining new and previous API's;
     *
     * @param newApiVersion - new actual version of API;
     */
    public void setActualApiVersion(String newApiVersion) {
        previousApiVersions.add(this.actualApiVersion);
        this.actualApiVersion = newApiVersion;
    }
}

