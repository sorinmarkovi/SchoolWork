package twoDemArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestBin

{
	static double[][] a = new double[16][16];

	public static void main(String[] args) throws IOException {
		// TODO find the determ. of a 16x16 matrix
		int N = 16;
		fillArray();
		// printArray();
		
		
		System.out.println(determinant(a, N));
		System.out.println("Hek");
	}

	public static double determinant(double[][] a, int N ) {
	       double det=0;
	        if(N == 1)
	        {
	            det = a[0][0];
	        }
	        else if (N == 2)
	        {
	            det = a[0][0]*a[1][1] - a[1][0]*a[0][1];
	        }
	        else
	        {
	            det=0;
	            for(int j1=0;j1<N;j1++)
	            {
	                double[][] m = new double[N-1][];
	                for(int k=0;k<(N-1);k++)
	                {
	                    m[k] = new double[N-1];
	                }
	                for(int i=1;i<N;i++)
	                {
	                    int j2=0;
	                    for(int j=0;j<N;j++)
	                    {
	                        if(j == j1)
	                            continue;
	                        m[i-1][j2] = a[i][j];
	                        j2++;
	                    }
	                }
	                det += Math.pow(-1.0,1.0+j1+1.0)* a[0][j1] * determinant(m,N-1);
	            }
	        }
	        //System.out.println(det);
	        return det;
	}
	public static void fillArray() 
	{
		String fileName = "E:\\Documents\\Git\\SchoolWork\\twoDemArray\\m0016x0016.bin";

		try 
		{
			// Use this for reading the data.
			byte[] buffer = new byte[1000];

			FileInputStream inputStream = new FileInputStream(fileName);

			// read fills buffer with data and returns
			// the number of bytes read (which of course
			// may be less than the buffer size, but
			// it will never be more).
			int total = 0;
			int nRead = 0;
			for (int i = 0; i <= 15; i++) 
			{
				for (int j = 0; j <= 15; j++) 
				{
					a[i][j] = inputStream.read();
				}
			}
			// Always close files.
			inputStream.close();
			//System.out.println("Read " + total + " bytes");
		} 
		catch (FileNotFoundException ex) 
		{
			System.out.println("Unable to open file '" + fileName + "'");
		} 
		catch (IOException ex) 
		{
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
	public static void printArray() 
	{
		for (int i = 0; i <= 15; i++) 
		{
			for (int j = 0; j <= 15; j++) 
			{
				System.out.println(a[i][j]);
			}

		}
	}
}