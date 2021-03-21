
public class Novels extends Books {
	private int pageNum;

	/**
	  * constructor
	  * pre: none
	  * post: A novel object is created and integers set to 0
	  */
	public Novels() {
		pageNum = 0;
	}
	
	/**
	  * constructor
	  * pre: none
	  * post: A novel object is created initialized by values from parameters. 
	  */
	public Novels(String n, int y, String g, int c,String co, String a, int p) {
		super(n,y,g,c,co,a);
		pageNum = p;
		super.setType(1);
	}
	
	/*
	 * Returns page number of novel
	 * pre: none
	 * post: page number is returned
	 */
	public int getPageNum() {
		return(pageNum);
	}
	
	/*
	 * sets page number of comic
	 * pre: none
	 * post: page number of comic is set
	 */
	public void setPageNum(int p) {
		pageNum = p;
	}

	/*
	 * toString override method to return all info of item
	 * pre: none
	 * post: all info regarding the item is returned
	 */
	public String toString() {
		String info;
		info = "Title: "+super.getName()+"\nYear: "+super.getYear()+"\nGenre: "+super.getGenre()+"\nAuthor: "+super.getAuthor()+"\nNumber of Pages: "+pageNum+"\n**Code: "+super.getCode()+"**";
		return (info);
	}
	/*
	 * returns string that is used as key words for the item during a search
	 * pre: none
	 * post: important info string is returned
	 */
	public String toString1() {
		String info;
		info = super.getName()+" "+super.getYear()+" "+super.getGenre()+" "+super.getAuthor()+" "+getTypeName();
		return (info);
	}
	/*
	 * returns small display of info on item
	 * pre: none
	 * post: only nessessary info is returned
	 */
	public String display() {
		String info;
		info = "|| Novels ||"+super.getName()+" "+super.getYear()+"\t||Genre: "+ super.getGenre()+"\t||Code: "+ super.getCode()+"\t||";
		return (info);
	}
	/*
	 * the string of the type of item is returned
	 * pre: none
	 * post: type in string form is returned
	 */
	public String getTypeName() {
		String info = "Novels";
		return (info);
	}
}
