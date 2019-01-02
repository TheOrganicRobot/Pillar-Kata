package test.java;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.searchKata.FileRead;
import main.java.searchKata.Finder;
import main.java.searchKata.GetWords;
import main.java.searchKata.MatrixBuilder;



public class TestFile {
	
	private FileRead file;
	private String filePath;	
	private GetWords wordsToSearch;
	private MatrixBuilder matrix;
	private Finder find;
	
	private List<String[]> fileReadOutput;
	private List<String> myWords;
	private char[][] charMatrix;
	private OutputStream os;
	private PrintStream ps;
	
	@Before
	public void setup() {	
		
		find = new Finder();
		file = new FileRead();
		matrix = new MatrixBuilder();
		wordsToSearch = new GetWords();
			
		filePath = "StarTrekSearch.csv"; //----------- Test file location for StarTrekSearch.csv ****COMMENT/UNCOMMENT THIS LINE AND SUBSEQUENT TESTS FOR TESTING****
//		filePath = "InstrumentSearch.csv"; //----------- Test file location for InstrumentSearch.csv ****COMMENT/UNCOMMENT THIS LINE AND SUBSEQUENT TESTS FOR TESTING****
		
		fileReadOutput = file.fileReader(filePath); //----------- Full list of all arrays and words from file
		myWords = wordsToSearch.getWordsToSearch(fileReadOutput); //----------- List of words to be searched
		charMatrix = matrix.Matricize(fileReadOutput); //----------- Two dimensional array for searching words
		
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

	//--------------------------------------------BEGIN TEST FOR STARTREKSEARCH.CSV---COMMENT/UNCOMMENT FOR TESTING THAT FILE-----------
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
			assertTrue(find.searchWestToEast(charMatrix, myWords.get(3)));
			assertFalse(find.searchWestToEast(charMatrix, myWords.get(0)));
			assertFalse(find.searchWestToEast(charMatrix, myWords.get(1)));
			assertFalse(find.searchWestToEast(charMatrix, myWords.get(2)));
			assertFalse(find.searchWestToEast(charMatrix, myWords.get(4)));
			assertFalse(find.searchWestToEast(charMatrix, myWords.get(5)));
			assertFalse(find.searchWestToEast(charMatrix, myWords.get(6)));
			assertFalse(find.searchWestToEast(charMatrix, myWords.get(7)));
			assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)\n", os.toString());		
		}
		
		@Test
		public void testSearchEastToWest() {
			assertTrue(find.searchEastToWest(charMatrix, myWords.get(2)));
			assertFalse(find.searchEastToWest(charMatrix, myWords.get(0)));
			assertFalse(find.searchEastToWest(charMatrix, myWords.get(1)));
			assertFalse(find.searchEastToWest(charMatrix, myWords.get(3)));
			assertFalse(find.searchEastToWest(charMatrix, myWords.get(4)));
			assertFalse(find.searchEastToWest(charMatrix, myWords.get(5)));
			assertFalse(find.searchEastToWest(charMatrix, myWords.get(6)));
			assertFalse(find.searchEastToWest(charMatrix, myWords.get(7)));
			assertEquals("KIRK: (4,7),(3,7),(2,7),(1,7)\n", os.toString());
		}
		
		@Test
		public void testSearchNorthToSouth() {
			assertTrue(find.searchNorthToSouth(charMatrix, myWords.get(0)));
			assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(1)));
			assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(2)));
			assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(3)));
			assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(4)));
			assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(5)));
			assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(6)));
			assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(7)));
			assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)\n", os.toString());
		}
		
		@Test
		public void testSearchSouthToNorth() {
			assertTrue(find.searchSouthToNorth(charMatrix, myWords.get(1)));
			assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(0)));
			assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(2)));
			assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(3)));
			assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(4)));
			assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(5)));
			assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(6)));
			assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(7)));
			assertEquals("KHAN: (5,9),(5,8),(5,7),(5,6)\n", os.toString());
		}

		@Test
		public void testSearchNorthWestToSouthEast() {
			assertTrue(find.searchNorthWestToSouthEast(charMatrix, myWords.get(4)));
			assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(0)));
			assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(1)));
			assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(2)));
			assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(3)));
			assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(5)));
			assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(6)));
			assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(7)));
			assertEquals("SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)\n", os.toString());
		}
		
		@Test
		public void testSearchNorthEastToSouthWest() {
			
			assertTrue(find.searchNorthEastToSouthWest(charMatrix, myWords.get(6)));
			assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(0)));
			assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(1)));
			assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(2)));
			assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(3)));
			assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(4)));
			assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(5)));
			assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(7)));	
			assertEquals("UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)\n", os.toString());
		}
		
		@Test
		public void testSearchSouthWestToNorthEast() {
			assertTrue(find.searchSouthWestToNorthEast(charMatrix, myWords.get(7)));
			assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(0)));
			assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(1)));
			assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(2)));
			assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(3)));
			assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(4)));
			assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(5)));
			assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(6)));
			assertEquals("CRUSHER: (5,14),(6,13),(7,12),(8,11),(9,10),(10,9),(11,8)\n", os.toString());
		}
		
		@Test
		public void testSearchSouthEastToNorthWest() {
			assertTrue(find.searchSouthEastToNorthWest(charMatrix, myWords.get(5)));
			assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(0)));
			assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(1)));
			assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(2)));
			assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(3)));
			assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(4)));
			assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(6)));
			assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(7)));
			assertEquals("SULU: (3,3),(2,2),(1,1),(0,0)\n", os.toString());
		}
		//--------------------------------------------END TEST FOR STARTREKSEARCH.CSV---COMMENT/UNCOMMENT FOR TESTING THAT FILE-----------
			
	
