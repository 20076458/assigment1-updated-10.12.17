package model;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;

import com.google.common.base.Objects;

public class Rating {
		//Variables
	 public long Id;
	 static Long   counter = 0l;
	 
	 public Long userId;
	 public Long MovieId ;
	 public double rating ;
	 
	 public ArrayList<Movies> route = new ArrayList<Movies>();
	 
	 
	 public Rating()
	 {
		 
	 }
	 //Constructor
	 
	public Rating(  Long userId, Long MovieId, double rating) 	
	{
		this.userId = userId;
		this.MovieId = MovieId;
		this.rating = rating;
		this.Id = counter ++;
	}
	
	//ToString
	@Override
	public String toString()
	{
	  return toStringHelper(this).addValue(Id)
	                             .addValue(MovieId)
	                             .addValue(userId)
	                             .addValue(rating)
	                             .toString();
	 
	 }
	
	//Hashcode
	@Override  
	public int hashCode()  
	{  
	   return Objects.hashCode(this.MovieId, this.userId, this.rating, this.Id  );  
	}

	//Boolean
	@Override
	public boolean equals(final Object obj)
	{
		if(obj instanceof Rating)
		{
			final Rating other = (Rating) obj;
			return Objects.equal(MovieId, other.MovieId)
					&& Objects.equal(userId, other.userId)
					&& Objects.equal(rating, other.rating)
					&& Objects.equal(route, other.route);
		}
		else
		{
			return false;
		}
		
	}
	
}