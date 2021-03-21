import java.io.*;

abstract class User {
	private String username;
	private String password;
	private int accessLevel;

  	/**
	  * constructor
	  * pre: none
	  * post: A user object is created and strings are set to blank. 
	  */
	public User() {
		username="";
		password="";
	}

	/**
	  * constructor
	  * pre: none
	  * post: A users object is created initialized by values from parameters. 
	  */
	public User(String u, String p,int a) {
		username=u;
		password=p;
		accessLevel = a;
	}
	
	/*
	 * Returns username of person
	 * pre: none
	 * post: username is returned
	 */
	public String getUsername() {
		return(username);
	}
	/*
	 * Returns password of person
	 * pre: none
	 * post: password is returned
	 */
	public String getPassword() {
		return(password);
	}
	/*
	 * Returns accessLevel of person
	 * pre: none
	 * post: accessLevel is returned
	 */
	public int getAccessLevel() {
		return(accessLevel);
	}
	
	/*
	 * sets username of person
	 * pre: none
	 * post: username is set
	 */
	public void setUsername(String u) {
		username = u;
	}
	/*
	 * sets password of person
	 * pre: none
	 * post: password is set
	 */
	public void setPassword(String p) {
		password = p;
	}
	
	/*
	 * adds a user to the text file
	 * pre: none
	 * post: username, passoword and access level are added
	 */
	public void add(String u, String p, int a) {
		File userFile = new File("users.txt");
	
		try {
			FileWriter out = new FileWriter(userFile,true);
			BufferedWriter writeFile = new BufferedWriter(out);
			
			writeFile.write("------------------------------");
			writeFile.newLine();
			writeFile.write(String.valueOf(a));
			writeFile.newLine();
			writeFile.write(u);
			writeFile.newLine();
			writeFile.write(p);
			writeFile.newLine();
			writeFile.close();
			out.close();
		
		}
		catch(FileNotFoundException e) {	
		} 
		catch(IOException e) {
		}
		
	}
	
	/*
	 * checks if the username entered already exists
	 * pre: none
	 * post: boolean value returned (if exists true returned)
	 */
	public boolean exists(String name) {
		boolean yes = false;
		File userFile = new File("users.txt");
	
		try {
			FileReader in = new FileReader(userFile);
			BufferedReader readText = new BufferedReader(in);
			String line;
			
			while((line = readText.readLine()) != null && !yes) {
				line = readText.readLine();
				line = readText.readLine();
				if (name.equals(line)){
					yes = true;
				}
				line = readText.readLine();
			}
			readText.close();
			in.close();
		}
		catch(FileNotFoundException e) {	
		} 
		catch(IOException e) {
		}
		
		return yes;
	}
	
	/*
	 * reads info inputted by user and checks to see if 
	 * infomation is correct in order to log in
	 * pre: none
	 * post: boolean value is returned (true if login success)
	 */
	public boolean login(String name,String pass, int access) {
		boolean yes = false;
		File userFile = new File("users.txt");
	
		try {
			FileReader in = new FileReader(userFile);
			BufferedReader readText = new BufferedReader(in);
			String line;
			
			while((line = readText.readLine()) != null && !yes) {
				access = Integer.parseInt(readText.readLine());
				if (accessLevel == access){
					line = readText.readLine();
					if (username.equals(line)){
						line = readText.readLine();
						if (password.equals(line)){
							yes = true;
						}	
					} else {
						line = readText.readLine();
					}
				}else {
					line = readText.readLine();
					line = readText.readLine();
				}
				
			}
			readText.close();
			in.close();
		}
		catch(FileNotFoundException e) {	
		} 
		catch(IOException e) {
		}
		
		return yes;
	}
	
	/*
	 * toString override method to return all info of the user
	 * pre: none
	 * post: all info regarding user is returned
	 */
	public String toString() {
		String info;
		info = username +"\n"+password+"\n";
		return info;
	}

}
	





