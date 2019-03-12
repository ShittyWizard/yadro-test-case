package server;

import java.util.ArrayList;
import java.util.Enumeration;

public class ListOfNetInterfaces {
    private ArrayList<String> namesOfInterfaces;

    public ListOfNetInterfaces(ArrayList<String> namesOfInterfaces) {
        this.namesOfInterfaces = namesOfInterfaces;
    }

    public ArrayList<String> getNamesOfInterfaces() {
        return namesOfInterfaces;
    }
}
