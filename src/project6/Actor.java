package project6;

/**
 * This class represents the actors. Actor objects store the name as a String
 * 
 * @author Sammy Chuang
 * @version 2018.12.05
 */

public class Actor {
	private String name; 
	
	public Actor (String name) throws IllegalArgumentException {
		//throw an illegal argument exception if called with null or empty string parameter 
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Invalid name!");
		}
		//otherwise, set the name
		this.name = name; 
	}
	
	/**
	 * access an Actor's name (but can't change it, just look at it)
	 * 
	 * @param no parameters
	 * 
	 * @return the actor's name as a String
	 */
	public String getActorName() {
		return name;
	}
}
