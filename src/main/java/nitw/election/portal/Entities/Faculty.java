package nitw.election.portal.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * author @sushithreddy75
 *
 * This class is the template of table Faculty in the database
 * The class variable stores the Faculty details
 * Inside the database significance of the class is the Faculty
 * who are involved in the elections
 */
@Entity
public class Faculty {
    @Id
    public String facultyId;
    public String password;
    public String facultyName;

    public String getFacultyId() {
        return this.facultyId;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFacultyName(){
        return this.facultyName;
    }

    // initially set which can be changed by faculty later
    public void setPassword(){
        this.password="faculty@nitw";
    }

    // insert query for faculty details
    public String insertQuery(){
        this.setPassword();
        return "insert into Faculty values ('"+
                this.getFacultyId()+
                "','"+ this.getPassword()+
                "','"+ this.getFacultyName()+"');";
    }

    public static String getFacultySetionsQuery(String FacultyId){
        return "select SectionId from " +
                "Section " +
                "where Section.FacultyId='"+
                FacultyId+
                "';";
    }
}
