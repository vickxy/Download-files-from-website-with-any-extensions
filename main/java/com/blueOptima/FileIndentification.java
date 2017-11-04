package com.blueOptima;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FileIndentification extends Thread {

	
		// TODO Auto-generated method stub
		
		/* this part is for taken user input read from a file at given "H:\\text23.txt" and Write the output at the specified 
		 * Destination  H:\\testout.txt in simple text format using BufferReader and FileWriter..*/

		public void run() 
		{
			
			
		try
		{
		BufferedReader br=new BufferedReader(new FileReader("H:\\text23.txt"));
		FileWriter writer = new FileWriter("H:\\testout.txt");  
	    BufferedWriter bw = new BufferedWriter(writer);
		String[] input = br.readLine().split("\\s+");
		
		
	/*<---------------------------------------------------------------------------------------------------------------------------->
	 * Here we are taking each input as a string and append it with the URL so that we can fetch the exact details of extension
	 *  by using the Jsoup html parser. here all the html tags will be store at Document's Object and each Element of tags will
	 *  be print in text format by using Iterator. 
	 * */	
		
		
		for(int i=0;i<input.length;i++)
		 {
				String str="http://filext.com/file-extension/"+input[i];
				String str1="https://www.reviversoft.com/file-extensions/"+input[i];
				String str3="http://"+input[i]+".extensionfile.net/";
				Document doc1= Jsoup.connect(str).get();
				
				Elements fileType0 = doc1.select("div#file-type");
				if(fileType0.size()>0){
				
					Iterator<Element> it=fileType0.iterator();
					 while(it.hasNext())
					 {
						Element elem=it.next();
						System.out.println(elem.text());
		  				bw.write("*"+elem.text()+"\n");
					  }
					 
							Element elem=doc1.getElementById("extension-notes");
							bw.write(elem.child(6).text()+"\n");
							//bw.newLine();
				}
	/*<-----------------------------end of the first website fetching extension  details------------------------------------------>*/
		
						
   /*<---this iteration process for Second website "https://www.reviversoft.com/file-extensions/ applying the same process as applying  
    * for the first to fetching  the data------------------------------------->*/
		  
		
						Document doc2= Jsoup.connect(str1).get();
						Elements fileType = doc2.select("div[class=ext_info_wrapper]");
						Elements fileType2 = doc2.select("div[class=desc]");
						if(fileType.size()>0 && fileType2.size()>0){
							Element file =  fileType.first();
							Element file2 = file.child(1);
						  
							bw.write("\n");
							for(int k=0;k<file.childNodeSize()/2;k++)
							{
								System.out.println(file.child(k).text());
								bw.write("*"+file.child(k).text()+"\n");
						    for(int j=0;j<file2.childNodeSize()/2 && j==0;j++)
							 {
								System.out.println(file2.child(j).text());
								bw.write("*"+file2.child(j).text());
							 }
									
						         break;
							}
				
							Iterator<Element> itr = fileType2.iterator();
							bw.write("\nDescription:-->\n");
							while(itr.hasNext())
							{
								Element element = itr.next();
								System.out.println(element.text()+"\n");
								bw.write(element.text()+"\n");
							}
							    bw.write("\n"); 
						}
	/*<--------------------------------------end of the second website fetching details--------------------------------------------- >*/
						
						
	 /*<-----Here, We are fetching the file type extension from the  website  http://pd.extensionfile.net<----------------------------->/**/
			
						
						Document doc3= Jsoup.connect(str3).get();
						Elements elements3 = doc3.select("span[id=description]");
						if(elements3.size()>0){
							Element e3 = elements3.first();
							System.out.println(elements3.text());
				
	
					    	//System.out.println(e3.childNodeSize());
					    	StringBuilder sb=new StringBuilder();
				
							//Element e3 = element3.first();
							sb.append(e3.child(1).text()+"\n");
							String s=e3.child(2).text();
							String[] s2=s.split("\\. +");
							
					        for(String ss: s2)
					        {
								sb.append(ss+"\n");
							}
							System.out.println(sb.toString());
							bw.write(sb.toString()+"\n");
		    
						}
			 }
			    bw.flush();
			    bw.close();
/*<---------------------------end of the third website data fetching process ------------------------------------------->*/
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
		
		public static void main(String[] args) 
		{
			    FileIndentification fi = new FileIndentification();
			    fi.start();
			    
	}

}

