/**
 * 
 */
package mun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


/**
 * @author chenzhang
 *
 */
public class WriteToFile {
	
	String file = Constant.dictionaryPath;
	FileInputStream fstream;
	FileOutputStream fos;
	OutputStreamWriter bw;
	
	public WriteToFile() {
		
		File fout = new File(file);	
		try {
			fos = new FileOutputStream(fout,true);
			bw = new OutputStreamWriter(fos, "UTF-16");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param english
	 * @param cayuga
	 */
	public void addNewItem(String english,String cayuga) {
		
		if(bw != null) {
			
			//String outsingleStirng = english+"     "+cayuga;
			String outsingleStirng = String.format("%s     %s%s",english,cayuga,System.getProperty("line.separator"));
			//System.out.println(outsingleStirng);
			try {
				//bw.write("\n");
				bw.append(outsingleStirng);
				//bw.write(outsingleStirng);
				//bw.write("\n");
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}	
	}
	
}
