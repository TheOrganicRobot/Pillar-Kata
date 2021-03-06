package test.java;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeThat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import it.ozimov.cirneco.hamcrest.guava.GuavaMatchers;
import it.ozimov.cirneco.hamcrest.guava.base.IsEquivalent;
import org.hamcrest.collection.IsMapContaining;

import static org.hamcrest.core.IsEqual.equalTo;
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
	private Multimap<Integer, Integer> coordinatesMap;
	private Multimap<Integer, Integer> expectedMap;
	
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
	public void testCreateMatrixOfCharacters2() {
		assertEquals(15, charMatrix.length);
		assertEquals('P', charMatrix[0][10]);
		assertEquals('I', charMatrix[1][10]);
		assertEquals('A', charMatrix[2][10]);
		assertEquals('N', charMatrix[3][10]);
		assertEquals('O', charMatrix[4][10]);
	}
	
	@Test
	public void testSearchWestToEast() {
		expectedMap.put(0, 10);
		expectedMap.put(1, 10);
		expectedMap.put(2, 10);
		expectedMap.put(3, 10);
		expectedMap.put(4, 10);
		
		coordinatesMap = find.searchWestToEast(charMatrix, myWords.get(4));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(5)); //-----Word is PIANO
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));
	
	}
	
	@Test
	public void testSearchEastToWest() {
		expectedMap.put(8, 9);
		expectedMap.put(7, 9);
		expectedMap.put(6, 9);
		expectedMap.put(5, 9);
		expectedMap.put(4, 9);
		expectedMap.put(3, 9);
		expectedMap.put(2, 9);
		expectedMap.put(1, 9);
			
		coordinatesMap = find.searchEastToWest(charMatrix, myWords.get(6));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(8)); //-----Word is BARITONE
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));
	
	}
	
	@Test
	public void testSearchNorthToSouth() {
		expectedMap.put(9, 9);
		expectedMap.put(9, 10);
		expectedMap.put(9, 11);
		expectedMap.put(9, 12);
			
		coordinatesMap = find.searchNorthToSouth(charMatrix, myWords.get(0));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(4)); //-----Word is BASS
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));
	
	}
	
	@Test
	public void testSearchSouthToNorth() {
		expectedMap.put(12, 5);
		expectedMap.put(12, 4);
		expectedMap.put(12, 3);
		expectedMap.put(12, 2);
		expectedMap.put(12, 1);
		expectedMap.put(12, 0);
			
		coordinatesMap = find.searchSouthToNorth(charMatrix, myWords.get(1));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(6)); //-----Word is GUITAR
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));
	
	}
	
	@Test
	public void testSearchNorthWestToSouthEast() {
		expectedMap.put(8, 5);
		expectedMap.put(9, 6);
		expectedMap.put(10, 7);
		expectedMap.put(11, 8);
		expectedMap.put(12, 9);
			
		coordinatesMap = find.searchNorthWestToSouthEast(charMatrix, myWords.get(3));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(5)); //-----Word is DRUMS
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));
	
	}
	
	@Test
	public void testSearchNorthEastToSouthWest() {
		expectedMap.put(11, 0);
		expectedMap.put(10, 1);
		expectedMap.put(9, 2);
		expectedMap.put(8, 3);
		expectedMap.put(7, 4);
		expectedMap.put(6, 5);
		expectedMap.put(5, 6);
		expectedMap.put(4, 7);
			
		coordinatesMap = find.searchNorthEastToSouthWest(charMatrix, myWords.get(5));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(8)); //-----Word is MANDOLIN
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));
	
	}
	
	@Test
	public void testSearchSouthWestToNorthEast() {
		expectedMap.put(0, 7);
		expectedMap.put(1, 6);
		expectedMap.put(2, 5);
		expectedMap.put(3, 4);
		expectedMap.put(4, 3);
			
		coordinatesMap = find.searchSouthWestToNorthEast(charMatrix, myWords.get(2));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(5)); //-----Word is FLUTE
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));
	
	}
	
	@Test
	public void testSearchSouthEastToNorthWest() {
		expectedMap.put(13, 11);
		expectedMap.put(12, 10);
		expectedMap.put(11, 9);
		expectedMap.put(10, 8);
		expectedMap.put(9, 7);
		expectedMap.put(8, 6);
		expectedMap.put(7, 5);
			
		coordinatesMap = find.searchSouthEastToNorthWest(charMatrix, myWords.get(7));
	
		assertThat(coordinatesMap, is(expectedMap));
		assertThat(coordinatesMap.size(), is(7)); //-----Word is TRUMPET
		assertThat(coordinatesMap.entries(), equalTo(expectedMap.entries()));
	
	}
	
}
