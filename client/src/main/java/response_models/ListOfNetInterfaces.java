package response_models;

import java.util.ArrayList;

public class ListOfNetInterfaces {
    private ArrayList<String> namesOfInterfaces;
    private String error;

    public ListOfNetInterfaces() {
    }

    public ArrayList<String> getNamesOfInterfaces() {
        return namesOfInterfaces;
    }

    public void setNamesOfInterfaces(ArrayList<String> namesOfInterfaces) {
        this.namesOfInterfaces = namesOfInterfaces;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (namesOfInterfaces != null) {
            for (int i = 0; i < namesOfInterfaces.size(); i++) {
                if (i < namesOfInterfaces.size() - 1) {
                    sb.append(namesOfInterfaces.get(i)).append(", ");
                } else
                    sb.append(namesOfInterfaces.get(i));
            }
            return sb.toString();
        } else {
            return error;
        }
    }
}
