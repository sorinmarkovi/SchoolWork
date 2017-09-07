package twoDemArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class First 

{
	static int[] [] matrix = new int[16][16];
	public static void main(String[] args) throws IOException 
	{
		// TODO find the determ. of a 16x16 matrix
		determinant(matrix);
		fillArray();
	}
	public static int determinant(int[] [] matrix) 
	{
		// code borrowed from http://professorjava.weebly.com/matrix-determinant.html
		// method sig. takes a matrix (two dimensional array), returns determinant.
		int sum = 0;
		int s;
		if (matrix.length == 1) 
		{ // bottom case of recursion. size 1 matrix determinant is itself.
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
	public static void fillArray()
	{
		String fileName = "m0016x0016.bin";

        try {
            // Use this for reading the data.
            byte[] buffer = new byte[1000];

            FileInputStream inputStream = 
                new FileInputStream(fileName);

            // read fills buffer with data and returns
            // the number of bytes read (which of course
            // may be less than the buffer size, but
            // it will never be more).
            int total = 0;
            int nRead = 0;
            while((nRead = inputStream.read(buffer)) != -1) {
                // Convert to String so we can display it.
                // Of course you wouldn't want to do this with
                // a 'real' binary file.
                System.out.println(new String(buffer));
                total += nRead;
            }   
            // Always close files.
            inputStream.close();        

            System.out.println("Read " + total + " bytes");
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
}