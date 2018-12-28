package searchKata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Finder {

	public boolean searchWestToEast(char[][] charMatrix, String wordToSearch) {
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();	
		int i = 0;
		for (int row = 0; row < charMatrix.length; row++) { 
			for (int col = 0; col < charMatrix[row].length; col++) { 
				if (wordToSearch.charAt(i) == charMatrix[col][row]) {
					i++;
					rowList.add(row);
					colList.add(col);
				} else {
					i = 0;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					System.out.print(wordToSearch + ": ");
					int x = 0;
					for(int y = 0; y < rowList.size(); y++) {
						x++;
						if(x == wordToSearch.length()) {
				    		System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + ")\n");
				    	} else {
				        System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + "),");
				    	}
					}
					return true;
				}
			}
		}
		return false;
	}
	public boolean searchEastToWest(char[][] charMatrix, String wordToSearch) {
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();	
		int i = 0;
		for (int row = 0; row < charMatrix.length; row++) {
			for (int col = charMatrix[row].length - 1; col >= 0; col--) { 
				if (wordToSearch.charAt(i) == charMatrix[col][row]) {
					i++;
					rowList.add(row);
					colList.add(col);
				} else {
					i = 0;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					System.out.print(wordToSearch + ": ");
					int x = 0;
					for(int y = 0; y < rowList.size(); y++) {
						x++;
						if(x == wordToSearch.length()) {
				    		System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + ")\n");
				    	} else {
				        System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + "),");
				    	}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean searchNorthToSouth(char[][] charMatrix, String wordToSearch) {
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();		
		int i = 0;
		int j = 0;
		for (int row = 0; row < charMatrix[j].length - 1; row++) { 
			for (int col = 0; col < charMatrix.length; col++) { 
				if (wordToSearch.charAt(i) == charMatrix[row][col]) {
					i++;					
					rowList.add(row);
					colList.add(col);
				} else {
					i = 0;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					System.out.print(wordToSearch + ": ");
					int x = 0;
					for(int y = 0; y < rowList.size(); y++) {
						x++;
						if(x == wordToSearch.length()) {
				    		System.out.print("(" + rowList.get(y) + "," +  colList.get(y) + ")\n");
				    	} else {
				        System.out.print("(" + rowList.get(y) + "," +  colList.get(y) + "),");
				    	}
					}
					return true;
				}
			}
		}
		return false;
	}
	
		public boolean searchSouthToNorth(char[][] charMatrix, String wordToSearch) {
			List<Integer> rowList = new ArrayList<>();
			List<Integer> colList = new ArrayList<>();		
			int i = 0;
			int j = 0;
			for (int row = 0; row < charMatrix[j].length - 1; row++) { 
				for (int col = charMatrix[row].length - 1; col > 0; col--) { 
					if (wordToSearch.charAt(i) == charMatrix[row][col]) {
						i++;					
						rowList.add(row);
						colList.add(col);
					} else {
						i = 0;
						rowList = new ArrayList<>();
						colList = new ArrayList<>();
					}
					if(i == wordToSearch.length()) {	
						System.out.print(wordToSearch + ": ");
						int x = 0;
						for(int y = 0; y < rowList.size(); y++) {
							x++;
							if(x == wordToSearch.length()) {
					    		System.out.print("(" + rowList.get(y) + "," +  colList.get(y) + ")\n");
					    	} else {
					        System.out.print("(" + rowList.get(y) + "," +  colList.get(y) + "),");
					    	}
						}
						return true;
					}
				}
			}
			return false;
		}
		
	public boolean searchNorthWestToSouthEast(char[][] charMatrix, String wordToSearch) {
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();		
		int i = 0;
		int j = 0;
		for (int row = 0; row < charMatrix.length; row++) { 
			j = row;
			for (int col = 0; col < charMatrix[row].length; col++) { 
				if (wordToSearch.charAt(i) == charMatrix[j][col]) {
					rowList.add(j);
					colList.add(col);
					i++;
					j++;				
				} else {
					i = 0;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					System.out.print(wordToSearch + ": ");
					int x = 0;
					for(int y = 0; y < rowList.size(); y++) {
						x++;
						if(x == wordToSearch.length()) {
				    		System.out.print("(" + rowList.get(y) + "," +  colList.get(y) + ")\n");
				    	} else {
				        System.out.print("(" + rowList.get(y) + "," +  colList.get(y) + "),");
				    	}
					}
					return true;
				}
				if(j >= charMatrix.length) {
					break;
				}
			}
		}
		return false;
	}
	
	public boolean searchNorthEastToSouthWest(char[][] charMatrix, String wordToSearch) {
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();		
		int i = 0;
		int j = 0;
		for (int row = 0; row < charMatrix.length; row++) { 
			j = row;
			for (int col = charMatrix[row].length - 1; col >= 0; col--) { 
				if (wordToSearch.charAt(i) == charMatrix[col][j]) {
					rowList.add(j);
					colList.add(col);
					i++;
					j++;				
				} else {
					i = 0;
					j = row;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					System.out.print(wordToSearch + ": ");
					int x = 0;
					for(int y = 0; y < rowList.size(); y++) {
						x++;
						if(x == wordToSearch.length()) {
				    		System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + ")\n");
				    	} else {
				        System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + "),");
				    	}
					}
					return true;
				}
				if(j >= charMatrix.length) {
					break;
				}
			}
		}
		return false;
	}
	
	public boolean searchSouthWestToNorthEast(char[][] charMatrix, String wordToSearch) {
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();		
		int i = 0;
		int j = 0;
		for (int row = 0; row < charMatrix.length; row++) { 
			j = row;
			for (int col = 0; col < charMatrix[row].length; col++) { 
				if (wordToSearch.charAt(i) == charMatrix[col][j]) {
					rowList.add(j);
					colList.add(col);
					i++;
					j--;				
				} else {
					i = 0;
					j = row;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					System.out.print(wordToSearch + ": ");
					int x = 0;
					for(int y = 0; y < rowList.size(); y++) {
						x++;
						if(x == wordToSearch.length()) {
				    		System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + ")\n");
				    	} else {
				        System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + "),");
				    	}
					}
					return true;
				}
				if(j < 0) {
					break;
				}
			}
		}
		return false;
	}
	
	public boolean searchSouthEastToNorthWest(char[][] charMatrix, String wordToSearch) {
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();		
		int i = 0;
		int j = 0;
		for (int row = 0; row < charMatrix.length; row++) { 
			j = row;
			for (int col = charMatrix[row].length - 1; col >= 0 ; col--) { 
				if (wordToSearch.charAt(i) == charMatrix[col][j]) {
					rowList.add(j);
					colList.add(col);
					i++;
					j--;				
					
				} else {
					i = 0;
					j = row;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					System.out.print(wordToSearch + ": ");
					int x = 0;
					for(int y = 0; y < rowList.size(); y++) {
						x++;
						if(x == wordToSearch.length()) {
				    		System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + ")\n");
				    	} else {
				        System.out.print("(" + colList.get(y) + "," +  rowList.get(y) + "),");
				    	}
					}
					return true;
				}
				if(j < 0) {
					break;
				}
			}
		}
		return false;
	}
}
