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
	
	private static String spellCheckDocument(Dictionary lang, FileReader fr){
		StringBuilder retStr = new StringBuilder();
		
		BufferedReader bufRdr = new BufferedReader(fr);
		StringTokenizer tknzr = null;
		String tknstrm = null;
		String token = null;
		
		int line = 1, word = 1;
		
		try{ 
			tknstrm = bufRdr.readLine();
			
		} catch(IOException e){
			System.out.println("Error: IOException on BufferedReader");
			return null;
		}
		
		retStr.append("Mispelled Words:\n");
		
		while(tknstrm != null){
			word = 1;
			tknzr = new StringTokenizer(tknstrm);
			
			while(tknzr.hasMoreTokens()){
				token = tknzr.nextToken();
				if(lang.spellCheck(token) == false){ //Spelled incorrectly
					retStr.append("" + token + " - Line " + line + " Word " + word + "\n");
				}
				word++;
			}
			
			line++;
			try{
				tknstrm = bufRdr.readLine();
			}catch(IOException e){
				System.out.println("Error: IOException on BufferedReader");
				return null;
			}
		}
		
		token = retStr.toString();
		
		if("Mispelled Words:\n".equals(token)){
			return "Spelling Correct\n";
		} else {
			return token;
		}
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
				System.out.println("Language not found, please try again.");
			}
		}while(fr == null);
		
		Dictionary lang = makeDictionary(fName, fr);
		
		do{
		
			do{
				System.out.print("Please enter a path of a simple text document to spell check (or \"exit\" to exit): ");
				fName = input.nextLine();
			
				if("exit".equals(fName.toLowerCase())){
					input.close();
					return;
				}
				
				try{
					fr = new FileReader(new File(fName));
				} catch(FileNotFoundException e){
					fr = null;
					System.out.println("File not found, please try again.");
				}
			}while(fr == null);
			
			System.out.println("\nSpell Check for "+ fName + ":\n" + spellCheckDocument(lang,fr));
	
		}while(true);
	}

}