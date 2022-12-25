package nitw.election.portal.Faculty;


import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountVerification {
    private static AccountVerification ourInstance = null;

    static AccountVerification getInstance(){
        if(ourInstance==null)
            ourInstance=new AccountVerification();
        return ourInstance;
    }

    private AccountVerification(){}

    List<Integer> pendingStudents(JdbcTemplate jdbcTemplate, String facultyId){
        List<Map<String,Object>> res=jdbcTemplate.queryForList(
                pendingStudentsQuery(facultyId)
        );
        List<Integer> pendingList=new ArrayList<>();
        for (Map<String, Object> re : res) {
            pendingList.add((Integer) re.get("RollNo"));
        }
        return pendingList;
    }
    String pendingStudentsQuery(String facultyId){
        String query="Select * from PendingVerification";
        if(facultyId==null)
            return query;
        query="Select PendingVerification.RollNo from " +
                "PendingVerification,Student " +
                "where Student.RollNo=PendingVerification.RollNo and " +
                "Student.SectionId in (" +
                "Select SectionId from Section " +
                "where FacultyId='"+facultyId+
                "');";
//        System.out.println(query);
        return query;
    }

    public void verifyAccount(JdbcTemplate jdbcTemplate, Integer rollNo){
        String query="delete from PendingVerification where RollNo="+
                rollNo+";";
        jdbcTemplate.execute(query);
    }
}