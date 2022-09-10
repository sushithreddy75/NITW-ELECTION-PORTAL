package nitw.election.portal.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * author @sushithreddy75
 *
 * This class is the template of table Students in the database
 * The class variable stores the Student details
 * Inside the database significance of the class is the Student
 * for which elections are conducted in the portal
 */

@Entity
public class Section {
    @Id
    public int sectionId;
    public String sectionName;
    public String facultyId;

    public int getSectionId(){
        return this.sectionId;
    }

    public String getSectionName(){
        return this.sectionName;
    }

    public String getFacultyId(){
        return this.facultyId;
    }

    public String insertQuery(){
        String query="insert into Section values("+
                this.getSectionId()+
                ",'"+ this.getSectionName()+"', ";
        if(this.getFacultyId()==null)
            query+=this.getFacultyId()+");";
        else
            query+="'+"+this.getFacultyId()+"');";
        System.out.println(query);
        return query;
    }
}
