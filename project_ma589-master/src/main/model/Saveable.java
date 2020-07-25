package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Saveable {
    //EFFECTS: saves the information to the file
    void save() throws FileNotFoundException, UnsupportedEncodingException, IOException;
}
