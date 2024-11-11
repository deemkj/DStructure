

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */



import java.io.File;
import java.util.Scanner;

public class Reading {
    public static void Load(String fileName) {
        String line = null;
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
         
            s.nextLine();
            while (s.hasNextLine()) {
                line = s.nextLine();
                
                if (line.trim().length() < 3) {
                    System.out.println("Empty line found, skipping this line=" + line);
                    break; 
                }

                System.out.println(line);
                String[] values = line.split(",");
                String x = line.substring(0, line.indexOf(','));
                int id = Integer.parseInt(x.trim());
                String content = line.substring(line.indexOf(',') + 1).trim();
            }
        } catch (Exception e) {
            System.out.println("end of file");
        }
    }

    public static void main(String[] args) {
  Load("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/dataset.csv");

    }
}
