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
import java.util.ArrayList;

/**
 * @author cz5670
 *
 */
public class ImportDictionary {

	public ImportDictionary(File inputfile,ArrayList<String> dictionaryEnglishList,ArrayList<String> dictionaryCayugaList) {
		
		
		try {
				String file = Constant.dictionaryPath;
				FileOutputStream fos = new FileOutputStream(file,false);
				OutputStreamWriter bw = new OutputStreamWriter(fos, "UTF-16");
	          
	          
				FileInputStream fstream;
				try {
	  				fstream = new FileInputStream(inputfile);
	  				Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
	  				BufferedReader br = new BufferedReader(chars);
	  				String strLine;
	  				//Read File Line By Line
//	  				while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
//	  					String[] outstring = strLine.split("     ");
////	  					String outsingleStirng = String.format("%s%s",strLine,System.getProperty("line.separator"));
//	  					String outsingleStirng = String.format("%s     %s%s",outstring[0].replaceAll("\\p{C}", "").trim(),outstring[1].replaceAll("\\p{C}", "").trim(),System.getProperty("line.separator"));
//	  					bw.append(outsingleStirng); 
//	  				}
	  				
	  				
	  				
	  	  			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
	  	  				
	  	  				String[] outstring = strLine.split("     ");

	  	  					if(dictionaryCayugaList.contains(outstring[0].replaceAll("\\p{C}", "").trim()) &&
	  	  							dictionaryEnglishList.contains(outstring[1].replaceAll("\\p{C}", "").trim())) {
	  	  					
	  	  						dictionaryCayugaList.remove(outstring[0].replaceAll("\\p{C}", "").trim());
	  	  						dictionaryEnglishList.remove(outstring[1].replaceAll("\\p{C}", "").trim());
	  	  					
	  	  					}
	  	  					
	  	  				String outsingleStirng = String.format("%s     %s%s",outstring[0].replaceAll("\\p{C}", "").trim(),outstring[1].replaceAll("\\p{C}", "").trim(),System.getProperty("line.separator"));
	  					bw.append(outsingleStirng);
	  	  				
	  	  
	  	  			}
	  				
	  				int length = dictionaryCayugaList.size();
	  				
	  				for (int i=0; i< length; i++) {
	  					
	  					
	  					String outsingleStirng = String.format("%s     %s%s",dictionaryCayugaList.get(i).trim().toString(),dictionaryEnglishList.get(i).trim().toString(),System.getProperty("line.separator"));
	  					bw.append(outsingleStirng);
	  					
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
	      }catch (IOException ex) {
	          ex.printStackTrace();
	      }
		
		
		
	}
	
}
