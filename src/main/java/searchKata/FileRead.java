
package main.java.searchKata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRead {
	
	public List<String[]> fileReader(String filePath) {
		
		File fileToRead = new File(filePath);
		Scanner scanner = null;
		List<String[]> myList = new ArrayList<String[]>();
		try {
			scanner = new Scanner(fileToRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] array = line.split(",| ");
			myList.add(array);
		}
		return myList;
	}
}
