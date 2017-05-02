package structures;

import java.util.HashMap;

public class Dictionary {
	String lang;
	private HashMap<Integer,WordNode> words;
	
	/**
	 * Creates a new unpopulated Dictionary object
	 * 
	 * @param language The language of the dictionary
	 */
	public Dictionary(String language) {
		lang = language;
		words = new HashMap<Integer,WordNode>(36);
	}	
	
	/**
	 * Given a string, will return the key for the string.
	 * Key is based on first char. (Only lowercase alphanumeric)
	 * 
	 * @param str The string to find the key of
	 * @return The key if a valid string was given, -1 otherwise
	 */
	private int getKey(String str){
		int c = (int) str.charAt(0);
		
		//For 0-9
		if(c >= (int) '0' && c <= (int) '9')
			return c - '0';
		
		//For a-z
		if(c >= (int) 'a' && c <= (int) 'z')
			return c - 'a' + 10;
		
		return -1;
	}
	
	/**
	 * Given a string, will add that string to the dictionary.
	 * 
	 * @param wrd The string to add to the dictionary
	 * @return 0 if successfully added, -1 otherwise
	 */
	public int addWord(String wrd){
		wrd = wrd.toLowerCase(); //Put the string in lowercase before entering
		
		int key = getKey(wrd); //Obtain the key for the given string
		
		if(key == -1) //If its not a valid word
			return -1;
		
		//Declare and Init variables
		WordNode ptr = words.get(key);
		WordNode newWord = new WordNode(wrd,null);
		
		//If there is no word currently in the list for that char
		if(ptr == null) {
			words.put(key,newWord);
			return 0;
			
		} else { //If there is a word in the list for that char
			if(newWord.compareTo(ptr) <= 0){ //newWord is new front
				newWord.next = ptr;
				words.put(key,newWord);
				return 0;
			}
			
			//newWord belongs later in the list
			WordNode prv = ptr;
			ptr = ptr.next;
			
			while(ptr != null){
				if(newWord.compareTo(ptr) <= 0){
					newWord.next = ptr;
					prv.next = newWord;
					return 0;
				}
				prv = ptr;
				ptr = ptr.next;
			}
			
			//newWord belongs at the end of the list
			prv.next = newWord;
			return 0;		
		}
	}

	/**
	 * Given a string, determine if string is spelled correctly
	 * 	according to the words in the Dictionary.
	 * 
	 * @param wrd The string check the spelling of
	 * @return true if spelled correctly, false otherwise
	 */
	public boolean spellCheck(String wrd){
		wrd = wrd.toLowerCase(); //Put the string in lowercase before entering
		
		int key = getKey(wrd); //Obtain the key for the given string	
		int cmp; //For comparing the strings
		WordNode ptr = words.get(key); //Find the front of LL at the same key
		
		while(ptr != null){ //Search for the word
			cmp = (wrd.toLowerCase()).compareTo(ptr.toString());
			
			if(cmp == 0) //If they are the same
				return true;
			
			if(cmp < 0) //If the string is lexicographically less, stop the search
				return false;
			
			ptr = ptr.next;
		}
		
		return false;
	}

	/**
	 * Given a string, determine if string is spelled correctly
	 * 	according to the words in the Dictionary.
	 * 
	 * @return A string containing the language and first word in each LL of the hash
	 */
	public String toString(){
		return "" + lang + ": " + words.entrySet();
	}
	
}