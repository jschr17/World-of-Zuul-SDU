/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;

/**
 *
 * @author Rasmus Jensen
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class InputFileToHashmapTest 
{
public static void main( String[] args ) throws IOException
{
    String filePath = "files/test.txt";     // sets the filepath based on the projectfolder else it can be set from scratch like C:/Users/Documents/......
    HashMap<String, String> textMap = new HashMap<>();  // crateas a hasmap for storing keys and thir coresponding string value

    String line;
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    while ((line = reader.readLine()) != null)
    {
        String[] parts = line.split(":", 2);
        if (parts.length >= 2)
        {
            String key = parts[0];
            String value = parts[1];
            textMap.put(key, value);
        } else {
            System.out.println("ignoring line: " + line);
        }
    }

    for (String key : textMap.keySet())
    {
        System.out.println(key + ":" + textMap.get(key));
    }
    reader.close();
}

    
}
