package client.response_models;

import java.util.ArrayList;

public class ListOfNetInterfaces {
    private ArrayList<String> namesOfInterfaces;

    public ListOfNetInterfaces() {
    }

    public ArrayList<String> getNamesOfInterfaces() {
        return namesOfInterfaces;
    }

    public void setNamesOfInterfaces(ArrayList<String> namesOfInterfaces) {
        this.namesOfInterfaces = namesOfInterfaces;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (namesOfInterfaces.size() != 0) {
            for (int i = 0; i < namesOfInterfaces.size(); i++) {
                if (i < namesOfInterfaces.size() - 1) {
                    sb.append(namesOfInterfaces.get(i)).append(", ");
                } else
                    sb.append(namesOfInterfaces.get(i));
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
