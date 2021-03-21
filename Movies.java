
public class Movies extends Videos{
	private String mainActor;
  
	/**
	  * constructor
	  * pre: none
	  * post: A movies object is created and strings set to blank
	  */
	public Movies() {
		mainActor = "";
	}

	/**
	  * constructor
	  * pre: none
	  * post: A movies object is created initialized by values from parameters. 
	  */
	public Movies(String n, int y, String g, int c,String co, String dd, int d,String m) {
		super(n,y,g,c,co,dd,d);
		mainActor = m;
		super.setType(3);
	}
	
	/*
	 * Returns main actor of movie
	 * pre: none
	 * post: main actor is returned
	 */
	public String getMainActor() {
		return(mainActor);
	}
	
	/*
	 * sets main actor of movie
	 * pre: none
	 * post: main actor of movie is set
	 */
	public void setMainActor(String p) {
		mainActor = p;
	}
	/*
	 * toString override method to return all info of item
	 * pre: none
	 * post: all info regarding the item is returned
	 */
	public String toString() {
		String info;
		info = "Title: "+super.getName()+"\nYear: "+super.getYear()+"\nGenre: "+super.getGenre()+"\nDirector: "+super.getDirector()+"\nDuration: "+super.getDuration()+" mins\nMain Actor: "+mainActor+"\n**Code: "+super.getCode()+"**";
		return (info);
	}
	/*
	 * returns string that is used as key words for the item during a search
	 * pre: none
	 * post: important info string is returned
	 */
	public String toString1() {
		String info;
		info = super.getName()+" "+super.getYear()+" "+super.getGenre()+" "+super.getDirector()+" "+mainActor+ " "+getTypeName();
		return (info);
	}
	/*
	 * returns small display of info on item
	 * pre: none
	 * post: only nessessary info is returned
	 */
	public String display() {
		String info;
		info = "|| Movies ||"+super.getName()+" "+super.getYear()+"\t||Genre: "+ super.getGenre()+"\t||Code: "+ super.getCode()+"\t||";
		return (info);
	}
	/*
	 * the string of the type of item is returned
	 * pre: none
	 * post: type in string form is returned
	 */
	public String getTypeName() {
		String info = "Movies";
		return (info);
	}
}

