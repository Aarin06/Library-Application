import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends User{
	final private int pin= 6243;
	
	/**
	  * constructor
	  * pre: none
	  * post: An admin object is initialized
	  */
	public Admin() {
	}
	
	/**
	  * constructor
	  * pre: none
	  * post: An admin object is created initialized by values from parameters. 
	  */
	public Admin(String u, String p,int a) {
		super(u,p,a);
		
	}
	
	/*
	 * Returns special admin pin
	 * pre: none
	 * post: pin is returned
	 */
	public int getPin() {
		return pin;
	}
	
	/*
	 * Returns arraylist of people who checked out items
	 * pre: none
	 * post: arraylist is returned
	 */
	public ArrayList<Integer> getCheckedOut(Library lib[]) {
		ArrayList<Integer> check = new ArrayList<Integer>();
		for (int i = 0 ; i < lib.length ; i++) {
			if (!lib[i].getCheckedOut().equals("no")) {	
				check.add(i);
			}
		}
		return check;
	}
	
	/*
	 * removes an item from library text file
	 * pre: none
	 * post: one item from library text file is removed
	 */
	public void remove(Library lib[],int place) {
		File libFile = new File("lib1.txt");
		File libFileO = new File("lib.txt");
		boolean empty = false;
	
		try {
			FileWriter out= new FileWriter(libFile);
			BufferedWriter writeText = new BufferedWriter(out);
			FileReader in = new FileReader(libFileO);
			BufferedReader readText = new BufferedReader(in);
			String readLine;
			
			while((readLine = readText.readLine()) != null) {
		
				if (readLine.equals("------------------------------")) {
					readLine = readText.readLine();
					if (readLine.equals(lib[place].getName())) {
						while(!empty){
							//System.out.println(readLine);
							if(!readLine.equals("------------------------------")){
								readLine = readText.readLine();
							}
							else {
								empty = true;
								writeText.write("------------------------------");
								writeText.newLine();
							}
							if (readLine == null) {
								empty = true;
							}
						}
						if (!empty) {
							writeText.write("------------------------------");
							writeText.newLine();
						}
					}
					else {
						writeText.write("------------------------------");
						writeText.newLine();
						writeText.write(readLine);
						writeText.newLine();
					}
				}
				else {
					//System.out.print(readLine);
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
	
	/*
	 * adds an item from library text file
	 * pre: none
	 * post: one item from library text file is added
	 */
	public void addI(String name, String year, String genre, String var1, String var2, String var3, int choose, int Fcode) {
		File libFile = new File("lib.txt");
		
		try {
			FileWriter out= new FileWriter(libFile,true);
			BufferedWriter writeText = new BufferedWriter(out);
		
			writeText.write("------------------------------");
			writeText.newLine();
			writeText.write(name);
			writeText.newLine();
			writeText.write(year);
			writeText.newLine();
			writeText.write(genre);
			writeText.newLine();
			writeText.write(String.valueOf(Fcode)); 
			writeText.newLine();
			writeText.write("no");
			writeText.newLine();
			writeText.write(String.valueOf(choose));
			writeText.newLine();
			writeText.write(var1);
			writeText.newLine();
			writeText.write(var2);
			writeText.newLine();
			if (var3 != null) {
				writeText.write(var3);
				writeText.newLine();
			}
			
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
