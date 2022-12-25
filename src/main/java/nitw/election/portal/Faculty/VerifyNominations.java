package nitw.election.portal.Faculty;

import nitw.election.portal.Student.StudentAccount;
import org.json.simple.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class VerifyNominations {
    private static VerifyNominations ourInstance = null;

    static VerifyNominations getInstance(){
        if(ourInstance==null)
            ourInstance=new VerifyNominations();
        return ourInstance;
    }

    private VerifyNominations(){}

    void verifyNomination(JdbcTemplate jdbcTemplate, JSONObject updates){
        String query="update Nominations " +
                "set Status='" + updates.get("status")+
                "' where PostId=" +updates.get("postId")+
                " and RollNo=" +updates.get("rollNo")+
                ";" ;
        jdbcTemplate.execute(query);
        System.out.println(query);
    }

    public List<Map<String, Object>> pendingVerifications(JdbcTemplate jdbcTemplate, String facultyId) {
        String query=pendingVerificationsQuery(facultyId);
        List<Map<String,Object>> res=jdbcTemplate.queryForList(
                query
        );
        return res;
    }
    String pendingVerificationsQuery(String facultyId){
        String query="Select * from Nominations where Status='PENDING'";
        if(facultyId==null)
            return query;
        if(facultyId.equals("dean_sw@nitw.ac.in"))
            query= "Select * from Nominations" +
                    "where Status='PENDING' and " +
                    "PostId<=8";
        else
            query="Select * from Nominations " +
                    "where Status='PENDING' and " +
                    "RollNo in (Select Nominations.RollNo from " +
                    "Nominations,Student " +
                    "where Student.RollNo=Nominations.RollNo and " +
                    "Nominations.PostId in (" +
                    "Select SectionId from Section " +
                    "where FacultyId='"+facultyId+
                    "'));";
//        System.out.println(query);
        return query;
    }
}

