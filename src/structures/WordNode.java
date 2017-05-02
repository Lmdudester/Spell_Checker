package structures;

public class WordNode {
	public String word;
	public WordNode next;
	
	/**
	 * Creates a WordNode object for a given String and WordNode pointer.
	 * 
	 * @param 	wrd The string of the word for the node
	 * @param	next A pointer to the next node in the list
	 */
	public WordNode(String word, WordNode next){
		this.word= word;
		this.next = next;
	}
	
	/**
	 * Given a WordNode, determines which contains a word lexicographically first.
	 * (Acts as compareTo() would on the string contained in each WordNode)
	 * 
	 * @param cmpNode the WordNode to be compared.
	 * @return The value 0 if the argument WordNode's String is equal to this WordNode's String;
	 *  a value less than 0 if this WordNode's String is lexicographically less than the 
	 *  argument WordNode's String; and a value greater than 0 if this WordNode's String is lexicographically 
	 *  greater than the argument WordNode's String.
	 */
	public int compareTo(WordNode cmpNode){
		return word.compareTo(cmpNode.toString());
	}
	
	/**
	 * Returns a String of the word contained in the WordNode
	 * 
	 * @return	A String of the word contained in the WordNode
	 */
	public String toString(){
		return "" + word;
	}
	
}