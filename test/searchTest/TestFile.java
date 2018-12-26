package searchTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import searchKata.FileRead;

public class TestFile {


	@Test
	public void testFileRead() {
		FileRead file = new FileRead();
		List<String[]> wordsToSearch = file.fileReader();
		assertEquals(0, wordsToSearch.size());
		
	}

}
