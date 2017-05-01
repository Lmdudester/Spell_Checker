package structures;

import java.util.*;

public class Dictionary {
	String lang;
	HashMap<Integer,WordNode> words;
	
	public Dictionary(String language) {
		lang = language;
		words = new HashMap<Integer,WordNode>(36);
	}	
	
	private int getKey(String s){
		int c = (int) s.charAt(0);
		
		//For 0-9
		if(c >= (int) '0' && c <= (int) '9')
			return c - '0';
		
		//For a-z
		if(c >= (int) 'a' && c <= (int) 'z')
			return c - 'a' + 10;
		
		return -1;
	}
	
	public int addWord(String w){
		w = w.toLowerCase(); //Put the string in lowercase before entering
		
		int key = getKey(w); //Obtain the key for the given string
		
		if(key == -1) //If its not a valid word
			return -1;
		
		//Declare and Init variables
		WordNode ptr = words.get(key);
		WordNode newWord = new WordNode(w,null);
		
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

	public boolean spellCheck(String w){
		w = w.toLowerCase();
		
		int key = getKey(w);
		
		WordNode ptr = words.get(key);
		
		while(ptr != null){
			if((w.toLowerCase()).equals(ptr.toString()))
				return true;
			
			ptr = ptr.next;
		}
		
		return false;
	}

	public String toString(){
		return "" + words.entrySet();
	}
	
}
