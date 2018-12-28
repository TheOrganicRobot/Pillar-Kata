package searchKata;

import java.util.List;

public class WordSearch {

	public static void main(String[] args) {
//		String filePath = "StarTrekSearch.csv"; //***COMMENT/UNCOMMENT TO TEST DIFFERENT FILES
		String filePath = "InstrumentSearch.csv"; //***COMMENT/UNCOMMENT TO TEST DIFFERENT FILES
		
		Run(filePath);

	}

	private static void Run(String filePath) {
		FileRead file = new FileRead();
		GetWords words = new GetWords();
		MatrixBuilder matrix = new MatrixBuilder();
		Finder find = new Finder();
		
		List<String[]> wordsAndMatrix = file.fileReader(filePath);
		List<String> wordsToSearchList = words.getWordsToSearch(wordsAndMatrix);
		char[][] searchPuzzle = matrix.Matricize(wordsAndMatrix);
		
		for(String word : wordsToSearchList) {
			find.searchWestToEast(searchPuzzle, word);
			find.searchEastToWest(searchPuzzle, word);
			find.searchNorthToSouth(searchPuzzle, word);
			find.searchSouthToNorth(searchPuzzle, word);
			find.searchNorthEastToSouthWest(searchPuzzle, word);
			find.searchNorthWestToSouthEast(searchPuzzle, word);
			find.searchSouthEastToNorthWest(searchPuzzle, word);
			find.searchSouthWestToNorthEast(searchPuzzle, word);
		}
		
	}
}
