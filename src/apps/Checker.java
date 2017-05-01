package apps;

import structures.*;

public class Checker {

	public static void main(String[] args) {
		Dictionary eng = new Dictionary("English");	
		
		eng.addWord("0do");
		eng.addWord("1do");
		eng.addWord("2do");
		eng.addWord("3do");
		eng.addWord("4do");
		eng.addWord("5do");
		eng.addWord("6do");
		eng.addWord("7do");
		eng.addWord("8do");
		eng.addWord("9do");
		eng.addWord("ado");
		
		System.out.print("List: " + eng.toString());
		
	}

}
