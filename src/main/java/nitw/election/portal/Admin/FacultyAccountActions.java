package nitw.election.portal.Admin;

import nitw.election.portal.Entities.Faculty;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * author @sushithreddy75
 *
 * This singleton class has methods which make changes to
 * the database involving faculty
 * JDBC Template passed from the controller executes the
 * queries on the database
 */
public class FacultyAccountActions {
    private static FacultyAccountActions ourInstance = null;

    static FacultyAccountActions getInstance(){
        if(ourInstance==null)
            ourInstance=new FacultyAccountActions();
        return ourInstance;
    }

    private FacultyAccountActions(){

    }

    // adding a new faculty account to the database
    public void addFaculty(Faculty newFaculty, JdbcTemplate jdbcTemplate){
        jdbcTemplate.execute(newFaculty.insertQuery());
    }
}
