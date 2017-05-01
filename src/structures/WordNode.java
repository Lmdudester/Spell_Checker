package structures;

public class WordNode {
	public String word;
	public WordNode next;
	
	public WordNode(String word, WordNode next){
		this.word= word;
		this.next = next;
	}
	
	public int compareTo(WordNode b){
		return word.compareTo(b.toString());
	}
	
	public String toString(){
		return "" + word;
	}
	
}
