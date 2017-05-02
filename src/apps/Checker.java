package apps;

import structures.Dictionary;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Checker {

	/**
	 * Creates a Dictionary object and populates it with words from a given Dictionary (.txt) file
	 * 
	 * @param fName The name of the file being read (and therefore the language of the Dictionary)
	 * @param fr The FileReader object to be read from (the Dictionary)
	 * @return A Dictionary object containing every valid word in the file
	 */
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
	
	/**
	 * Creates a string containing the line and word number of each misspelled word in the document.
	 * 
	 * @param lang The Dictionary object being used to spell check the document
	 * @param fr The FileReader object to be read from (the Document)
	 * @return A String containing the line and word number of each misspelled word in the Document
	 */
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
		
		do{ //Obtain a valid language from the user
			System.out.print("Please enter a Language: ");
			fName = input.nextLine();
			
			try{ //Try to open the respective language file
				fr = new FileReader(fName.toLowerCase() + ".txt");
			} catch(FileNotFoundException e){
				fr = null;
				System.out.println("Language not found, please try again.");
			}
		}while(fr == null);
		
		Dictionary lang = makeDictionary(fName, fr); //Create and populate the dictionary
		
		do{ //Keep in a loop until user types "exit"
			
			do{ //Keep in a loop while file path in invalid
				
				System.out.print("Please enter a path of a simple text document to spell check (or \"Exit\" to exit): ");
				fName = input.nextLine();
			
				if("exit".equals(fName.toLowerCase())){ //Exit upon "exit"
					input.close();
					return;
				}
				
				try{ //Try to open the file
					fr = new FileReader(new File(fName));
				} catch(FileNotFoundException e){
					fr = null;
					System.out.println("File not found, please try again.\n");
				}
			}while(fr == null);
			
			//Spell check the given file
			System.out.println("\nSpell Check for "+ fName + ":\n" + spellCheckDocument(lang,fr));
	
		}while(true);
	}

}