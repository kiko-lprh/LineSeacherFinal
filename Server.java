package cop2805;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {
		ServerSocket server = null;
		boolean off = false;
		// int port = ;
		
		// Try to bound port
		try {
			server = new ServerSocket(port);
			System.out.println("port " + port + " succesfully bound, awaiting connections.");
		} catch (Exception e) {
			System.out.println("port not bound!");
			e.printStackTrace();
			System.exit(-1);
		}
	
		
		while(!off){
			// Accept connections & get Input + Output streams			
			try {
				ArrayList<String> clientLines = new ArrayList<String>(); 
				Socket client = server.accept();
				OutputStream output = client.getOutputStream();
				BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				//read user input
				String lineNumber = input.readLine();
				
				// Look for the desired lines with the Search class and send them back to the user
				if (isInt(lineNumber)){
					System.out.print("Client wants line " + lineNumber + "\n\n");
					
								
					Search hamlet = new Search();
					
					clientLines = hamlet.FileSearch(Integer.parseInt(lineNumber));
					if (Integer.parseInt(lineNumber) <= 0 || Integer.parseInt(lineNumber) >= hamlet.total) {
						String response = "Error. Please enter a integer between 1 and " + (hamlet.total - 1);
						output.write(response.getBytes());
					}
					else {
						
						for (String s : clientLines) {
							String response = s + "\n";
							System.out.println(s);
							output.write(response.getBytes());
						}
					}
				}
				else {
					String response = "Error. Please enter a valid positive integer.\n";
					output.write(response.getBytes());
				}
				
				client.close();						
				
			} catch (IOException e) {
				// if client fails to connect
				e.printStackTrace();
				continue;
			}
			
			
		}
		
		

	}
	
	// determines if lineNumber is an integer or not
	public static boolean isInt(String x) {
		try {
			Integer.parseInt(x);
			return true;
		} catch (NumberFormatException e) {
			// cast failed, x isn't a number
			return false;
		}
		
	}

}