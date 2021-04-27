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
	
	public UserFile() {
			if(!usernameExists()) {
				String username = createRandomUsername();
				saveUsername(username);
			}
	}
	
	public Boolean usernameExists()  {
		String username = readUsernameFile();
		if(username.isEmpty()) {
			return false;
		}
		return true;
	}
    
    public String readUsernameFile() {
  		String username = "";
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fileName));
			username = in.readLine();
			in.close();
			return username;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return username;
    }
    
	public String createRandomUsername() {
        String name = "";
        for (int i = 0; i < numberOfCharacters; i++) {
            int v = 1 + (int) (Math.random() * 26);
            char c = (char) (v + (i == 0 ? 'A' : 'a') - 1);
            name += c;
        }
        return name;
	}
	
    private void saveUsername(String data){
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