//	//--------------------------------------------BEGIN TEST FOR INSTRUMENTSEARCH.CSV---COMMENT/UNCOMMENT FOR TESTING THAT FILE-----------
//	@Test
//	public void testCreateMatrixOfCharacters2() {
//		assertEquals(15, charMatrix.length);
//		assertEquals('P', charMatrix[0][10]);
//		assertEquals('I', charMatrix[1][10]);
//		assertEquals('A', charMatrix[2][10]);
//		assertEquals('N', charMatrix[3][10]);
//		assertEquals('O', charMatrix[4][10]);
//	}
//	
//	@Test
//	public void testSearchWestToEast2() {
//		assertTrue(find.searchWestToEast(charMatrix, myWords.get(4)));
//		assertFalse(find.searchWestToEast(charMatrix, myWords.get(0)));
//		assertFalse(find.searchWestToEast(charMatrix, myWords.get(1)));
//		assertFalse(find.searchWestToEast(charMatrix, myWords.get(2)));
//		assertFalse(find.searchWestToEast(charMatrix, myWords.get(3)));
//		assertFalse(find.searchWestToEast(charMatrix, myWords.get(5)));
//		assertFalse(find.searchWestToEast(charMatrix, myWords.get(6)));
//		assertFalse(find.searchWestToEast(charMatrix, myWords.get(7)));
//		assertEquals("PIANO: (0,10),(1,10),(2,10),(3,10),(4,10)\n", os.toString());				
//	}
//	
//	@Test
//	public void testSearchEastToWest2() {
//		assertTrue(find.searchEastToWest(charMatrix, myWords.get(6)));
//		assertFalse(find.searchEastToWest(charMatrix, myWords.get(0)));
//		assertFalse(find.searchEastToWest(charMatrix, myWords.get(1)));
//		assertFalse(find.searchEastToWest(charMatrix, myWords.get(2)));
//		assertFalse(find.searchEastToWest(charMatrix, myWords.get(3)));
//		assertFalse(find.searchEastToWest(charMatrix, myWords.get(4)));
//		assertFalse(find.searchEastToWest(charMatrix, myWords.get(5)));
//		assertFalse(find.searchEastToWest(charMatrix, myWords.get(7)));
//		assertEquals("BARITONE: (8,9),(7,9),(6,9),(5,9),(4,9),(3,9),(2,9),(1,9)\n", os.toString());
//	}
//	
//	@Test
//	public void testSearchNorthToSouth2() {
//		assertTrue(find.searchNorthToSouth(charMatrix, myWords.get(0)));
//		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(1)));
//		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(2)));
//		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(3)));
//		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(4)));
//		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(5)));
//		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(6)));
//		assertFalse(find.searchNorthToSouth(charMatrix, myWords.get(7)));
//		assertEquals("BASS: (9,9),(9,10),(9,11),(9,12)\n", os.toString());
//	}
//	
//	@Test
//	public void testSearchSouthToNorth2() {
//		assertTrue(find.searchSouthToNorth(charMatrix, myWords.get(1)));
//		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(0)));
//		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(2)));
//		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(3)));
//		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(4)));
//		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(5)));
//		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(6)));
//		assertFalse(find.searchSouthToNorth(charMatrix, myWords.get(7)));
//		assertEquals("GUITAR: (12,5),(12,4),(12,3),(12,2),(12,1),(12,0)\n", os.toString());
//	}
//	
//	@Test
//	public void testSearchNorthWestToSouthEast2() {
//		assertTrue(find.searchNorthWestToSouthEast(charMatrix, myWords.get(3)));
//		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(0)));
//		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(1)));
//		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(2)));
//		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(4)));
//		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(5)));
//		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(6)));
//		assertFalse(find.searchNorthWestToSouthEast(charMatrix, myWords.get(7)));
//		assertEquals("DRUMS: (8,5),(9,6),(10,7),(11,8),(12,9)\n", os.toString());
//	}
//	
//	@Test
//	public void testSearchNorthEastToSouthWest2() {
//		
//		assertTrue(find.searchNorthEastToSouthWest(charMatrix, myWords.get(5)));
//		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(0)));
//		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(1)));
//		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(2)));
//		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(3)));
//		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(4)));
//		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(6)));
//		assertFalse(find.searchNorthEastToSouthWest(charMatrix, myWords.get(7)));	
//		assertEquals("MANDOLIN: (11,0),(10,1),(9,2),(8,3),(7,4),(6,5),(5,6),(4,7)\n", os.toString());
//	}
//	
//	@Test
//	public void testSearchSouthWestToNorthEast2() {
//		assertTrue(find.searchSouthWestToNorthEast(charMatrix, myWords.get(2)));
//		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(0)));
//		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(1)));
//		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(3)));
//		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(4)));
//		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(5)));
//		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(6)));
//		assertFalse(find.searchSouthWestToNorthEast(charMatrix, myWords.get(7)));
//		assertEquals("FLUTE: (0,7),(1,6),(2,5),(3,4),(4,3)\n", os.toString());
//	}
//	
//	@Test
//	public void testSearchSouthEastToNorthWest2() {
//		assertTrue(find.searchSouthEastToNorthWest(charMatrix, myWords.get(7)));
//		assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(0)));
//		assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(1)));
//		assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(2)));
//		assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(3)));
//		assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(4)));
//		assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(5)));
//		assertFalse(find.searchSouthEastToNorthWest(charMatrix, myWords.get(6)));
//		assertEquals("TRUMPET: (13,11),(12,10),(11,9),(10,8),(9,7),(8,6),(7,5)\n", os.toString());
//	}
//	//--------------------------------------------END TEST FOR INSTRUMENTSEARCH.CSV---COMMENT/UNCOMMENT FOR TESTING THAT FILE-----------
		
	
}