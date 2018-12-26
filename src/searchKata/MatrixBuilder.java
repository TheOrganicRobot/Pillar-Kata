package searchKata;

import java.util.List;

public class MatrixBuilder {
		
		public char[][] Matricize(List<String[]> myList){
			
			myList.remove(0); //------Removing first position because those are the words to search for
			char[][] matrix = new char[myList.size()][myList.get(0).length];
			String[] row = null;
			
			for(int i = 0; i < myList.size(); i++) {
				row = myList.get(i);
				for(int j = 0; j < row.length; j++) {
					matrix[j][i] = row[j].charAt(0);
				}
			}
			return matrix;
		}
}
