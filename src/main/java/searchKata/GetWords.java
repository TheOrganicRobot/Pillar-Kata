package main.java.searchKata;

import java.util.Arrays;
import java.util.List;

public class GetWords {

	public List<String> getWordsToSearch(List<String[]> myList) {
		
		String[] wordsToSearch = myList.get(0);
		List<String> myWords = Arrays.asList(wordsToSearch);
		
		return myWords;
	}
}
