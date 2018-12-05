package project6;

/**
 *  This class represents each individual movie's title,
 

 *  release year, the list of locations it was filmed at, the director
 *  the writer, the list of actors and the list of locations.
 *  
 * @author Sammy Chuang
 * @version 2018.12.05
 */

import java.util.ArrayList; 

public class Movie implements Comparable<Movie> {
	
	private String title; 
	private int year; 
	private String director; 
	private String writer;
	private ArrayList<Actor> actors = new ArrayList<>(); 
	private ArrayList<Location> locations = new ArrayList<>();
	
	//constructors
	public Movie (String title, int year) {
		//throw an illegal argument exception for an empty string or null string
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid string!"); 
		}
		//throw illegal argument exception for a year too early or too late
		if (year < 1900 || year > 2020) 
		{
			throw new IllegalArgumentException("Invalid year!");
		}
		
		this.title = title;
		this.year = year; 
	}
	
	public Movie (String title, int year, String director, String writer, Actor actor1, Actor actor2, Actor actor3) {
		//throw an illegal argument exception for an empty string or null string
		if (title == null || title.length() == 0) 
		{
			throw new IllegalArgumentException("Invalid title");
		}
		// throw illegal argument exception for a year too early or too late
		if (year < 1900 || year > 2020) 
		{
			throw new IllegalArgumentException("Invalid year!");
		}
		//throw illegal argument exception if actor1 is given null input
		if (actor1 == null) 
		{
			throw new IllegalArgumentException("Need at least one actor");
		}
		else {
			actors.add(actor1);
			
			if (actor2 != null) {
				actors.add(actor2);
			}
			
			if (actor3 != null) {
				actors.add(actor3);
			}
		}
		
		this.title = title; 
		this.year = year;
		this.director = director; 
		this.writer = writer; 
	}
	
	/** 
	 * Adds the location at which a film was filmed to the 
	 * ArrayList of locations
	 * 
	 * @param loc a Location object that represents the film site
	 * 
	 * @return  void
	 */
	
	public void addLocation (Location loc) {
		//throw illegal argument exception if the location is null
		if (loc == null) 
		{
			throw new IllegalArgumentException("Invalid location!");
		}
		//if location is valid, add it to the list of locations
		else 
		{
			locations.add(loc);
		}
	}
	
	/**
	 * Overrides the .equals method and implements own and checks if
	 * the parameter object is an instance of the another object
	 * 
	 * @param otherMovie: an Object
	 * 
	 * return true if the given object is an instance of another object
	 * and false if not.
	 */
	
	@Override
	public boolean equals (Object otherObj) 
	{
		if (otherObj instanceof Movie) {
			String otherMovieTitle = ((Movie)otherObj).getTitle();
			int otherMovieYear = ((Movie)otherObj).getYear();
			if (otherMovieTitle.equals(this.title) && otherMovieYear == this.year) {
				return true;
			}
			else {
					return false;
				}
			}
		return false; 
	}
	
	/**
	 * Overrides the .compareTo method and implements own that checks
	 * to see if two movies are identical (same title, same year)
	 * 
	 * @param otherMovie an Object of Movie type 
	 * 
	 * @return 0 if the movies are the same, 1 if the current movie's title is 
	 * longer than the parameter Movie. -1 otherwise. 
	 */
	
	@Override
	public int compareTo (Movie otherMovie) {
		//compare the years each movie was released.
		if (this.year == otherMovie.year) 
		{
			//if the years match, compare the titles
			if (this.title.equalsIgnoreCase(otherMovie.title)) 
			{
				//if the titles also match, return 0 indicating that both movies are 
				//the same
				return 0;
			}
			else if (this.title.compareToIgnoreCase(otherMovie.title) > 0) 
			{
				//else if, let the user know that the otherMovie title is shorter
				return 1;
			}
			else 
			{
				//otherwise, let them know that it is longer
				return -1;
			}
		}
		
		//let the user know that the initial movie is older than the otherMovie parameter
		else if (this.year > otherMovie.year) 
		{
			return 1;
		}
		
		//otherwise, the otherMovie parameter is older than this current movie
		else 
		{
			return -1;
		}
	}
	
	/**
	 * Overrides the .toString method and builds a large string
	 * with specific formatting to print data in columns and rows
	 * 
	 * @param 
	 * 
	 * @return returnString, a single, long string that is the entire table/chart
	 * of the movie title, year, director, writer, stars, and locations. 
	 */
 
	@Override 
	public String toString() {
		//create a stringBuilder to construct one large string to return
		StringBuilder returnString = new StringBuilder();
		
		//using .append and String.format, add formatted strings to the larger string
		//creating rows and columns 
		returnString.append(title + " (" + Integer.toString(year) + ")\n");
		returnString.append("-------------------------------------------------\n");
		returnString.append(String.format("director : %20s %n", director));
		returnString.append(String.format("writer : %20s %n", writer));
		returnString.append(String.format("starring :		"));
		for (int i = 0; i < actors.size(); i++) {
			if (actors.get(i) == null) {
				continue;
			}
			else {
				returnString.append(String.format("%2s, ", actors.get(i).getActorName()));
			}
		}
		
		returnString.append("\n");
		returnString.append("filmed on location at: \n");

		for (int j = 0; j < locations.size(); j++) {
			returnString.append(String.format("Location " + (j + 1) + " %20s", locations.get(j).getLocationName()));
			if (!locations.get(j).getFunFact().equals("") && locations.get(j).getFunFact() != null) {
				returnString.append("(Fun Fact: " + locations.get(j).getFunFact() + ") \n");
			}
			else {
				returnString.append("\n");
			}
			//but now what if fun fact is an empty string? 
		}
		
		//return the very large string
		return returnString.toString();
	}
	

	/**
	 * Getters and setters for the title, year, locations, director, writer,
	 * and Actors
	 * 
	 * @param Setters take strings, integers or Actors as parameters
	 * and set the local variables equal to the new, ones (parameters)
	 * 
	 * @return Getters return strings, integers and ArrayLists
	 */
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle( String newTitle ) 
	{
		this.title = newTitle; 
	}
	
	//year
	public int getYear() 
	{
		return year;
	}
	
	public void setYear( int newYear ) 
	{
		this.year = newYear; 
	}
	
	//list of locations 
	public ArrayList<Location> getLocations() 
	{
		return locations;
	}
	
	public void setLocations ( ArrayList<Location> newLocations ) 
	{
		this.locations = newLocations;
	}
	
	//director 
	public String getDirector() 
	{
		return director;
	}
	
	public void setDirector( String newDirector ) 
	{
		this.director = newDirector; 
	}
	
	//writer
	public String getWriter() 
	{
		return writer;
	}
	
	public void setWriter( String newWriter )
	{
		this.writer = newWriter; 
	}
	
	//actor
	public ArrayList<Actor> getActors() 
	{
		return actors; 
	}
	
	public void setActor( Actor newActor ) 
	{
		actors.add(newActor);
	}
}
