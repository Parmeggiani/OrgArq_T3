
import java.io.*;
import java.util.ArrayList;

public class Memory {
      
      public void initializeMatrixAssociative16(String[][] matrix){
            for(int i = 0; i<16; i++){
                  matrix[i][0] = "  ";
                  matrix[i][1] = "  ";
                  matrix[i][2] = "  ";
                  matrix[i][3] = "  ";
                  matrix[i][4] = "  ";
            }
      }
      
      public void initializeMatrixAssociative32(String[][] matrix){
            for(int i = 0; i<32; i++){
                  matrix[i][0] = " ";
                  matrix[i][1] = " ";
                  matrix[i][2] = " ";
            }
      }
      
      public void AssociativeMapping16(ArrayList<String> binNumbers, String[][] mapAssoc16, ArrayList<String> hitMissA16){
            int cont = 0;
            int j = 0;
            for(int i = 0; i<binNumbers.size(); i++){
                  
                  String str = binNumbers.get(i);
                  String tag = str.substring(0,14);
                  String data = str.substring(14, 16);
                  
                  if(cont > 15){
                        cont = 0;
                  }
                  
                  if (mapAssoc16[cont][0].equals(tag)) {
                        hitMissA16.add(i,"Hit");
                        cont++;
                        if (data.equals("00")) {
                              mapAssoc16[cont][1] = data;
                        }
                        else if (data.equals("01")) {
                              mapAssoc16[cont][2] = data;
                        }
                        else if (data.equals("10")) {
                              mapAssoc16[cont][3] = data;
                        }
                        else if (data.equals("11")) {
                              mapAssoc16[cont][4] = data;
                        }
                  }
                  else if (mapAssoc16[cont][0].equals("  ") || !mapAssoc16[cont][0].equals(tag)) {
                        while( j < 16){
                              if(mapAssoc16[j][0].equals(tag)){
                                    hitMissA16.add(i,"Hit");
                                    break;
                              }
                              j++;
                        }
                        if(j>=16) {
                              mapAssoc16[cont][0] = tag;
                              hitMissA16.add(i,"Miss");
                              if (data.equals("00")) {
                                    mapAssoc16[cont][1] = data;
                              }
                              else if (data.equals("01")) {
                                    mapAssoc16[cont][2] = data;
                              }
                              else if (data.equals("10")) {
                                    mapAssoc16[cont][3] = data;
                              }
                              else if (data.equals("11")) {
                                    mapAssoc16[cont][4] = data;
                              }
                              cont++;
                        }
                        j =0;
                  }
                  
            }
            
      }
      
      public void AssociativeMapping32(ArrayList<String> binNumbers, String[][] mapAssoc32, ArrayList<String> hitMissA32){
            int cont = 0;
            int j = 0;
            for(int i = 0; i<binNumbers.size(); i++){
                  
                  String str = binNumbers.get(i);
                  String tag = str.substring(0,15);
                  String data = str.substring(15, 16);
                  
                  if(cont > 31){
                        cont = 0;
                  }

                  if (mapAssoc32[cont][0].equals(tag)) {
                        hitMissA32.add("Hit");
                        if (data.equals("0")) {
                              mapAssoc32[cont][1] = data;
                        }
                        else if (data.equals("1")) {
                              mapAssoc32[cont][2] = data;
                        }
                  }
                  
                  else if ( mapAssoc32[cont][0].equals(" ") || !mapAssoc32[cont][0].equals(tag) ) {
                        while(j < 32) {
                              if (mapAssoc32[j][0].equals(tag)) {
                                    hitMissA32.add("Hit");
                                    break;
                              }
                              j++;
                        }
                        if(j >= 32){
                              mapAssoc32[cont][0] = tag;
                              hitMissA32.add("Miss");
                              if (data.equals("0")) {
                                    mapAssoc32[cont][1] = data;
                              }
                              else if (data.equals("1")) {
                                    mapAssoc32[cont][2] = data;
                              }
                              cont++;
                        }
                        j=0;
                  }
            }
      }
      
      public void writeOnFile16(ArrayList<String> binNumbers, String[][] mapAssoc16, ArrayList<String> hitMissA16) throws IOException {
            int perHit16;
            perHit16 = percentageHit(hitMissA16);
            
            FileWriter matrix = new FileWriter("resultadosAssociativo16.txt");
            PrintWriter gravarArq = new PrintWriter(matrix);
            gravarArq.printf("Mapeamento Associativo 16 bits%n");
            gravarArq.printf("Memória Associativa ---           Cache        %n");
            for (int i=0; i < 16; i++) {
                  gravarArq.printf(" [%s]   --- [%s] - [%s] - [%s] - [%s]%n", mapAssoc16[i][0], mapAssoc16[i][1], mapAssoc16[i][2], mapAssoc16[i][3], mapAssoc16[i][4]);
            }
            matrix.close();
      
            FileWriter hitmiss = new FileWriter("HitMissAssociativo16.txt");
            PrintWriter gravArq = new PrintWriter(hitmiss);
            gravArq.printf("Hit e Miss do Mapeamento Associativo 16 bits%n");
            for (int j=0; j < binNumbers.size(); j++) {
                  gravArq.printf("%s ---- %s%n", binNumbers.get(j), hitMissA16.get(j));
            }
            gravArq.printf("Porcentagem de Hit: %2d%n", (perHit16*100)/hitMissA16.size());
            gravArq.printf("Porcentagem de Miss: %2d%n", 100 - ((perHit16*100)/hitMissA16.size()));
            gravArq.println("Numero de Hits: "+perHit16);
            gravArq.println("Numero de Miss: "+(288-perHit16));
            hitmiss.close();
      }
      
      public void writeOnFile32(ArrayList<String> binNumbers, String[][] mapAssoc32, ArrayList<String> hitMissA32) throws IOException {
            int perHit32;
            perHit32 = percentageHit(hitMissA32);
            
            FileWriter matrix = new FileWriter("resultadosAssociativo32.txt");
            PrintWriter gravarArq = new PrintWriter(matrix);
            gravarArq.printf("Mapeamento Associativo 32 bits%n");
            gravarArq.printf("Memória Associativa ---   Cache    %n");
            for (int i=0; i < 16; i++) {
                  gravarArq.printf(" [%s]  --- [%s] - [%s]%n", mapAssoc32[i][0], mapAssoc32[i][1], mapAssoc32[i][2]);
            }
            matrix.close();
            
            FileWriter hitmiss = new FileWriter("HitMissAssociativo32.txt");
            PrintWriter gravArq = new PrintWriter(hitmiss);
            gravArq.printf("Hit e Miss do Mapeamento Associativo 32 bits%n");
            for (int j=0; j < binNumbers.size(); j++) {
                  gravArq.printf("%s ---- %s%n", binNumbers.get(j), hitMissA32.get(j));
            }
            gravArq.printf("Porcentagem de Hit: %2d%n", (perHit32*100)/hitMissA32.size());
            gravArq.printf("Porcentagem de Miss: %2d%n", 100 - ((perHit32*100)/hitMissA32.size()));
            gravArq.println("Numero de Hits: "+perHit32);
            gravArq.println("Numero de Miss: "+(288-perHit32));
            hitmiss.close();
      }
      
      private int percentageHit(ArrayList<String> hitMiss){
            int cont = 0;
            for(int i=0; i < hitMiss.size(); i++){
                  if(hitMiss.get(i).equals("Hit")){
                        cont++;
                  }
            }
            return cont;
      }
      
      
}
