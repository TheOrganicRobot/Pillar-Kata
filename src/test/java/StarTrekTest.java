package test.java;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import main.java.searchKata.FileRead;
import main.java.searchKata.Finder;
import main.java.searchKata.GetWords;
import main.java.searchKata.MatrixBuilder;



public class StarTrekTest {
	
	private FileRead file;
	private String filePath;	
	private GetWords wordsToSearch;
	private MatrixBuilder matrix;
	private Finder find;
	
	private List<String[]> fileReadOutput;
	private List<String> myWords;
	private char[][] charMatrix;
	private Multimap<Integer, Integer> coordinatesMap;
	private Multimap<Integer, Integer> expectedMap;
	
	@Before
	public void setup() {	
		
		find = new Finder();
		file = new FileRead();
		matrix = new MatrixBuilder();
		wordsToSearch = new GetWords();
			
		filePath = "StarTrekSearch.csv";

		
		fileReadOutput = file.fileReader(filePath); //----------- Full list of all arrays and words from file
		myWords = wordsToSearch.getWordsToSearch(fileReadOutput); //----------- List of words to be searched
		charMatrix = matrix.Matricize(fileReadOutput); //----------- Two dimensional array for searching words
		
		coordinatesMap = LinkedHashMultimap.create();
		expectedMap = LinkedHashMultimap.create();
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
	public void testCreateMatrixOfCharacters() {
		assertEquals(15, charMatrix.length);
		assertEquals('S', charMatrix[0][5]);
		assertEquals('C', charMatrix[1][5]);
		assertEquals('O', charMatrix[2][5]);
		assertEquals('T', charMatrix[3][5]);
		assertEquals('T', charMatrix[4][5]);
		assertEquals('Y', charMatrix[5][5]);
	}
	
	@Test
	public void testSearchWestToEast() {
		expectedMap.put(0, 5);
		expectedMap.put(1, 5);
		expectedMap.put(2, 5);
		expectedMap.put(3, 5);
		expectedMap.put(4, 5);
		expectedMap.put(5, 5);
		
		coordinatesMap = find.searchWestToEast(charMatrix, myWords.get(3));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(6)); //-----Word is SCOTTY
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));	
	}
	
	@Test
	public void testSearchEastToWest() {
		expectedMap.put(4, 7);
		expectedMap.put(3, 7);
		expectedMap.put(2, 7);
		expectedMap.put(1, 7);
		
		coordinatesMap = find.searchEastToWest(charMatrix, myWords.get(2));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(4)); //-----Word is KIRK
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));	
	}
	
	@Test
	public void testSearchNorthToSouth() {
		expectedMap.put(0, 6);
		expectedMap.put(0, 7);
		expectedMap.put(0, 8);
		expectedMap.put(0, 9);
		expectedMap.put(0, 10);
		
		coordinatesMap = find.searchNorthToSouth(charMatrix, myWords.get(0));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(5)); //-----Word is BONES
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));	
	}
}