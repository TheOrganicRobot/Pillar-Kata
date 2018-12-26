package searchTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import searchKata.FileRead;

public class TestFile {

	
	@Test
	public void testFileRead() {
		
		String filePath = "StarTrekSearch.csv";
		FileRead file = new FileRead();
		List<String[]> wordsToSearch = file.fileReader(filePath);
		assertEquals(16, wordsToSearch.size());
		
	}

}
