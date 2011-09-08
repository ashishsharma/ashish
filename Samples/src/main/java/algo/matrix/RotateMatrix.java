package main.java.algo.matrix;

public class RotateMatrix {

	public static void main(String[] args) {
		RotateMatrix m = new RotateMatrix();

		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };

		//int[][] rotatedMatrix = m.rotateInPlace(matrix, 4);
		
		m.rotateInPlace(matrix, 4);

		for (int i = 0; i < 4; i++) {
			System.out.println(System.getProperty("line.separator"));
			for (int j = 0; j < 4; j++) {
				System.out.println(matrix[i][j]);
			}
		}

	}

	public void rotateInPlace(int[][] m, int n) {

		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n / 2; j++) {

				int tmp = m[i][j];
				m[i][j] = m[j][n - 1 - i];
				m[j][n - 1 - i] = m[n - 1 - i][n - 1 - j];
				m[n - 1 - i][n - 1 - j] = m[n - 1 - j][i];
				m[n - 1 - j][i] = tmp;
			}
		}
	}

	public int[][] rotate(int[][] matrix, int n) {

		int[][] newMatrix = new int[4][4];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				// when rotated - every cell [row][col] will move to [col][end - row] 
				
				newMatrix[j][n - 1 - i] = matrix[i][j];
			}
		}

		return newMatrix;

	}
}
