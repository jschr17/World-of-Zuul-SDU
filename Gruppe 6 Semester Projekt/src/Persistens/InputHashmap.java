package Persistens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Rasmus Jensen
 */
// we use a FileReader to read a file as a string input, and wrap it in the BufferedReader to get it line by line and then preforms a split at the first occurrence of : in the line
// this code forgoes any error detection based on Wrong syntax in the file, therefore it is important that each change in the file gets cheeked by the ImputFileHashmapTest class before the revisions are shipped

public class InputHashmap  {

    
    private final String filePath = "files/test.txt";     // sets the filepath based on the projectfolder else it can be set from scratch like C:/Users/Documents/......
    private HashMap<String, String> textMap;  // crateas a hasmap for storing keys and thir coresponding string value
    String line;
     
    public InputHashmap()throws IOException{
        createTextMap();
        
    }

    private void createTextMap() throws IOException{
        textMap = new HashMap<>();
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    while ((line = reader.readLine()) != null){
        String[] parts = line.split(":", 2);
        {
            String key = parts[0];
            String value = parts[1];
            textMap.put(key, value);
        }
    }
    }

    public String getText(String key){
        return textMap.get(key);
    }
}
