
public class tvShows extends Videos {
	private int season;
	
	/**
	  * constructor
	  * pre: none
	  * post: A tvshows object is created and integers set to 0
	  */
	public tvShows() {
		season = 0;
	}
	
	/**
	  * constructor
	  * pre: none
	  * post: A tvshows object is created initialized by values from parameters. 
	  */
	public tvShows(String n, int y, String g, int c,String co, String dd, int d,int s) {
		super(n,y,g,c,co,dd,d);
		season = s;
		super.setType(4);
	}
	
	/*
	 * Returns number of seasons in tvshow
	 * pre: none
	 * post: number of seasons is returned
	 */
	public int getSeason() {
		return(season);
	}
	
	/*
	 * sets number of seasons in tvshow
	 * pre: none
	 * post: number of seasons of tvshow is set
	 */
	public void setSeason(int p) {
		season = p;
	}
	
	/*
	 * toString override method to return all info of item
	 * pre: none
	 * post: all info regarding the item is returned
	 */
	public String toString() {
		String info;
		info = "Title: "+super.getName()+"\nYear: "+super.getYear()+"\nGenre: "+super.getGenre()+"\nDirector: "+super.getDirector()+"\nDuration Per Episode: "+super.getDuration()+" mins\nSeasons: "+season+"\n**Code: "+super.getCode()+"**";
		return (info);
	}
	/*
	 * returns string that is used as key words for the item during a search
	 * pre: none
	 * post: important info string is returned
	 */
	public String toString1() {
		String info;
		info = super.getName()+" "+super.getYear()+" "+super.getGenre()+" "+super.getDirector()+" "+super.getDuration()+"season "+season+ " "+getTypeName();
		return (info);
	}
	/*
	 * returns small display of info on item
	 * pre: none
	 * post: only nessessary info is returned
	 */
	public String display() {
		String info;
		info = "|| TVShow ||"+super.getName()+" "+super.getYear()+"\t||Genre: "+ super.getGenre()+"\t||Code: "+ super.getCode()+"\t||";
		return (info);
	}
	/*
	 * the string of the type of item is returned
	 * pre: none
	 * post: type in string form is returned
	 */
	public String getTypeName() {
		String info = "TVShow";
		return (info);
	}
}
