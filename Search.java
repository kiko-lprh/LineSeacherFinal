package cop2805;
import java.io.*;
import java.util.*;

public class Search {

	int total;
	
	public ArrayList<String> FileSearch(int lineWanted){
		
		ArrayList<String> requestedLines = new ArrayList<String>();
		
		//Attempt to open file
				try {
					BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\corde\\Downloads\\hamlet.txt"));
					String line = reader.readLine();
					int lineNumber = 1;
					
					//Reads through file until it finds requested lines
					while(line != null) {
						if (lineNumber >= lineWanted - 2 && lineNumber <= lineWanted + 2) {
							requestedLines.add(line);
						}
						line = reader.readLine();
						lineNumber++;
					}
					reader.close();
					
					total = lineNumber;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		return requestedLines;
	}
	
	public int getNumberOfLines() {
		return total;
	}
}
