/**
 * 
 */
package main.java.algo.matrix;

/**
 *PROBLEM:
 *for 2 arrays A[x][y] and B[y][z], multiple them.
 *OUTPUT:
 *An [x][z] matriz where C[i][j] is the dot product of ith row of A and jth column of B
 *
 */
public class MatrixMultiplication {

	public static void main(String[] args) {
		MatrixMultiplication m = new MatrixMultiplication();
		int a[][] = { { 5, 6, 7 }, { 4, 8, 9 } };
		int b[][] = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
		
		int c[][] = m.matrixMultiply(a, b);
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				System.out.print(" " + c[i][j]);
			}
			System.out.println();
		}

	}
	
	private int[][] matrixMultiply(int[][] a, int[][] b) { 
		int aRow = a.length;
		int aCol = a[0].length;
		int bRow = b.length;
		int bCol = b[0].length;
		
		// col of one matrix must match row of another for matrix multiplication
		if(aCol != bRow)
			return null;
		
		int[][] result = new int[aRow][bCol];
		
		//controls the row position
		for (int i = 0; i < aRow; i++) {
			//controls the column position
			for (int j = 0; j < bCol; j++) {
				//controls the index of which individual cell is being multiplied and added up
				//elements of row of A are multiplied with elements of column of B and then added up for [i][j] cell.
				for (int k = 0; k < aCol; k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return result;
	}
}

/*
 * COMPLEXITY:
 * for 2 arrays [x][y] and [y][z] - the result is [x][z]
 * aRow * bCol + aCol --> x * z * y
 * Thus it is O(fn) = xyz
 * If we assume 3 dimensions are same, then the complexity is n Cube 
 * */
