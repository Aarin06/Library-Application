import java.io.*;
import java.util.*;

public class Client {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choose=0,accessLevel=0,last;
		String username="",password="";

		User tempUser = null;
		Admin tempAdmin = new Admin();
		int Fcode=0;
		int pin = tempAdmin.getPin();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		File libFile = new File("lib.txt");
		Library [] library = new Library[libSize(libFile)];
		library = createLib();
		
		Fcode = findCode(Fcode,library);
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
		line();
		System.out.println("\t\t\t\t     Welcome to Aarin's Library!");
		do {
		choose = welcome(choose);
		if(choose==1) {
			accessLevel = access();
	
			if (accessLevel != 0) {
				tempUser = login(accessLevel,username,password);
				if (tempUser.getUsername().equals("0") && tempUser.getPassword().equals("0")){
					choose = 1;	
				}
				else {
					choose = 0;
				}
			}
		}
		if (choose == 2) {
			createAcc(username,password,pin);
		}	
		
		
		}while (choose!=0);
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Customer Interface
		if (accessLevel == 1 && !tempUser.getUsername().equals("0")){
			System.out.println("\n\t\t\t\t  You have Succesfully Logged in!");
			line();
			Customer user = new Customer(tempUser.getUsername(),tempUser.getPassword(),tempUser.getAccessLevel());
			Admin admin = null;
			do {
				System.out.print("\t\t\t    Welcome "+user.getUsername()+", here are your following options:\n"
					+ "\n   1. Search for an Item"
					+ "    2. Browse"
					+ "\t  3. Review Cart  "
					+ "    4. Return Item"
					+ "\t   0. Log Out"
					+ "\n\nChoose an option: ");
				choose = input.nextInt();
				if (choose == 1) {
					search(library,user,admin);
				}
				else if (choose == 2) {
					sort(library,user,admin);
				}
				else if (choose == 3) {
					last = checkout(user,library);
					if (last == 0) {
						choose = 0;
					}
					else {
						line();
					}
				}
				else if (choose == 4) {
					library = returnItem(user,library);
				}
				else if (choose != 0) {
					line();
					System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
					line();
				}
			}while (choose != 0);
					
		}
		//Admin Interface
		if (accessLevel == 2 && !tempUser.getUsername().equals("0")){
			System.out.println("\nYou have Succesfully Logged in!");
			line();
			Customer user = null;
			Admin admin = new Admin(tempUser.getUsername(),tempUser.getPassword(),tempUser.getAccessLevel());
			do {
				System.out.print("\t\t\t    Welcome "+admin.getUsername()+", here are your following options:\n"
					+ "\n\t\t1. Search for an Item"
					+ "\t2. Browse"
					+ "\t3. Display Checked Out"
					+ "\n\t\t4. Remove Item"
					+ "\t\t5. Add Item"
					+ "\t0. Exit"
					+ "\n\nChoose an option: ");
				choose = input.nextInt();
				if (choose == 1) {
					search(library,user,admin);
				}
				else if (choose == 2) {
					sort(library,user,admin);
				}
				else if (choose == 3) {		
					displayCheckedOut(library,admin);
				}
				else if (choose == 4) {
					library = removeItem(library,admin);
					
				}
				else if (choose == 5) {
					library = addItem(library,admin,Fcode);
					Fcode = findCode(Fcode,library);
				}
				else if (choose != 0) {
					line();
					System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
					line();
				}
			}while (choose != 0);
					
		}
		line();

	}	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	/*
	 * Prints a line break to divide parts of the program
	 * pre: none
	 * post: prints a series of asterisks and enters a new line
	 */
	public static void line(){
		System.out.println("\n****************************************************************************************************");
	}
	
	/*
	 * Prints all objects inside library array
	 * pre: none
	 * post: library is printed
	 */
	public static void printLib(Library lib[]) {
		String arr[][] = new String[lib.length][6];
		
		
		for(int i = 0 ; i < lib.length; i++) {
			arr[i][0] = String.valueOf(i);
			arr[i][1] = lib[i].getTypeName();
			arr[i][2] = lib[i].getName();
			arr[i][3] = String.valueOf(lib[i].getYear());
			arr[i][4] = lib[i].getGenre();
			arr[i][5] = String.valueOf(lib[i].getCode());
		}
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
		System.out.print("◊◊ XX ◊◊  Type  ◊◊ Title\t\t\t\t◊◊ Year ◊◊ Genre\t\t◊◊ Code  ◊◊");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
		for(int i = 0 ; i < lib.length ; i++) {
			System.out.print("|| ");
			for(int j = 0 ; j < 6 ; j++) {
				System.out.print(arr[i][j]);
				if (j==0) {
					if (arr[i][j].length() == 1) {
						System.out.print("  || ");
					}
					else {
						System.out.print(" || ");
					}
				}
				else if (j==2) {
					if (arr[i][j].length() <=4) {
						System.out.print("\t\t\t\t\t|| ");
					}
					else if (arr[i][j].length() <=12) {
						System.out.print("\t\t\t\t|| ");
					}
					else if (arr[i][j].length() <=20) {
						System.out.print("\t\t\t|| ");
					}
					else if (arr[i][j].length() <=29) {
						System.out.print("\t\t|| ");
					}
					else {
						System.out.print("\t|| ");
					}
				}
				else if (j==4) {
					if (arr[i][j].length() <= 12) {
						System.out.print("\t\t|| ");
					}
					else {
						System.out.print("\t|| ");
					}
				}
				else {
					System.out.print(" || ");
				}
			}
			System.out.println("\n ————————————————————————————————————————————————————————————————————————————————————————————————— ");
		}
		
		//length=1 NEED: 6
		//length<=9 NEED: 5
		//length <= 17 NEED:4
		//length <= 25 NEED:3
		//length <= 33 NEED:2
		//else NEED: 1

		//length<=5 NEED: 2
		//else need 1

		
		
	}
	
	/*
	 * Establishes the library by loading all info from text file
	 * into an array of library objects
	 * pre: none
	 * post: library is created
	 */
	public static Library[] createLib() {
		File libFile = new File("lib.txt");
		Library [] library = new Library[libSize(libFile)];

		try {
			FileReader in = new FileReader(libFile);
			BufferedReader readText = new BufferedReader(in);
			String textLine,name,genre,director,author,mainActor,checkedOut;;
			int year,seasons,duration,code,pageNum,volume;

		
			for(int i = 0 ; i < library.length;i++) {
				name = readText.readLine();
				if (!name.equals("------------------------------")) {
					year = Integer.parseInt(readText.readLine());
					genre = readText.readLine();
					code = Integer.parseInt(readText.readLine());
					checkedOut = readText.readLine();
					textLine = readText.readLine();
					//novel
					if (textLine.equals("1")) {
						author = readText.readLine();
						pageNum = Integer.parseInt(readText.readLine());
						library[i] = new Novels(name,year,genre,code,checkedOut,author,pageNum);
					}	
					//comic
					if (textLine.equals("2")) {
						author = readText.readLine();
						volume = Integer.parseInt(readText.readLine());
						library[i] = new Comics(name,year,genre,code,checkedOut,author,volume);
					}	
					//movie
					if (textLine.equals("3")) {
						director = readText.readLine();
						duration = Integer.parseInt(readText.readLine());
						mainActor = readText.readLine();
						library[i] = new Movies(name,year,genre,code,checkedOut,director,duration, mainActor);
					}	
					//tvshow
					if (textLine.equals("4")) {
						director = readText.readLine();
						duration = Integer.parseInt(readText.readLine());
						seasons = Integer.parseInt(readText.readLine());
						library[i] = new tvShows(name,year,genre,code,checkedOut,director,duration, seasons);
					}	
				}
				else {
					i--;
				}
			}
			readText.close();
			in.close();
		} 
		catch(FileNotFoundException e) {	
			System.out.println("File does not exist or could not be found.");
			System.err.println("FileNotFoundException: " + e.getMessage());
		} 
		catch(IOException e) {
			System.out.println("Problem reading file."); 
			System.err.println("IOException: " + e.getMessage());
		}	
	
		return (library);
	}
	
	/*
	 * Returns the size of the library by reading text file
	 * pre: none
	 * post: size of library is returned
	 */
	public static int libSize(File lib) {
		int count=0;
		try {
			FileReader in = new FileReader(lib);
			BufferedReader readText = new BufferedReader(in);
			String textLine;
		
			while((textLine = readText.readLine()) != null) {
				if (textLine.equals("------------------------------")) {
					count++;
				}
			}
			in.close();
			readText.close();
		} 
		catch(FileNotFoundException e) {
			System.out.println("File does not exist or could not be found.");
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		catch(IOException e) {
			System.out.println("Problem reading file."); 
			System.err.println("IOException: " + e.getMessage());
		}
		return count;
	}
	
	/*
	 * Returns a new code for library to use when item is added
	 * pre: none
	 * post: new code is returned
	 */
	public static int findCode(int Fcode,Library lib[]) {
		for(int i = 0 ; i < lib.length ; i++) {
			if (lib[i].getCode() > Fcode) {
				Fcode = lib[i].getCode();
			}
		}
		Fcode++;
		return Fcode;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Returns users decision to login, create account or quit
	 * pre: none
	 * post: decision is returned
	 */
	public static int welcome(int choose) {
		Scanner input = new Scanner(System.in);
		System.out.print("\n\t\t\t1. Login\t2. Create an Account?\t0. Quit\n\nEnter your choice: ");	
		choose = input.nextInt();
		if (choose != 1 && choose !=2 && choose !=0) {
			line();
			System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");

		}	
		if (choose != 1 && choose !=2 && choose !=0) {
			line();
		
			return welcome(choose);
		}
		line();

		return choose;
	}
	/*
	 * Creates an account in library for new user
	 * pre: none
	 * post: new account is created
	 */
	public static void createAcc(String username,String password,int secretPin) {
		Scanner input = new Scanner(System.in);
		boolean tryAgain = false;
		int pin=0;
		int choose=0;
		boolean numCheck = false;
		do {
			System.out.println("\t\t\t\t    Please Choose an Account Type");
			System.out.print( "\n\t\t\t    1. Customer\t     2. Admin\t    0. Go Back\n\nEnter your choice: ");
			choose = input.nextInt(); 
			if (choose!=1 && choose != 2&&choose != 0) {
				line();
				System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
				
			}
			line();
		}while(choose!=1 &&choose != 2&&choose != 0);
		if (choose != 0) {
			if (choose == 2) {
				System.out.print("\nEnter Special Pin to make Admin Account: ");
				pin = input.nextInt();
				if (pin!=secretPin) {
					System.out.println("\nInvalid Pin.");
				}
				else {
					System.out.print("\nCorrect Pin.");
				}
				line();
			}
			if (pin == secretPin || choose ==1) {
				System.out.print("\nAccount Requirements\n - Both password and username must be atleast 6 characters long\n - the USERNAME cannot exist as numbers only\n\nEnter a username: ");
				username = input.next();
				System.out.print("Enter a passoword: ");
				password = input.next();
				try {
			        Integer.parseInt(username);
			        numCheck = true;
			    }
			    catch( Exception e ) {
			    }
				line();
				User user = new Customer(username,password,choose);
				tryAgain = user.exists(username);
				if (tryAgain) {
					System.out.println("\nThis username already exists, please try again.");
					line();
					createAcc(username,password,choose);
				}
				else if (username.equals("0") && password.equals("0")) {	
				}
				else if (numCheck) {	
					System.out.println("\n\t\t   The username you entered is only numbers. Please try again.");
					line();
					createAcc(username,password,choose);
				}
				else if (username.length() <= 5) {	
					System.out.println("\n\t\t    The username you enter must be alteast 6 characters long.");
					line();
					createAcc(username,password,choose);
				}
				else if (username.length() <= 5) {	
					System.out.println("\n\t\t    The password you enter must be alteast 6 characters long.");
					line();
					createAcc(username,password,choose);
				}
				else {
					user.add(username,password,choose);
					System.out.println("\nYour Account has been successfuly created!");
					line();
				}
			}
		}
	}
		
	/*
	 * Returns accesslevel depending on what user is signing in as
	 * pre: none
	 * post: accessLevel is returned
	 */
	public static int access() {
		int accessLevel;
		Scanner input = new Scanner(System.in);
		System.out.print( "\n\t\t\t    1. Customer\t     2. Admin\t    0. Go Back\n\nEnter your choice: ");
		accessLevel = input.nextInt(); 
		if (accessLevel != 1 && accessLevel !=2 && accessLevel !=0) {
			line();
			System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
		}	
		if (accessLevel != 1 && accessLevel !=2 && accessLevel !=0) {
			line();
			return access();
		}
		line();
		return accessLevel;
	}
	
	/*
	 * Allows user to login and checks identification
	 * pre: none
	 * post: returns user information to be used in main
	 */
	public static User login(int accessLevel,String username, String password) {
		Scanner input = new Scanner(System.in);
		boolean tryAgain = false;
	
		System.out.print("\n\t\t\t\tLogin (Type 0 twice to bo Back):\n\nUsername: ");
		username = input.next();
		System.out.print("Passoword: ");
		password = input.next();
		User user = new Customer(username,password,accessLevel);
		tryAgain =user.login(username,password,accessLevel);
		line();
		if (!tryAgain && !username.equals("0")) {
			System.out.println("\n\t\t\tInvaid username or password. Please Try Again.");
			line();
			return login(accessLevel,username,password);
		}
		if (username.equals("0") && !password.equals("0") || !username.equals("0") && password.equals("0")) {
			System.out.println("\n\t\t\tInvaid username or password. Please Try Again.");
			line();
			return login(accessLevel,username,password);
		}
		user = new Customer(username,password,accessLevel);
	
		return user;
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Sort page allow user to select how to sort library
	 * and allows them to choose books to add to cart
	 * pre: none
	 * post: action is performed depending on user input
	 */
	public static void sort(Library library[],Customer user, Admin admin) {
		int arr1[] = new int[library.length];
		String arr2[] = new  String [library.length];
		
		int choose;
		Scanner input = new Scanner(System.in);
		do {
			Library arr3[] = null;
			line();
			System.out.print("\t\t\t\t    How would you like to Sort:\n");
			System.out.print("\n ————————————————————————————————————————————————————————————————————————————————————————————————— ");
			System.out.println("\n Sort Instructions\n   - Enter the corresponding number to sort the library as the option states\n   - Numbers 4-7 will display only that type of media");
			System.out.println("\n Add to Cart Instructions\n   - You may type the code of an item here to add it to your cart");
			System.out.println(" ————————————————————————————————————————————————————————————————————————————————————————————————— ");
			System.out.println("\t\tAny of the two options above must be entered into \"Enter an Option\"");
			
			System.out.print("\n\t\t    1. Alphabetically\t2. Genre\t3. Year"
					+ "\n\t\t    4. Novels\t\t5. Comics\t6. Movies"
					+ "\n\t\t    7. TV Show\t\t8. Display All\t0. Go To Home Screen"
					+ "\n\nEnter an Option: ");
			
			choose = input.nextInt();
			line();

			if (choose == 1) {
				arr2 = library[0].arrName(library);
				insertionSortString(arr2,library);
			}
			else if (choose == 2) {
				arr2 = library[0].arrGenre(library);
				insertionSortString(arr2,library);
			}
			else if (choose == 3) {
				arr1 = library[0].arrYear(library);
				selectionSortInt(arr1,library);
			}
			else if (choose == 4) {
				arr3 = library[0].DisType(library,1);
			}
			else if (choose == 5) {
				arr3 = library[0].DisType(library,2);
			}
			else if (choose == 6) {
				arr3 = library[0].DisType(library,3);
			}
			else if (choose == 7) {
				arr3 = library[0].DisType(library,4);
			}
			else if (choose >=10000 && admin ==null){
				cart(library,choose,user);
			}
			else if (choose != 0) {
				System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
			}

			if (choose > 0 && choose < 4|| choose == 8) {
				System.out.println();
				printLib(library);	
			}
			else if (choose > 3 && choose < 8) {
				System.out.println();
				printLib(arr3);	
			}
			if (choose > 0 && choose < 9) {
				int details = getDetails(library);
				if (details != -1) {
					System.out.println("\n\n--------------------------------\n"+library[details]);
					System.out.println("--------------------------------");
				}
			}
		}while (choose !=0);
	}
	
	/*
	 * Sorts library based on how string array is sorted
	 * pre: none
	 * post: library is sorted
	 */
	public static void insertionSortString(String[] arr2, Library library[]) {
		int min_loc = 0;
		int place_at = 0;	
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j <i && min_loc == 0; j++) {
				//moves element to front(a-z)
				if(arr2[i].compareToIgnoreCase(arr2[j]) < 0)  { 
					min_loc = i;
					place_at = j;
					
				}
			}
		
			String min_val = arr2[min_loc];
			Library min_val1 = library[min_loc];
			for (int j = min_loc; j > place_at; j--) {
				arr2[j] = arr2[j - 1];
				library[j] = library[j - 1];
			}
			arr2[place_at] = min_val;
			library[place_at] = min_val1;
			min_loc = 0;
			place_at = 0;	
		}	
	}	
	/*
	 * Sorts library based on how integer array is sorted
	 * pre: none
	 * post: library is sorted
	 */
	public static void selectionSortInt(int arr1[], Library library[]) {		
		for (int i = arr1.length ; i>1 ; i--) {
			int max = 0;
			for (int j = 0 ; j < i ; j++) {
				if (arr1[j]>arr1[max]) {
					max=j;
				}
			}
			int key = arr1[max];
			arr1[max] = arr1[i-1];
			arr1[i-1] = key;
			
			Library key1 = library[max];
			library[max] = library[i-1];
			library[i-1] = key1;
		}
	}
	/*
	 * Sorts library based on how boolean array is sorted
	 * pre: none
	 * post: library is sorted
	 */
	public static void bubbleSortBoolean(Boolean arr3[],Library library []) {
		Boolean temp;
		Library temp1;
		boolean swap = true;
		
		for (int i = 0 ; i < arr3.length-1 && swap; i++) {
			swap = false;
			for (int j = 0 ; j< arr3.length-1-i; j++) {
				if(!arr3[j] && arr3[j+1]) { //<-- move false to back
					temp = arr3[j];
					arr3[j] = arr3[j+1];
					arr3[j+1] = temp;
					
					temp1 = library[j];
					library[j] = library[j+1];
					library[j+1] = temp1;
					swap = true;
				}		
			}
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Page for user to add item to cart if intended
	 * pre: none
	 * post: adds item to cart or displays reason why action wasn't performed
	 */
	public static void cart(Library lib[],int choose,Customer user) {
		Scanner input = new Scanner(System.in);
		int arr[];
		arr = lib[0].arrCode(lib);
		selectionSortInt(arr,lib);
		int place = binarySearch(lib,0,lib.length-1,choose);
		int y;
		
		if (place == -1) {
		
			System.out.println("\n\t\t\t\t   This is an Invalid Code");
			
		}
		else {
			do {
			System.out.print("\t\t\tWould you like Add this item to your Cart?\n\n-------------------------\n"+lib[place]+"\n-------------------------\n\n(1= YES || 2 = NO || 0 = BACK): ");
			y = input.nextInt();
			if (y!=0 && y!=1 && y!=2) {
				line(); 
				System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
				line();
			}
			}while(y!=0 && y!=1 && y!=2);
			//CHECK INPUT
			if (y==1 && user.getCart().contains(choose)) {
				line(); 
				System.out.println("\nYou already have this item in your cart.");
				
			}
			else if (y==1 && lib[place].getCheckedOut().equals("no")) {
				user.addCart(choose);
				line(); 
				System.out.println("\n\t\t\t     Item was successfully added to your cart.");
			}
			else if (y==1 && lib[place].getCheckedOut().equals(user.getUsername())) {
				line(); 
				System.out.println("\n\t\t\t\t You currently have this item signed out.");
			}
			else if (y==1) {
				line(); 
				System.out.println("\n\t\t\t   This book is currently signed out to someone else.");
			}
		}
	}
	/*
	 * Checkout page gives user option to sign out items or remove from cart
	 * pre: none
	 * post: corresponding action to user input occurs
	 */
	public static int checkout(Customer user,Library lib[]) {
		Scanner input = new Scanner(System.in);
		int op,code,last=1;
		ArrayList <Integer> cart = user.getCart();
		int arr[];
		arr = lib[0].arrCode(lib);
		selectionSortInt(arr,lib);
		line();
	
		if(cart.size()!= 0) {	
			System.out.println("\t\t\t\t\t     Checkout");
			do {
				for (int i = 0 ; i < cart.size() ; i++) {
					int place = binarySearch(lib,0,lib.length-1,cart.get(i));
					System.out.println("\n-------------------------\n"+lib[place]+"\n-------------------------\n");
				}
				System.out.print("\t1. Sign out Selected items\t2. Remove an item\t3. Clear Cart\t0. Go Back\n\nChoose an Option: ");
				op = input.nextInt();
				
				if (op == 1) {
					itemOut(lib,user,cart);
					line();
					System.out.println("\n\t\t\t\t\tTransaction Success!");
					do {
						System.out.print("\t\t\t      Thank you for visting Aarin's Library!\n\n\t\t\t\t  0.Exit   1. Continue Browsing\n\nChoose an Option: ");
						last = input.nextInt();
						if (last != 0 && last != 1) {
							line();
							System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
				
						}	
					}while(last != 0 && last != 1);
					
				}
				else if (op == 2) {
					System.out.print("\nEnter code of item to be removed: ");
					code = input.nextInt();
					op = user.removeCart(code);
					if (op == 2) {
						line();
						System.out.println("\n\t\t\t\t\tItem has been removed.");
					}
				}
				else if (op == 3) {
					line();
					System.out.println("\n\t\t\t\t\tCart is Clear.");
					cart.clear();
				}
				else if (op != 0) {
					System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
					line();
				}
				
					
			}while(op != 1 && op != 2 && op != 0 && op != 3);
		}
		else {
			System.out.println("\n\t\t\t  You have not added any items to your cart.");
			
		}
		return last;
	
	}
	/*
	 * Changes checkedOut to users name so admin knows who checked it out
	 * pre: none
	 * post: user is tracked and cart is cleared
	 */
	public static void itemOut(Library lib[],Customer user,ArrayList <Integer> cart) {
		for (int i = 0 ; i < cart.size() ; i++) {
			int place = binarySearch(lib,0,lib.length-1,cart.get(i));
			lib[place].setCheckedOut(user.getUsername());
			newFile(cart.get(i),user);
			transfer();
		}
		cart.clear();
	}
	/*
	 * Displays what items are checked out only for admin
	 * pre: none
	 * post: checked out list is displayed
	 */
	public static void displayCheckedOut(Library lib[], Admin admin) {

		ArrayList<Integer> check = admin.getCheckedOut(lib);
		line();
		System.out.println("\t\t\t\t\tDisplay Checked Out\n");
		
		if (check.size()>0) {
			String out [][] = new String [check.size()][3];
			for (int i = 0 ; i < check.size() ; i++) {
				out[i][0] = lib[check.get(i)].getCheckedOut(); 
				out[i][1] = lib[check.get(i)].getTypeName(); 
				out[i][2] = lib[check.get(i)].getName(); 
			}
		
			System.out.println("----------------------------------------------------------\n|Name\t\t|Item\t|Title");
			System.out.println("----------------------------------------------------------");
			print2DArray(out);
			System.out.println("----------------------------------------------------------");
		}
		else
			System.out.println("\t\t\t\t\tNo items Checked Out");
		line();
	}
	/*
	 * 2D array of checked out list is printed
	 * pre: none
	 * post: array is printed
	 */
	public static void print2DArray(String [][]numbers) {
		for (int count1 = 0 ; count1 < (numbers.length) ; count1++) {
			for(int count2 = 0 ; count2 < (numbers[0].length) ; count2++) {
				System.out.print("|"+numbers[count1][count2]+"\t");
				if (count2 == 0) {
					if (numbers[count1][count2].length() < 6) {
						System.out.print("\t");
					}
				}
			}		
		System.out.println();
		}
		for (int temp = 0 ; temp < (numbers[0].length) ; temp++) {
		
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * returns the position of a needed item in library
	 * pre: none
	 * post: position is returned
	 */
	public static int binarySearch(Library arr[], int bottom, int top, int choose) { 
        if (top >= bottom) { 
            int mid = bottom + (top - bottom) / 2; 
            if (arr[mid].getCode() == choose) 
                return mid; 
            if (arr[mid].getCode() > choose) 
                return binarySearch(arr, bottom, mid - 1, choose); 
            return binarySearch(arr, mid + 1, top, choose); 
        } 
       
        return -1; 
    } 

	/*
	 * Search page allow user to apply filters on their search
	 * which returns the most related items to their search
	 * pre: none
	 * post: action is performed depending on user input
	 */
	public static void search(Library lib[],Customer user,Admin admin) {
		String choose;
		ArrayList<String> filter = new ArrayList<String>();
		ArrayList<String> filterN = new ArrayList<String>();
		Scanner input = new Scanner(System.in);

		do {
			int num=0;
			line();
			System.out.println("\t\t\t\t\tSearch for an Item");
			System.out.print("\n ————————————————————————————————————————————————————————————————————————————————————————————————— ");
			System.out.println("\n Search Instructions\n   - Your search can be comprised of number or letters\n   - Your search must be atleast 2 characters long");
			System.out.println("\n Filter Instuctions\n   - To add a filter, type the corresponding number and hit enter\n   - To remove a filter, re-type the corresponding number and hit enter");
			System.out.println("\n Add to Cart Instructions\n   - You may type the code of an item here to add it to your cart");
			System.out.println(" ————————————————————————————————————————————————————————————————————————————————————————————————— ");
			System.out.println("\t\t   Any of the three options above must be entered into \"Search\"");
			System.out.print("\nCurrent Filters: | ");
			if (filterN.size() == 0) {
				filterN.add("No Filters Applied");
			}
			for (int i = 0; i < filterN.size(); i++) {
				System.out.print(filterN.get(i)+" | ");
		    }
			
			System.out.print("\n\n\t  1. Novels"
					+ "\t  2. Comics"
					+ "\t  3. Movies"
					+ "\t  4. TvShows"
					+ "\t  0. Go To Home Screen"
					+ "\n\nSearch: ");
			choose = input.nextLine();
			choose = choose.trim();
			choose = choose.toLowerCase();
			try {
		        Integer.parseInt( choose );
		        num =  Integer.parseInt( choose );
		    }
		    catch( Exception e ) {
		    }
			if(filter.contains(choose)) {
				int a = filter.indexOf(choose);
				filter.remove(a);
				filterN.remove(a);
			}
			else {
				if (filterN.contains("No Filters Applied")) {
					filterN.remove(0);
				}
				if (choose.equals("1")) {
					filter.add("1");
					filterN.add("Novels");
				}
				else if (choose.equals("2")) {
					filter.add("2");
					filterN.add("Comics");
				}
				else if (choose.equals("3")) {
					filter.add("3");
					filterN.add("Movies");
				}
				else if (choose.equals("4")) {
					filter.add("4");
					filterN.add("TvShows");
				}
				else if (num > 10000 && admin == null) {
					line();
					cart(lib,num,user);
				}
				else if(choose.length() > 1) {
					String arr[] = choose.split(" ");
					line();
					linearSearch(choose,arr,lib,filter,filterN);
				}
				else if (!choose.equals("0")) {
					line();
					System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
			
				}
			}
		}while (!choose.equals("0"));
		line();
	}
	
	/*
	 * Searches library for most related item to their search
	 * and displays it from most to least related
	 * pre: none
	 * post: list of items are displayed 
	 */
	public static void linearSearch(String choose,String arr[], Library lib[],ArrayList<String> filter,ArrayList<String> filterN) {
		HashMap<Library, Integer> tempLib1 = new HashMap<Library, Integer>();
		if (filter.size()>0) {
			lib = getFilteredLib(lib,filter);
		}
		
		int temp;
	
		for (int i = 0 ; i < lib.length ; i++) {
			temp=0;
			String temp1 = lib[i].toString1();
			//System.out.println(temp1);
			temp1 = temp1.replace("-", "");
			temp1 = temp1.toLowerCase();
			for (int j = 0 ; j < arr.length ; j++) {
				String temp2 = arr[j];
				temp2 = temp2.replace("-", "");
				try {
			        Integer.parseInt(temp2);
			        temp2 = " "+temp2+" "; 
			    }
			    catch( Exception e ) {
			    }
				if(temp2.equals("the")) {
					 temp2 = " "+temp2+" "; 
				}
				if (temp1.contains(temp2)) {
					if(temp2.equals("the")) {
						temp++;
					}else {
						temp+=2;
					}
				}

			}
			if (temp > 0) {
				tempLib1.put(lib[i], temp);
			}
		
		}
		Library tempLib2[] = new Library[tempLib1.size()];	
		int score2[] = new int [tempLib1.size()];
		int count = 0;
		for (Library i : tempLib1.keySet()) {
			tempLib2[count] = i;
			score2[count] = tempLib1.get(i);
			count++;
		}	
			
		selectionSortInt(score2,tempLib2);
		Library finalTemp[] = new Library[tempLib2.length];	
		count = 0;
		for (int i = finalTemp.length-1; i >=0 ; i--) {
			finalTemp[count] = tempLib2[i];
			count++;
	
		}
		
		System.out.println("\t\t\t\t   Results to: \""+choose+"\"");
		System.out.print("\t\t\t\tCurrent Filters | ");
		if (filterN.size() == 0) {
			filterN.add("No Filters Applied");
		}
		for (int i = 0; i < filterN.size(); i++) {
			System.out.print(filterN.get(i)+" | ");
	    }
		System.out.println("\n");
		if (tempLib1.size()==0) {
			System.out.println("Your search does not match any items in our database.");
	
		}else {
			printLib(finalTemp);
			int details = getDetails(finalTemp);
			if (details != -1) {
				System.out.println("\n-------------------------\n"+finalTemp[details]);
				System.out.println("-------------------------");
			}	
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * returns index of item that user wants more detail about
	 * pre: none
	 * post: index is returned
	 */
	public static int getDetails(Library lib[]) {
		Scanner input = new Scanner(System.in);
		String details;
		Boolean check = false;
		System.out.print("\nFor more details on an item, enter the number on its left (Type \"exit\" to Go Back): ");
		details = input.next();
		if (details.equalsIgnoreCase("exit")) {
			return -1;
		}
		try {
	       Integer.parseInt(details);
	       check = true;
	    }
	    catch( Exception e ) {
	    }
		if(!details.equals("exit") && !check) {
			line();
			System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
			line();
			return getDetails(lib);
		}
		if(Integer.parseInt(details) > (lib.length-1) || Integer.parseInt(details) < -1) {
			line();
			System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
			line();
			return getDetails(lib);
		}
	
		return Integer.parseInt(details);
		
	}
	
	/*
	 * Creates a temporary library that only includes
	 * items depending on filter added
	 * pre: none
	 * post: temp library is returned
	 */
	public static Library[] getFilteredLib(Library lib[],ArrayList<String> filter ) {
		ArrayList<Library> tempLib = new ArrayList<Library>();

		for (int i = 0; i < filter.size(); i++) {
			for (int j = 0; j < lib.length; j++) {
				if (String.valueOf(lib[j].getType()).equals(filter.get(i))) {
					tempLib.add(lib[j]);
				}
			}
		}
		Library tempLib2[] = new Library[tempLib.size()];
		for (int j = 0; j < tempLib2.length; j++) {
			tempLib2[j] = tempLib.get(j);
		}
	
		return tempLib2;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Finds the item that user signed out and changes checked out
	 * status to user's name in txt file (saves in a temp file)
	 * pre: none
	 * post: temp file is writeen with new contents
	 */
	public static void newFile(int choose, User user) {
		File libFile = new File("lib1.txt");
		File libFileO = new File("lib.txt");
	
		try {
			FileWriter out= new FileWriter(libFile);
			BufferedWriter writeText = new BufferedWriter(out);
			FileReader in = new FileReader(libFileO);
			BufferedReader readText = new BufferedReader(in);
			String readLine;
			while((readLine = readText.readLine()) != null) {
				if (!readLine.equals(String.valueOf(choose))) {
					writeText.write(readLine);				
					writeText.newLine();
				
				}
				else {
					writeText.write(String.valueOf(choose));				
					writeText.newLine();
					readLine = readText.readLine();
					writeText.write(user.getUsername());				
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
	 * Transfers content from temp to original file
	 * pre: none
	 * post: file contents is copied to original
	 */
	public static void transfer() {
		File libFile = new File("lib1.txt");
		File libFileO = new File("lib.txt");
	
		try {
			FileWriter out= new FileWriter(libFileO);
			BufferedWriter writeText = new BufferedWriter(out);
			FileReader in = new FileReader(libFile);
			BufferedReader readText = new BufferedReader(in);
			String readLine;
			while((readLine = readText.readLine()) != null) {
				writeText.write(readLine);				
				writeText.newLine();
				
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Item is removed by admin if possible and 
	 * updated text file is created
	 * pre: none
	 * post: item removed
	 */
	public static Library [] removeItem(Library lib [],Admin admin) {
		Scanner input = new Scanner(System.in);
	
		int item,decision=0;
		File libFile = new File("lib.txt");
		Library [] newl = new Library[libSize(libFile)];

		line();
		System.out.println("\t\t\t\t\t     Remove Item");
		System.out.print("\nEnter the code of item to be removed: ");
		item = input.nextInt();
		line();
		int arr[];
		arr = lib[0].arrCode(lib);
		selectionSortInt(arr,lib);
		int place = binarySearch(lib,0,lib.length-1,item);
		
		if (place == -1) {
			System.out.println("\n\t\t\t\t      This item does not exist.");

		}
		else if (!lib[place].getCheckedOut().equals("no")) {
			System.out.println("\n\t\tYou cannot remove this item, it is currently signed out by "+lib[place].getCheckedOut());
			
		}
		else {
			do {
				System.out.println("\nAre you sure you want to remove: ");
				System.out.println("\n-------------------------\n"+lib[place]);
				System.out.println("-------------------------");
				System.out.print("\n\t\t\t\t\t1 = YES \t2 = NO\n\nEnter Your Choice: ");
				decision = input.nextInt();
				if (decision != 1 && decision != 2) {
					line();
					System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
				}
			}while(decision != 1 && decision != 2);
			line();
			if (decision == 1) {
				admin.remove(lib,place);
				transfer();
				System.out.println("\n\t\t\t\t\t     Item Removed.");
			}
			else {
				System.out.println("\n\t\t\t\t\t   Item Not Removed.");
			}
		}
		newl = createLib();
		line();
		return(newl);
		
	}
	/*
	 * Admin can add an item to the library by filling
	 * out info nessessary 
	 * pre: none
	 * post: item is added
	 */
	public static Library [] addItem(Library lib [],Admin admin,int Fcode) {
		line();
		Scanner input = new Scanner(System.in);
		File libFile = new File("lib.txt");
		Library [] newl = new Library[libSize(libFile)];
		String name= null,genre=null,year=null,var1=null,var2=null,var3=null;
		int choose;
		
		do {
			System.out.println("\t\t\t\t\t     Add Item");
			System.out.print("\n\t  1. Novels"
					+ "\t  2. Comics"
					+ "\t  3. Movies"
					+ "\t  4. TvShows"
					+ "\t  0. Go Back"
					+ "\n\nEnter an Option: ");
			choose = input.nextInt();
			
			if(choose > 0 && choose < 5) {
				line();
				System.out.println("\nTo Quit, enter \"0\" within the first 3 fields.");
				System.out.print("\nEnter Title: ");
				name = input.nextLine();
				name = input.nextLine();
				System.out.print("\nEnter Year: ");
				year = input.nextLine();
				System.out.print("\nEnter Genre: ");
				genre = input.nextLine();
			}
			
			if (name.equals("0") && year.equals("0") && genre.equals("0")) {
				line();
				System.out.println("\n\t\t\t\t\tItem was not added.");
				choose = 0;
			}
			
			if (choose == 1 || choose == 2) {	
				System.out.print("\nEnter author: ");
				var1 = input.nextLine();
				if (choose == 1) 
					System.out.print("\nEnter Number of Pages: ");
				else 
					System.out.print("\nEnter Volume: ");
				var2 = input.next();
				admin.addI(name, year, genre, var1, var2, var3, choose,Fcode);
				line();
				System.out.println("\n\t\t\t\t\tItem was added.");
			
			}
			else if (choose == 3 || choose == 4) {	
				System.out.print("\nEnter Director: ");
				var1 = input.nextLine();
				System.out.print("\nEnter Duration (If TVShow, Enter Per Episode): ");
				var2 = input.nextLine();
				if (choose == 4) 
					System.out.print("\nNumber of Seasons: ");
				else 
					System.out.print("\nEnter Main Actor: ");
				var3 = input.nextLine();
				
				admin.addI(name, year, genre, var1, var2, var3, choose,Fcode);
				line();
				System.out.println("\n\t\t\t\t\tItem was added.");
			}	
			else if (choose != 0) {
				line();
				System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
				
			}
			line();
		}while(choose != 0);
		
		newl = createLib();
		return(newl);
		
	}
	/*
	 * Customer can return items they have checked out already
	 * and text file is updated
	 * pre: none
	 * post: item is returned
	 */
	public static Library [] returnItem(Customer user,Library lib[]) {
		Scanner input = new Scanner(System.in);
		File libFile = new File("lib.txt");
		ArrayList<Library> item = new ArrayList<Library>();
		Library [] newl = new Library[libSize(libFile)];
		int arr[];
		int code,decision;
		arr = lib[0].arrCode(lib);
		selectionSortInt(arr,lib);
		
		line();
		System.out.println("\t\t\t\t\t     Return Item");

		for (int i = 0 ; i < lib.length ; i++) {
			if (lib[i].getCheckedOut().equals(user.getUsername())) {
				item.add(lib[i]);
			}
		}
		if (item.size()>0) {
			System.out.println("\n\t\t\t\t    Currently Checked Out Items:");
			for (int i = 0 ; i < item.size() ; i++) {
				System.out.println("\n-------------------------\n"+item.get(i)+"\n-------------------------");
			}
			System.out.print("\nEnter the code of item to be returned: ");
			code = input.nextInt();
		
			int place = binarySearch(lib,0,lib.length-1,code);
			if (place == -1) {
				line();
				System.out.println("\n\t\t\t\tThis item does not exist or belong to you.");

			}
			else {
				do {
					line();
					System.out.println("\nAre you sure you want to return: ");
					System.out.println("\n-------------------------\n"+lib[place]);
					System.out.println("-------------------------");
					System.out.print("\n\t\t\t\t\t1 = YES \t2 = NO\n\nEnter Your Choice: ");
					decision = input.nextInt();
					if (decision != 1 && decision != 2) {
						line();
						System.out.println("\n\t\t\t\tInvalid Input. Please Try Again.");
					}
				}while(decision != 1 && decision != 2);
				line();
				if (decision == 1) {
					user.returnItem(lib,place);
					transfer();
					System.out.println("\n\t\t\t\t\t     Item Returned.");
				}
				else {
					System.out.println("\n\t\t\t\t\t   Item Not Returned.");
				}
			}	
		}
		else {
			System.out.println("\n\t\t\t\tYou currently have no items checked out.");
		}	
		newl = createLib();
		line();
		return (newl);
	}	
}