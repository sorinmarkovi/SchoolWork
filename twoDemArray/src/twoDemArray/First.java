package twoDemArray;

public class First {
	static int[][] matrix = new int[16][16];

	public static void main(String[] args) 
	{
		// TODO find the determ. of a 16x16 matrix
		determinant(matrix);
	}

	public static int determinant(int[][] matrix) 
	{
		// code borrowed from http://professorjava.weebly.com/matrix-determinant.html
		// method sig. takes a matrix (two dimensional array), returns determinant.
		int sum = 0;
		int s;
		if (matrix.length == 1) { // bottom case of recursion. size 1 matrix
									// determinant is itself.
			return (matrix[0][0]);
		}
		for (int i = 0; i < matrix.length; i++) { // finds determinant using
													// row-by-row expansion
			int[][] smaller = new int[matrix.length - 1][matrix.length - 1]; // creates smaller matrix- values not in same row, column
			for (int a = 1; a < matrix.length; a++) 
			{
				for (int b = 0; b < matrix.length; b++) 
				{
					if (b < i) 
					{
						smaller[a - 1][b] = matrix[a][b];
					} 
					else if (b > i) 
					{
						smaller[a - 1][b - 1] = matrix[a][b];
					}
				}
			}
			if (i % 2 == 0) 
			{ // sign changes based on i
				s = 1;
			} 
			else 
			{
				s = -1;
			}
			sum += s * matrix[0][i] * (determinant(smaller)); // recursive step: determinant of larger determined by smaller.
		}
		return (sum); // returns determinant value. once stack is finished, returns final determinant.
	}
}