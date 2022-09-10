package nitw.election.portal.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * author @sushithreddy75
 *
 * This class is the template of table Years in the database
 * The class variable stores the year
 * Inside the database significance of the class is the years
 * for which elections are conducted in the portal
 */
@Entity // This tells Hibernate to make a table out of this class
public class Years {
    @Id
    public int year;
    public int getYear(){
        return this.year;
    }

    // returns a String of SQL query to insert current object into the database
    public String insertQuery(){
        String query="INSERT INTO Years VALUES("+this.getYear()+")"; //SQL query
        return query;
    }

}
