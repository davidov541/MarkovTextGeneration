import java.io.*;

import java.util.*;


/**
 * Reads in files, analyzes the word choice of the author, and then makes a new output file 
 * using the word choice of the original document, but mixed up.
 *
 * @author David McGinnis and Dane Bennington
 *         Created Oct 3, 2007.
 */
public class Markov {

	private String filestr = new String();
	private int length;
	private int maxwords;
	private int numperline;
	private File infile;
	private Scanner scanin;
	private HashMap<String, MultiSet> hashm;
	private MultiSet currentset;
	private FixedLengthQueue firstWord;
	private FixedLengthQueue wordAssociate;
	private StringBuffer output;
	
	/**
	 *	Constructor as defined in the specification for Markov.
	 *
	 * @param file
	 * @param len
	 * @param max
	 * @param perline
	 */
	public Markov(String file, int len, int max, int perline) {
		this.output = new StringBuffer();
		this.filestr = file;
		this.firstWord = new FixedLengthQueue(len);
		this.length = len + 1;
		this.maxwords = max;
		this.numperline = perline;
		this.infile = new File(this.filestr);
		this.hashm = new HashMap<String, MultiSet>();
		this.currentset = new MultiSet();
		this.wordAssociate = new FixedLengthQueue(len);

		try {
			this.scanin = new Scanner(this.infile);
		} catch (IOException e) {
			System.out.println("Could not find file");
		}
		
		
		if (this.scanin != null) {
			
			for(int i = 2; i < this.length && this.scanin.hasNext(); i++){
				this.wordAssociate.add("NONWORD");
				this.firstWord.add("NONWORD");
			}
			String addedWord = this.scanin.next();
			
			this.wordAssociate.add(addedWord);
			this.firstWord.add(addedWord);
			
			while (this.scanin.hasNext()) {
				String nextWord = this.scanin.next();
				if (!this.hashm.containsKey(this.wordAssociate.toString())) 
					this.hashm.put(this.wordAssociate.toString(), new MultiSet());
				this.hashm.get(this.wordAssociate.toString()).add(nextWord);
				this.wordAssociate.add(nextWord);
			}
			if(!this.hashm.containsValue(this.wordAssociate.toString()))
				this.hashm.put(this.wordAssociate.toString(), new MultiSet());
			this.hashm.get(this.wordAssociate.toString()).add("FILEEND");
		}
		
		FixedLengthQueue currentWordSet = this.firstWord;
		ArrayList nextPoss = new ArrayList();
		String word;
		LinkedList<String> printedWord = new LinkedList<String>();
		
		printedWord.add(this.firstWord.toString());
		
		for(int i = 0; i < this.maxwords && !this.firstWord.toString().contains("FILEEND"); i++){
				this.currentset = this.hashm.get(currentWordSet.toString());
				nextPoss = this.currentset.values();
				word = (String)nextPoss.get((int) (Math.random() * nextPoss.size()));
				currentWordSet.add(word);
				printedWord.add(word);
				printedWord.add(" ");
		}
		
		int index = 0;
		int subdex = 0;
		int charCount = 0;
		StringBuffer removal = new StringBuffer(printedWord.get(0));
		removal = removal.delete(0, ((this.length - 2) * 8));
		removal.trimToSize();
		printedWord.remove(0);
		printedWord.add(0, removal.toString());
		while(index + subdex < printedWord.size()){
			
			charCount += printedWord.get(index + subdex).length();
			subdex++;
			int requiredSpaces = 0;
			if(charCount > this.numperline){
				subdex--;
				charCount -= printedWord.get(index + subdex).length();
				if(printedWord.get(index + subdex - 1) == " "){
					printedWord.remove(index + subdex - 1);
					subdex--;
					requiredSpaces++;
				}
				if(printedWord.get(index + subdex) == " "){
					printedWord.remove(index + subdex);
				}
				
				requiredSpaces += this.numperline - charCount;
				boolean hasSpace = true;
				while(requiredSpaces != 0 && hasSpace == true){
					hasSpace = false;
					for(int i = index; (i < index + subdex) && (requiredSpaces != 0); i++){
						if(printedWord.get(i).toString() == " "){
							hasSpace = true;
							if(Math.random() > 0.5){
							printedWord.set(i, printedWord.get(i).toString() + " ");
							requiredSpaces--;}
						}
					}
				}
					
					for(int i = index; i < subdex + index; i++){
						this.output.append(printedWord.get(i));
						System.out.print(printedWord.get(i));
					}
					index += (subdex);
					subdex = 0;
					System.out.println();
					this.output.append('\n');
					charCount = 0;
					}
				}
			}
	/** This is to output to something other than a console*/
	
	
	public StringBuffer readOut(){
		return this.output;
	}

}