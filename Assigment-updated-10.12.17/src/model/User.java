package model;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class User {
	//variables
	public String FName, LName, Age, Gender, Job;
	public Long UserId;
	public Object movies;
	public static Long   counter = 0l;
	//constructor
	public User(String fname, String lName, String age, String gender, String job, Long userId) {
		
		this.FName = fname;
		this.LName = lName;
		this.Age = age;
		this.Gender = gender;
		this.Job = job;
		this.UserId = counter++;
	}
	
	@Override
	public String toString()
{
  return toStringHelper(this).addValue(FName)
                             .addValue(LName)
                             .addValue(Age)
                             .addValue(Gender)   
                             .addValue(Job) 
                             .addValue(UserId) 
                             .toString();
}
		
		
	
	public int hashCode()  
{  
   return Objects.hashCode(this.FName, this.LName, this.Age, this.Gender , this.Job);  
}
	public boolean equals(final Object obj)
{
	if (obj instanceof User)
	{
		final User other = (User) obj;
		return Objects.equal(FName, other.FName)
				&& Objects.equal(LName, other.LName)
				&& Objects.equal(Age, other.Age)
				&& Objects.equal(Gender, other.Gender)
				&& Objects.equal(Job, other.Job)
				&& Objects.equal(UserId, other.UserId);
	}
	else
	{
		return false;
	}
}
}