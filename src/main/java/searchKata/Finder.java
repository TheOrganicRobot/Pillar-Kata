package main.java.searchKata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

public class Finder {

	public Multimap<Integer, Integer> searchWestToEast(char[][] charMatrix, String wordToSearch) {
		Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
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
					return addToLinkedHashMap(rowList, colList);
				}
			}
		}
		return coordinatesMap;
	}
	public Multimap<Integer, Integer> searchEastToWest(char[][] charMatrix, String wordToSearch) {
		Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
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
					return addToLinkedHashMap(rowList, colList);
				}
			}
		}
		return coordinatesMap;
	}
	
	public Multimap<Integer, Integer> searchNorthToSouth(char[][] charMatrix, String wordToSearch) {
		Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();		
		int i = 0;
		int j = 0;
		for (int row = 0; row < charMatrix[j].length - 1; row++) { 
			for (int col = 0; col < charMatrix.length; col++) { 
				if (wordToSearch.charAt(i) == charMatrix[row][col]) {
					i++;					
					rowList.add(col);
					colList.add(row);
				} else {
					i = 0;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					return addToLinkedHashMap(rowList, colList);
				}
			}
		}
		return coordinatesMap;
	}
	
		public Multimap<Integer, Integer> searchSouthToNorth(char[][] charMatrix, String wordToSearch) {
			Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
			List<Integer> rowList = new ArrayList<>();
			List<Integer> colList = new ArrayList<>();		
			int i = 0;
			int j = 0;
			for (int row = charMatrix[j].length - 1; row > 0; row--) { 
				for (int col = charMatrix[row].length - 1; col >= 0; col--) { 
					if (wordToSearch.charAt(i) == charMatrix[row][col]) {
						i++;					
						rowList.add(col);
						colList.add(row);
					} else {
						i = 0;
						rowList = new ArrayList<>();
						colList = new ArrayList<>();
					}
					if(i == wordToSearch.length()) {	
						return addToLinkedHashMap(rowList, colList);
					}
				}
			}
			return coordinatesMap;
		}
		
	public Multimap<Integer, Integer> searchNorthWestToSouthEast(char[][] charMatrix, String wordToSearch) {
		Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
		List<Integer> rowList = new ArrayList<>();
		List<Integer> colList = new ArrayList<>();		
		int i = 0;
		int j = 0;
		for (int row = 0; row < charMatrix.length; row++) { 
			j = row;
			for (int col = 0; col < charMatrix[row].length; col++) { 
				if (wordToSearch.charAt(i) == charMatrix[j][col]) {
					rowList.add(col);
					colList.add(j);
					i++;
					j++;				
				} else {
					i = 0;
					rowList = new ArrayList<>();
					colList = new ArrayList<>();
				}
				if(i == wordToSearch.length()) {	
					return addToLinkedHashMap(rowList, colList);
				}
				if(j >= charMatrix.length) {
					break;
				}
			}
		}
		return coordinatesMap;
	}
	
	public Multimap<Integer, Integer> searchNorthEastToSouthWest(char[][] charMatrix, String wordToSearch) {
		Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
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
					return addToLinkedHashMap(rowList, colList);
				}
				if(j >= charMatrix.length) {
					break;
				}
			}
		}
		return coordinatesMap;
	}
	
	public Multimap<Integer, Integer> searchSouthWestToNorthEast(char[][] charMatrix, String wordToSearch) {
		Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
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
					return addToLinkedHashMap(rowList, colList);
				}
				if(j < 0) {
					break;
				}
			}
		}
		return coordinatesMap;
	}
	
	public Multimap<Integer, Integer> searchSouthEastToNorthWest(char[][] charMatrix, String wordToSearch) {
		Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
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
					return addToLinkedHashMap(rowList, colList);
				}
				if(j < 0) {
					break;
				}
			}
		}
		return coordinatesMap;
	}
	
	private Multimap<Integer, Integer> addToLinkedHashMap(List<Integer> rowList, List<Integer> colList) {
		Multimap<Integer, Integer> coordinatesMap = LinkedHashMultimap.create();
		for(int y = 0; y < rowList.size(); y++) {
			coordinatesMap.put(colList.get(y), rowList.get(y));
		
		}
		return coordinatesMap;
	}
}
