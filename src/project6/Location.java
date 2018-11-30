package project6;

/**
 * This class represents the location at which a film was shot
 * and the fun fact related to that film
 * 
 * @author Sammy Chuang
 * @version 2018.12.05
 */

public class Location {
	private String location;
	private String funFact;
	
	public Location (String loc, String funFact) throws IllegalArgumentException {
		//throw an exception if the location is null or an empty string
		if (loc == null || loc == "") 
		{
			throw new IllegalArgumentException("Invalid location");
		}
		
		//otherwise, set the location
		else 
		{
			this.location = loc;
			this.funFact = funFact; 
		}
	}
	
	/**
	 * access to the location (but can't change it)
	 * 
	 * @return the location of the film as a String
	 */
	
	public String getLocationName() 
	{
		return location; 
	}
	
	/**
	 * access to the fun fact (but can't change it)
	 * 
	 * 
	 * @return the fun fact associated with the film and location
	 * as a String
	 */
	
	public String getFunFact() {
		return funFact; 
	}
}
