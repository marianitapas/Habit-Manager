package model;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    //EFFECTS: loads the information from the file
    ArrayList load() throws IOException;
}
