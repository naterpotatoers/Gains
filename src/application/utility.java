package application;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class utility {
	
	//write data to a txt file
    //create the file if no save data yet
    //append if the save already existed
    public static void saveToFile(String data){
        try{
            // Create file
            FileWriter fstream = new FileWriter("out.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);

            out.append(data + "\n");

            //Close the output stream
            out.close();
        }catch (Exception exception){
            System.err.println("Error: " + exception.getMessage());
        }
    }

}
