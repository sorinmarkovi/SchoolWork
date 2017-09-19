import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextTest1 {

	public static void main(String[] args) throws FileNotFoundException 
	{
		// TODO Auto-generated method stub
		Scanner s = new Scanner(new File("C:\\Users\\mcdan\\Desktop\\Git\\SchoolWork\\ArrayList16x16\\map.txt.txt"));
		String[][] map = new String[8][8];
		while (s.hasNext()) {
		    String value = s.next();
		    int x = s.nextInt();
		    int y = s.nextInt();
		    map[x][y] = value;
		}
	}

}
