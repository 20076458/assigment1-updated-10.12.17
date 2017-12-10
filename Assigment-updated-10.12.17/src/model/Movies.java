package model;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class Movies {
//Variables
public long MovieId;
public static Long   counter = 0l;
public String RealeaseDate;
public String url;
public String MovieTitle;
	
// array list in Rating
 public List<Rating> movie  = new ArrayList<>();
	 
	
//Constructor
 	public Movies( String MovieTitle, String RealeaseDate, String url) {
	
	this.MovieId = counter ++;
	this.MovieTitle = MovieTitle;
	this.RealeaseDate = RealeaseDate;
	this.url = url;
	}
	
	//
	@Override
	public String toString()
	{
	  return toStringHelper(this).addValue(MovieTitle)
	                             .addValue(RealeaseDate)
	                             .addValue(url)
	                             .addValue(MovieId)
	                             .toString();
	 
	 }
	
	@Override  
	public int hashCode()  
	{  
	   return Objects.hashCode(this.MovieTitle, this.RealeaseDate, this.url, this.MovieId  );  
	}

	@Override  
	public boolean equals(final Object obj)
	{
		if (obj instanceof Movies)
		{
			final Movies other = (Movies) obj;
			return Objects.equal(MovieTitle, other.MovieTitle)
					&& Objects.equal(RealeaseDate, other.RealeaseDate)
					&& Objects.equal(url, other.url);
		}
		else
		{
			return false;
		}
	}
		
	}
	
