package apps;

import structures.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Checker {

	private static Dictionary makeDictionary(String fName, FileReader fr){
		Dictionary lang = new Dictionary(fName);
		BufferedReader bufRdr = new BufferedReader(fr);
		StringTokenizer tknzr = null;
		String tknstrm = null;
		
		try{ 
			tknstrm = bufRdr.readLine();
			
		} catch(IOException e){
			System.out.println("Error: IOException on BufferedReader");
			return null;
		}
		
		while(tknstrm != null){
			tknzr = new StringTokenizer(tknstrm);
			
			while(tknzr.hasMoreTokens()){
				lang.addWord(tknzr.nextToken());
			}
			
			try{
				tknstrm = bufRdr.readLine();
				
			}catch(IOException e){
				System.out.println("Error: IOException on BufferedReader");
				return null;
			}
		}
		
		return lang;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		FileReader fr = null;
		String fName;
		
		do{
			System.out.print("Please enter a Language: ");
			fName = input.nextLine();
			
			try{
				fr = new FileReader(fName.toLowerCase() + ".txt");
			} catch(FileNotFoundException e){
				fr = null;
				System.out.println("File not found, please try again.");
			}
		}while(fr == null);
		
		input.close();
		
		Dictionary lang = makeDictionary(fName, fr);
		
		System.out.println("English: "+ lang.toString());
		
	}

}
