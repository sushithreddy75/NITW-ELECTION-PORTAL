package nitw.election.portal.Admin;

import nitw.election.portal.Entities.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/*
 * author @sushithreddy75
 *
 * This singleton class has methods which make changes to
 * the database involving year
 * JDBC Template passed from the controller executes the
 * queries on the database
 */
public class Year {
    // This method adds year into the Database by running SQL query
    // using JDBC Template
    private static Year ourInstance=null;

    public static Year getInstance(){
        if(ourInstance==null)
            ourInstance=new Year();
        return ourInstance;
    }

    private Year(){}
    public void addYear(Years newYear, JdbcTemplate jdbcTemplate){
        // executes the SQL query obtained from the method

        jdbcTemplate.update(newYear.insertQuery());
    }

    // this method returns max value of all the years entered which is current year
    public List<Map<String, Object>> sectionDetails(JdbcTemplate jdbcTemplate){
        String query="select max(year) from Section";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        return  rows;
    }
}
