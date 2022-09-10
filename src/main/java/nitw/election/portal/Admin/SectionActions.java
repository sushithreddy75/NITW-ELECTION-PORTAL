package nitw.election.portal.Admin;

import nitw.election.portal.Entities.Section;
import org.json.simple.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/*
 * author @sushithreddy75
 *
 * This singleton class has methods which make changes to
 * the database involving sections
 * JDBC Template passed from the controller executes the
 * queries on the database
 */
public class SectionActions {
    private static SectionActions ourInstance = null;

    static SectionActions getInstance(){
        if(ourInstance==null)
            ourInstance=new SectionActions();
        return ourInstance;
    }

    private SectionActions(){

    }

    // this method adds a new section into the database
    public void addSection(Section newSection, JdbcTemplate jdbcTemplate){
        jdbcTemplate.execute(newSection.insertQuery());
    }

    // this method is used to update the faculty advisor for a section
    public void changeFacad(JSONObject updates, JdbcTemplate jdbcTemplate){
        String query="update Section set " +
                "FacultyId='"+updates.get("facultyId")+
                "' where SectionId ="+(Integer)updates.get("sectionId")+";";
        jdbcTemplate.execute(query);
    }

    // this method returns the list of all sections
    public List<Map<String,Object>> getSectionDetails(JdbcTemplate jdbcTemplate){
        String query="select * from Section";
        return jdbcTemplate.queryForList(query);
    }
}
