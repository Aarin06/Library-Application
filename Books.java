//Constructors are only used so they can be used in the constructors of child classes
//since this is an abstract class

abstract class Books extends Library {
	private String author;
	
	/**
	  * constructor
	  * pre: none
	  * post: A book object is created, strings set to blank. 
	  */
	public Books() {
		author = "";
	}

  	/**
	  * constructor
	  * pre: none
	  * post: A books object is created initialized by values from parameters. 
	  */
	public Books(String n,int y, String g, int c,String co, String a) {
		super(n,y,g,c,co);
		author = a;
	}
	/*
	 * Returns author of book
	 * pre: none
	 * post: author is returned
	 */
	public String getAuthor() {
		return(author);
	}
	/*
	 * sets author of book
	 * pre: none
	 * post: author of book is set
	 */
	public void setAuthor(String a) {
		author = a;
	}
	
	abstract String getTypeName();
	
	
}

