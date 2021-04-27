package application.Resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserFile {
	private String fileName = "username.txt";
	private int numberOfCharacters = 99;
	private String username = "";
	
	public UserFile() {
		checkUsername();
	}
    
    public String getUsername() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			username = in.readLine();
			in.close();
			return username;
		} catch (IOException e) {
			System.out.println("Cannot find username.txt file!");
			e.printStackTrace();
		}
		return username;
    }

	private void checkUsername()  {
		try {
			if(getUsername().isEmpty()) {
				System.out.println("Creating username.txt with random username...");
				String username = createUsername();
				setUsername(username);
				System.out.println("Created username.txt with username " + username);
			}
		} catch (Exception e) {
			System.out.println("username.txt not set!");
		}
	}
    
	private String createUsername() {
        for (int i = 0; i < numberOfCharacters; i++) {
            int v = 1 + (int) (Math.random() * 26);
            char c = (char) (v + (i == 0 ? 'A' : 'a') - 1);
            username += c;
        }
        return username;
	}
	
    private void setUsername(String data){
        try{
            FileWriter fstream = new FileWriter(fileName, false);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(data + "\n");
            out.close();
        }catch (Exception exception){
            System.err.println("Error: " + exception.getMessage());
        }
    }
}
