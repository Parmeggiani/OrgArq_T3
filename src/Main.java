import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
      
      static ArrayList<String> binNumbers = new ArrayList();
      
      static ArrayList<String> hitMissA16 = new ArrayList();
      static String [][] mapAssoc16 = new String[16][5];
      
      static ArrayList<String> hitMissA32 = new ArrayList();
      static String [][] mapAssoc32 = new String[32][3];
      
      public static void main(String arg []) throws IOException{
            Memory cache = new Memory();
            
            readFile();
            cache.initializeMatrixAssociative16(mapAssoc16);
            cache.AssociativeMapping16( binNumbers, mapAssoc16, hitMissA16);
            cache.writeOnFile16( binNumbers, mapAssoc16, hitMissA16);
      
            cache.initializeMatrixAssociative32(mapAssoc32);
            cache.AssociativeMapping32( binNumbers, mapAssoc32, hitMissA32);
            cache.writeOnFile32( binNumbers, mapAssoc32, hitMissA32);
            
            
      }
      
      public static void readFile() throws FileNotFoundException {
            File read = new File("binNumbers.txt");
            Scanner file = new Scanner(read);
            int i = 0;
            while(file.hasNextLine()){
                  String str = file.nextLine();
                  binNumbers.add(i,str);
                  i++;
            }
      }
      
}
