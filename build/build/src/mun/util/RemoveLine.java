/**
 * 
 */
package mun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * @author cz5670
 *
 */
public class RemoveLine {

	
	public void removeLine(String cayuga,String english) {
		
		String outsingleStirng = english+"     "+cayuga+"\n";
		System.out.println("out="+outsingleStirng);
		try {
	      File inputFile = new File(Constant.dictionaryPath);
          if (!inputFile.isFile()) {
              System.out.println("Parameter is not an existing file");
              return;
          }

          File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
          FileOutputStream fos = new FileOutputStream(tempFile,true);
          OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");
          
          
      	String file = Constant.dictionaryPath;
  		FileInputStream fstream;
  		try {
  			fstream = new FileInputStream(file);
  			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
  			BufferedReader br = new BufferedReader(chars);
  			String strLine;
  			
  			//Read File Line By Line
  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
  				
  				String[] outstring = strLine.split("     ");

  				if(outstring[0].replaceAll("\\p{C}", "").trim().compareTo(cayuga.replaceAll("\\p{C}", "").trim()) != 0||outstring[1].replaceAll("\\p{C}", "").trim().compareTo(english.replaceAll("\\p{C}", "").trim()) !=0) {
  					strLine = String.format("%s%s",strLine,System.getProperty("line.separator"));
  					bw.append(strLine);            	
  				}                  
                else {
                	System.out.println("3="+outsingleStirng);
                }
  			}
  			bw.close();
  			br.close();
  		} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
          
          //Delete the original file
          if (!inputFile.delete()) {
              System.out.println("Could not delete file");
              return;
          }

          //Rename the new file to the filename the original file had.
          if (!tempFile.renameTo(inputFile))
              System.out.println("Could not rename file");
          }
      		catch (FileNotFoundException ex) {
      		ex.printStackTrace();
      }catch (IOException ex) {
          ex.printStackTrace();
      }
		
	}
}
