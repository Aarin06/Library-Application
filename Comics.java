
public class Comics extends Books {
	private int volume;

  	/**
	  * constructor
	  * pre: none
	  * post: A comics object is created and integers set to 0
	  */
	public Comics() {
		volume = 0;
	}
	
	/**
	  * constructor
	  * pre: none
	  * post: A comics object is created initialized by values from parameters. 
	  */
	public Comics(String n, int y, String g, int c,String co, String a, int v) {
		super(n,y,g,c,co,a);
		volume = v;
		super.setType(2);
	}
	
	/*
	 * Returns volume of comic
	 * pre: none
	 * post: volume is returned
	 */
	public int getVolume() {
		return(volume);
	}
	
	/*
	 * sets volume of comic
	 * pre: none
	 * post: volume of comic is set
	 */
	public void setVolume(int v) {
		volume = v;
	}
	
	/*
	 * toString override method to return all info of item
	 * pre: none
	 * post: all info regarding the item is returned
	 */
	public String toString() {
		String info;
		info = "Title: "+super.getName()+"\nYear: "+super.getYear()+"\nGenre: "+super.getGenre()+"\nAuthor: "+super.getAuthor()+"\nVolume: "+volume+"\n**Code: "+super.getCode()+"**";
		return (info);
	}
	/*
	 * returns string that is used as key words for the item during a search
	 * pre: none
	 * post: important info string is returned
	 */
	public String toString1() {
		String info;
		info = super.getName()+" "+super.getYear()+" "+super.getGenre()+" "+super.getAuthor()+" volume "+volume+ " "+getTypeName();
		return (info);
	}
	/*
	 * returns small display of info on item
	 * pre: none
	 * post: only nessessary info is returned
	 */
	public String display() {
		String info;
		info = "|| Comics ||"+super.getName()+" "+super.getYear()+"\t||Genre: "+ super.getGenre()+"\t||Code: "+ super.getCode()+"\t||";
		return (info);
	}
	/*
	 * the string of the type of item is returned
	 * pre: none
	 * post: type in string form is returned
	 */
	public String getTypeName() {
		String info = "Comics";
		return (info);
	}
}
