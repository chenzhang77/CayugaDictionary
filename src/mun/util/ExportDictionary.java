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
public class ExportDictionary {

	public ExportDictionary(File outputfile) {
		
		
		try {

	          FileOutputStream fos = new FileOutputStream(outputfile,true);
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
	  				String outsingleStirng = String.format("%s%s",strLine,System.getProperty("line.separator"));
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
	
