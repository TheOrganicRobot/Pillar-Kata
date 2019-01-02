package test.java;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.hamcrest.collection.IsMapContaining;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import main.java.searchKata.FileRead;
import main.java.searchKata.Finder;
import main.java.searchKata.GetWords;
import main.java.searchKata.MatrixBuilder;



public class InstrumentTest {
	
	private FileRead file;
	private String filePath;	
	private GetWords wordsToSearch;
	private MatrixBuilder matrix;
	private Finder find;
	
	private List<String[]> fileReadOutput;
	private List<String> myWords;
	private char[][] charMatrix;
	private LinkedHashMap<Integer, Integer> coordinatesMap;
	private LinkedHashMap<Integer, Integer> expectedMap;
	private OutputStream os;
	private PrintStream ps;
	
	@Before
	public void setup() {	
		
		find = new Finder();
		file = new FileRead();
		matrix = new MatrixBuilder();
		wordsToSearch = new GetWords();
			

		filePath = "InstrumentSearch.csv"; 
		
		fileReadOutput = file.fileReader(filePath); //----------- Full list of all arrays and words from file
		myWords = wordsToSearch.getWordsToSearch(fileReadOutput); //----------- List of words to be searched
		charMatrix = matrix.Matricize(fileReadOutput); //----------- Two dimensional array for searching words
		coordinatesMap = new LinkedHashMap<Integer, Integer>();
		expectedMap = new LinkedHashMap<Integer, Integer>();
		os = new ByteArrayOutputStream(); //----------- #1 of capturing system out stream for testing
		ps = new PrintStream(os); //----------- #2 of capturing system out stream for testing
		System.setOut(ps); //----------- #3 of capturing system out stream for testing
	}
	
	@Test
	public void testFileExists() {
		File fileToRead = new File(filePath);
		assertTrue(fileToRead.exists());
	}
	
	@Test
	public void testFileRead() {
		fileReadOutput = file.fileReader(filePath);
		assertEquals(16, fileReadOutput.size());	
	}
	
	@Test
	public void testGetWords() {		
		assertEquals(8, myWords.size());
	}

	@Test
	public void testCreateMatrixOfCharacters2() {
		assertEquals(15, charMatrix.length);
		assertEquals('P', charMatrix[0][10]);
		assertEquals('I', charMatrix[1][10]);
		assertEquals('A', charMatrix[2][10]);
		assertEquals('N', charMatrix[3][10]);
		assertEquals('O', charMatrix[4][10]);
	}
	
	@Test
	public void testSearchWestToEast2() {
		expectedMap.put(0, 10);
		expectedMap.put(1, 10);
		expectedMap.put(2, 10);
		expectedMap.put(3, 10);
		expectedMap.put(4, 10);
		
		coordinatesMap = find.searchWestToEast(charMatrix, myWords.get(4));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(5)); //-----Word is Piano
		
		assertThat(coordinatesMap, IsMapContaining.hasEntry(0,10));
		assertThat(coordinatesMap, IsMapContaining.hasEntry(1,10));
		assertThat(coordinatesMap, IsMapContaining.hasEntry(2,10));
		assertThat(coordinatesMap, IsMapContaining.hasEntry(3,10));
		assertThat(coordinatesMap, IsMapContaining.hasEntry(4,10));
		
	}
	
}
