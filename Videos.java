//Constructors are only used so they can be used in the constructors of child classes
//since this is an abstract class

abstract class Videos extends Library{
	private int duration;
	private String director;
	
  	/**
	  * constructor
	  * pre: none
	  * post: A video object is created, integers set to 0 and strings set to blank. 
	  */
	public Videos() {
		duration = 0;
		director = "";
	}

  	/**
	  * constructor
	  * pre: none
	  * post: A videos object is created initialized by values from parameters. 
	  */
	public Videos(String n, int y, String g, int c,String co, String dd, int d) {
		super(n,y,g,c,co);
		director = dd; 
		duration = d;
	}
	
	/*
	 * Returns director of video
	 * pre: none
	 * post: director is returned
	 */
	public String getDirector() {
		return(director);
	}
	/*
	 * Returns duration of video
	 * pre: none
	 * post: duration is returned
	 */
	public int getDuration() {
		return(duration);
	}
	
	/*
	 * sets director of video
	 * pre: none
	 * post: director of video is set
	 */
	public void setDirector(String a) {
		director = a;
	}
	/*
	 * sets duration of video
	 * pre: none
	 * post: duration of video is set
	 */
	public void setDuration(int p) {
		duration = p;
	}
	
	abstract String getTypeName();
	
}

