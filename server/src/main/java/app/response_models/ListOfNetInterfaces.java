package app.response_models;

import java.util.ArrayList;

public class ListOfNetInterfaces {
    private ArrayList<String> namesOfInterfaces;

    public ListOfNetInterfaces() {
    }

    public ListOfNetInterfaces(ArrayList<String> namesOfInterfaces) {
        this.namesOfInterfaces = namesOfInterfaces;
    }

    public ArrayList<String> getNamesOfInterfaces() {
        return namesOfInterfaces;
    }
}
