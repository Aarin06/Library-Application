//Constructors are only used so they can be used in the constructors of child classes
//since this is an abstract class

abstract class Library {
	private String checkedOut;
	private int code;
	private String name;
	private int type;
	private String genre;
	private int year;

	
  	/**
	  * constructor
	  * pre: none
	  * post: A library object is created and integers are set to 0 and strings are set to blank. 
	  */
	public Library() {
		name = "";
		code = 0;
	}

  	/**
	  * constructor
	  * pre: none
	  * post: A library object is created initialized by values from parameters. 
	  */
	public Library(String n, int y, String g, int c,String co) {
		name = n;
		year = y;
		genre = g;
		code = c;
		checkedOut = co;
	}
	
	/*
	 * Returns username of person who checked out
	 * pre: none
	 * post: username is returned
	 */
	public String getCheckedOut() {
		return(checkedOut);
	}
	/*
	 * Returns name of item
	 * pre: none
	 * post: name of item is returned
	 */
	public String getName() {
		return(name);
	}
	/*
	 * Returns genre of item
	 * pre: none
	 * post: genre is returned
	 */
	public String getGenre() {
		return(genre);
	}
	/*
	 * Returns year of item
	 * pre: none
	 * post: year is returned
	 */
	public int getYear() {
		return(year);
	}
	/*
	 * Returns code of item
	 * pre: none
	 * post: code is returned
	 */
	public int getCode() {
		return(code);
	}
	/*
	 * Returns number representing type of item
	 * pre: none
	 * post: number is returned
	 */
	public int getType() {
		return(type);
	}

	/*
	 * sets checked out to username of customer
	 * pre: none
	 * post: name of person is set
	 */
	public void setCheckedOut(String c) {
		checkedOut = c;
	}
	
	/*
	 * sets name of item
	 * pre: none
	 * post: name of item is set
	 */
	public void setName(String n) {
		name = n;
	}
	/*
	 * sets genre of item
	 * pre: none
	 * post: genre of item is set
	 */
	public void setGenre(String g) {
		genre = g;
	}	
	/*
	 * sets year of item
	 * pre: none
	 * post: year of item is set
	 */
	public void setYear(int y) {
		year = y;
	}
	/*
	 * sets code of item
	 * pre: none
	 * post: code of item is set
	 */
	public void setCode(int c) {
		code = c;
	}
	/*
	 * sets code of item
	 * pre: none
	 * post: code of item is set
	 */
	public void setType(int t) {
		type = t;
	}
	
	/*
	 * prints a list of items depending on type that is passed by paramater t
	 * pre: none
	 * post: list is printed
	 */
	public Library [] DisType(Library lib[],int t) {
		int count = 0;
		for (int i = 0 ; i < lib.length ; i++) {
			if (lib[i].getType() == t) {
				count++;
			}
		}
		Library temp[] = new Library[count];
		count = 0;
		for (int i = 0 ; i < lib.length ; i++) {
			if (lib[i].getType() == t) {
				temp[count] = lib[i];
				count++;
			}
		}
		
		return temp;
		
	}
	
	/*
	 * returns string array of all names/titles of items in library
	 * pre: none
	 * post: array of titles is returned
	 */
	public String[] arrName(Library lib[]) {
		String arr[] = new String[lib.length];
		for (int i = 0 ; i < arr.length ; i++) {
			arr[i] = lib[i].getName();
		}
		return arr;
	}
	
	/*
	 * Returns array of the year items were made
	 * pre: none
	 * post: array of years is returned
	 */
	public int[] arrYear(Library lib[]) {
		int arr[] = new int[lib.length];
		for (int i = 0 ; i < arr.length ; i++) {
			arr[i] = lib[i].getYear();
		}
		return arr;
	}
	
	/*
	 * Returns array of the codes of each item
	 * pre: none
	 * post: array of codes is returned
	 */
	public int[] arrCode(Library lib[]) {
		int arr[] = new int[lib.length];
		for (int i = 0 ; i < arr.length ; i++) {
			arr[i] = lib[i].getCode();
		}
		return arr;
	}
	
	/*
	 * Returns array of the genre of each item
	 * pre: none
	 * post: array of genres is returned
	 */
	public String[] arrGenre(Library lib[]) {
		String arr[] = new String[lib.length];
		for (int i = 0 ; i < arr.length ; i++) {
			arr[i] = lib[i].getGenre();
		}
		return arr;
	}
	
	abstract String toString1();
	
	abstract String display();
	
	abstract String getTypeName();
	
		
	
}
