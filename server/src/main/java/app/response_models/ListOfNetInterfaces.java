package app.response_models;

import java.util.ArrayList;

public class ListOfNetInterfaces {
    private ArrayList<String> namesOfInterfaces;
    private String error;

    public ListOfNetInterfaces() {
    }

    public ListOfNetInterfaces(String error) {
        this.error = error;
    }

    public ListOfNetInterfaces(ArrayList<String> namesOfInterfaces) {
        this.namesOfInterfaces = namesOfInterfaces;
    }

    public ArrayList<String> getNamesOfInterfaces() {
        return namesOfInterfaces;
    }

    public String getError() {
        return error;
    }
}
