package project6;

/**
 * This class extends the (self-written) LinkedList class to create Linked Lists that
 * hold Movies.
 * 
 * @author Sammy Chuang
 * @version 2018.12.05
 */

//import java.util.Collections;

public class MovieList extends LinkedList<Movie> {

	/**
	 * Iterates through the list of movies to check if any movies match
	 * the keyword provided by the user
	 *
	 * @param movieName
	 * 
	 * @return matchingMovieTitles a list of Movie objects with the 
	 * same title as the user specifies
	 */
	public MovieList getMatchingTitles( String movieName ) {
		
		//return null if theNo matches found. Try again.No matches found. Try again. parameter is an empty string or null
		if (movieName == null || movieName == "") 
		{
			return null;
		}
		
		//create a MovieList to hold all of the movies that match the movieName (parameter)
		MovieList matchingMovieTitles = new MovieList();
		
		//iterate through the movies in the list
//		for (Movie m : this )
		for (int i = 0;i < this.size(); i++) 
		{
			//get each movie's title
			//String movie = m.getTitle();
			String movie = this.get(i).getTitle();
			
			//if title is null, skip it
			if (movie == null) 
			{
				continue;
			}
			
			//otherwise, add the movie to the movielist
			if (movie.toLowerCase().contains(movieName.toLowerCase()))
			{
				matchingMovieTitles.add(this.get(i));
			}
		}
		
		matchingMovieTitles.sort();
		
		if (matchingMovieTitles.isEmpty()) {
			return null;
		}
		//return a sorted list of movies
		return matchingMovieTitles; 
	}
	
	/**
	 * Iterates through the list of movies to check if any actors have the same 
	 * name as provided by the user
	 * 
	 * @param actorName
	 * 
	 * @return matchingActors a list of Movie objects that have Actors that match
	 * the name as provided by the user
	 */

	public MovieList getMatchingActor( String actorName ) {
		
		//return null for an empty string or null parameter
		if (actorName == null || actorName == "") 
		{
			return null;
		}
		
		//create a list to store the matching actors found 
		MovieList matchingActors = new MovieList();
		
		//for (Movie m : this ) 
		for (int i = 0; i < this.size(); i++)
		{
			//for every actor in the list 
			//for (Actor a : m.getActors()) 
			for (int j = 0; j < this.get(i).getActors().size(); j++) 
			{
				if (this.get(i).getActors().get(j) == null) 
				{
					continue; 
				}
				//look at their name 
				String actor = this.get(i).getActors().get(j).getActorName();
				//String actor = a.getActorName();
				
				//if name is null, skip it
				
				if (actor.toLowerCase().contains(actorName.toLowerCase()))
				{
					matchingActors.add(this.get(i));
				}
			}
		}
		
		//sort the actors
		matchingActors.sort();
		if (matchingActors.isEmpty()) {
			return null;
		}
		
		//return the sorted ArrayList of actors
		return matchingActors; 
	}
	
	/** Overrides the .toString() method 
	 * 
	 * @param 
	 * 
	 * @return a large, singular String of the list of movies in the current MovieList
	 */
	@Override
	public String toString() {
		
		//create a string builder to make a new string to return (of all of the titles) 
		StringBuilder listOfMoviesString = new StringBuilder();
		
		for (Movie m : this ) 
		{
			String movieTitle = m.getTitle();
			listOfMoviesString.append(movieTitle + "; ");
		}
		return listOfMoviesString.toString();
	}
}