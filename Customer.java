import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Customer extends User{
	ArrayList<Integer> cart = new ArrayList<Integer>();
	
	/**
	  * constructor
	  * pre: none
	  * post: A customers object is created initialized by values from parameters. 
	  */
	public Customer(String u, String p,int a) {
		super(u,p,a);
		
	}
	
	/*
	 * Returns cart of user
	 * pre: none
	 * post: cart is returned
	 */
	public ArrayList<Integer> getCart() {
		return(cart);
	}
	
	/*
	 * adds an item to cart
	 * pre: none
	 * post: item is added
	 */
	public void addCart(int c) {
		cart.add(c);
	}
	/*
	 * removed an item from cart 
	 * pre: none
	 * post: item is removed
	 */
	public int removeCart(int c) {
		if(!cart.contains(c)) {
			System.out.println("\nThis code given does not match item in cart. Try Again.");
			return(-1);
		}
		else
			cart.remove(cart.indexOf(c));
		return(2);
		//check INPUT
	}
	
	/*
	 * changes checkedOut from users name to "no" to return item
	 * pre: none
	 * post: checkedOut text is changed
	 */
	public void returnItem(Library lib[],int place) {
		File libFile = new File("lib1.txt");
		File libFileO = new File("lib.txt");
	
		try {
			FileWriter out= new FileWriter(libFile);
			BufferedWriter writeText = new BufferedWriter(out);
			FileReader in = new FileReader(libFileO);
			BufferedReader readText = new BufferedReader(in);
			String readLine;
			
			while((readLine = readText.readLine()) != null) {
				if (readLine.equals(String.valueOf(lib[place].getCode()))) {
					writeText.write(readLine);
					writeText.newLine();
					readLine = readText.readLine();
					if (readLine.equals(super.getUsername())) {
						writeText.write("no");
						writeText.newLine();
					}
				}
				else {
					writeText.write(readLine);
					writeText.newLine();
				}
					
			}
			readText.close();
			in.close();
			writeText.close();
			out.close();
		}
		catch(FileNotFoundException e) {	
			System.out.println("File does not exist or could not be found.");
			System.err.println("FileNotFoundException: " + e.getMessage());
		} 
		catch(IOException e) {
			System.out.println("Problem reading file."); 
			System.err.println("IOException: " + e.getMessage());
		}
	}
	
}
