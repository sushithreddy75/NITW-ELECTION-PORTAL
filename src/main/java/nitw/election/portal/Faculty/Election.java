package nitw.election.portal.Faculty;

import nitw.election.portal.Entities.Faculty;
import nitw.election.portal.Entities.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Election {
    private static Election ourInstance = null;

    static Election getInstance(){
        if(ourInstance==null)
            ourInstance=new Election();
        return ourInstance;
    }

    private Election(){}

    public String openVoting(JSONObject sectionDetails, JdbcTemplate jdbcTemplate){
        int sectionId= (int) sectionDetails.get("sectionId");
        String facultyId= (String) sectionDetails.get("facultyId");
        if(sectionId<=8 && !facultyId.equals("dean_sw@nitw.ac.in"))
            return "invalid request";
        List<Integer> sectionList= jdbcTemplate.queryForList(
                Faculty.getFacultySetionsQuery(facultyId),Integer.class
        );
        int flag=0;
        for(Integer re:sectionList){
            if(re.equals(sectionId))
                flag=1;
        }
        if(flag==0&&sectionId>8)
            return "invalid request";
        jdbcTemplate.execute(openVotingQuery(sectionId));
        return "voting opened succesfully";
    }

    public String openVotingQuery(int sectionId){
        String query="Update Post " +
                "set is_open=1 where ";
        if(sectionId==0)
            query+="PostId<=8;";
        else
            query+="PostId=" +sectionId+
                    ";";
        return query;
    }

    public String closeVoting(JSONObject sectionDetails, JdbcTemplate jdbcTemplate){
        int sectionId= (int) sectionDetails.get("sectionId");
        String facultyId= (String) sectionDetails.get("facultyId");
        if(sectionId<=8 && !facultyId.equals("dean_sw@nitw.ac.in"))
            return "invalid request";
        List<Integer> sectionList= jdbcTemplate.queryForList(
                Faculty.getFacultySetionsQuery(facultyId),Integer.class
        );
        int flag=0;
        for(Integer re:sectionList){
            if(re.equals(sectionId))
                flag=1;
        }
        if(flag==0&&sectionId>8)
            return "invalid request";
        jdbcTemplate.execute(closeVotingQuery(sectionId));
        return "voting closed succesfully";
    }

    public String closeVotingQuery(int sectionId){
        String query="Update Post " +
                "set is_open=0 where ";
        if(sectionId==0)
            query+="PostId<=8;";
        else
            query+="PostId=" +sectionId+
                    ";";
        return query;
    }

    public JSONArray declareWinner(JSONObject sectionDetails, JdbcTemplate jdbcTemplate){
        int sectionId= (int) sectionDetails.get("sectionId");
        int year= (int) sectionDetails.get("year");
        String facultyId= (String) sectionDetails.get("facultyId");
        if(sectionId<=8 && !facultyId.equals("dean_sw@nitw.ac.in"))
            return null;
        List<Integer> sectionList= jdbcTemplate.queryForList(
                Faculty.getFacultySetionsQuery(facultyId),Integer.class
        );
        int flag=0;
        for(Integer re:sectionList){
            if(re.equals(sectionId))
                flag=1;
        }
        if(flag==0&&sectionId>8)
            return null;
        JSONArray winners=new JSONArray();
        try {
            if (sectionId == 0) {
                for (int i = 1; i <= 8; i++) {
                    Integer rollNo = jdbcTemplate.queryForObject(resultsQuery(i), Integer.class);
                    jdbcTemplate.execute(updateWinners(i, rollNo, year));
                    winners.add(rollNo);
                }
            }
            else {
                Integer rollNo = jdbcTemplate.queryForObject(resultsQuery(sectionId), Integer.class);
                jdbcTemplate.execute(updateWinners(sectionId, rollNo, year));
                winners.add(rollNo);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return winners;
    }

    String resultsQuery(int sectionId){
        return "Select RollNo from Nominations " +
                "where VotesPolled in ( " +
                "Select max(VotesPolled) from Nominations " +
                "where PostId=" +sectionId+
                ") and " +
                "PostId="+sectionId+";";
    }

    String updateWinners(int sectionId, int rollNo, int year){
        return "insert into ElectedBody " +
                "values(" +year+
                "," +sectionId+
                "," +rollNo+
                ");";
    }
}
