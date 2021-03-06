import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * This is a training aid for the MuliSet Class.
 *
 * @author Matthew Boutell
 */
public class TestMultiSet {
	
	
	
/**
 * 
 * Shows functionality of MultiSet.
 *
 * @param args
 */
  public static void main(String[] args) {
	  
	File input = new File("Text Files\\testCase.txt");
	
	Scanner scan = null;
	try{scan = new Scanner(input);}
	catch(Exception e){
		System.out.println("Error: File does not exist.");
	}
	
    MultiSet t = new MultiSet();
	
	while(scan.hasNext()){
		t.add(scan.next());
	}

	scan.close();
	
	System.out.println(t.getMultiplicity(new String ("abcde")));
	
    t.add("Washington G.");
    t.add("Jefferson T.");
    t.add("Lincoln A.");
    t.add("Roosevelt T.");
    t.add("Reagan R.");
    System.out.println("Items in order:");
    for (int i = 0; i < t.size(); i++) {
    	System.out.println(t.findKth(i));
    }
    System.out.println();
    t.add("Washington G.");
    t.add("Jefferson T.");
    t.add("Lincoln A.");
    t.add("Roosevelt T.");
    t.add("Reagan R.");
    System.out.println("Items in order:");
    for (int i = 0; i < t.size(); i++) {
    	System.out.println(t.findKth(i));
    }
    System.out.println();
    for (int i = 0; i < 100; i++) {
    	t.add("Lincoln A.");
    }
    System.out.println("Number of items stored:");
    System.out.println(t.numEntries());
    System.out.println();
    ArrayList a = t.values();
    System.out.println("Items actually stored:");
    for (int i = 0; i < a.size(); i++) {
    	System.out.println(a.get(i)+" has multiplicity "+t.getMultiplicity(a.get(i)));
    }
    System.out.println();
    System.out.println("Out of bounds items k=-1 and k=1001:");
    System.out.println(t.findKth(-1));
    System.out.println(t.findKth(1001));
  }
}