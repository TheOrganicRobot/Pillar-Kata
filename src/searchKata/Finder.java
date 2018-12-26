package searchKata;

import java.util.LinkedHashMap;
import java.util.Set;

public class Finder {

	public boolean searchWestToEast(char[][] charMatrix, String wordToSearch) {
		LinkedHashMap<Integer, Integer> matrixPoints  = new LinkedHashMap<Integer, Integer>();
		int i;
		for (int row = 0; row < charMatrix.length; row++) { // ------Loop to pull row out of matrix
			i = 0;
			for (int col = 0; col < charMatrix[row].length; col++) { // ------Loop to pull column out of matrix
				if (wordToSearch.charAt(i) == charMatrix[col][row]) {
					i++;
					matrixPoints.put(col, row);
				} else {
					i = 0;
					matrixPoints = new LinkedHashMap<Integer, Integer>();
				}
				if (i == wordToSearch.length()) {	
					  System.out.print(wordToSearch + ": ");
					  Set <Integer> keys = matrixPoints.keySet();
					  int x = 0;
				    for (Integer key : keys) {
				    	x++;
				    	if(x == wordToSearch.length()) {
				    		System.out.print("(" + key + "," +  matrixPoints.get(key) + ")");
				    	} else {
				        System.out.print("(" + key + "," +  matrixPoints.get(key) + "),");
				    	}
				    }
					
					return true;
				}
			}
		}
		return false;
	}
	public boolean searchEastToWest(char[][] charMatrix, String wordToSearch) {
		LinkedHashMap<Integer, Integer> matrixPoints  = new LinkedHashMap<Integer, Integer>();
		int i;
		for (int row = 0; row < charMatrix.length; row++) { // ------Loop to pull row out of matrix
			i = 0;
			for (int col = charMatrix[row].length - 1; col >= 0; col--) { // ------Loop to pull column out of matrix
				if (wordToSearch.charAt(i) == charMatrix[col][row]) {
					i++;
					matrixPoints.put(col, row);
				} else {
					i = 0;
					matrixPoints = new LinkedHashMap<Integer, Integer>();
				}
				if (i == wordToSearch.length()) {	
					  System.out.print(wordToSearch + ": ");
					  Set <Integer> keys = matrixPoints.keySet();
					  int x = 0;
				    for (Integer key : keys) {
				    	x++;
				    	if(x == wordToSearch.length()) {
				    		System.out.print("(" + key + "," +  matrixPoints.get(key) + ")");
				    	} else {
				        System.out.print("(" + key + "," +  matrixPoints.get(key) + "),");
				    	}
				    }
					
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean searchNorthToSouth() {
		
		return true;
	}
	
}
