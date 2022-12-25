package nitw.election.portal.Student;

import org.json.simple.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

public class Voting {
    private static Voting ourInstance=null;
    static Voting getInstance(){
        if(ourInstance==null)
            ourInstance=new Voting();
        return ourInstance;
    }

    private Voting(){}

    String castVote(JdbcTemplate jdbcTemplate, JSONObject vote){
        int postId= (int) vote.get("postId");
        int rollNO= (int) vote.get("rollNo");
        int candidateRollNO= (int) vote.get("candidateRollNo");
        String eligibilityCheck= EligibilityCheckQuery(postId,rollNO);
        System.out.println(eligibilityCheck);
        Integer eligibility= jdbcTemplate.queryForObject(eligibilityCheck,  Integer.class);
        if(eligibility==null||eligibility==0)
            return "NOT ELIGIBLE TO VOTE";
        Integer isOpen= jdbcTemplate.queryForObject("Select is_open from Post where PostId="+postId,
                Integer.class);
        if(isOpen==null)
            return "Post doesnt exist";
        if(isOpen != 1)
            return "Voting for this post is closed";
        String candidateQuery=getCandidateDetails(postId, candidateRollNO);
        Integer votes=jdbcTemplate.queryForObject(candidateQuery, Integer.class);
        if(votes==null)
            return "Candidate does not exist";
        votes++;
        String addVoteQuery=addVoteQuery(candidateRollNO, postId, votes);
        jdbcTemplate.execute(addVoteQuery);
        jdbcTemplate.execute(recordVote(postId,rollNO));
        return "Vote casted Succesfully";
    }

    String recordVote(int postId, int rollNo){
        String query="insert into VotesPolled " +
                "values(" +postId+
                "," +rollNo+
                ");";
        return query;
    }
    String addVoteQuery(int candidateRollNo, int postId, int votes){
        String query= "update Nominations " +
                "set VotesPolled="+ votes+
                " where PostId=" +postId+
                " and RollNo="+candidateRollNo;
        return query;
    }
    String getCandidateDetails(int postId, int candidateRollNo){
        String query="Select VotesPolled " +
                "from Nominations " +
                "where PostId=" +postId+
                " and RollNo="+candidateRollNo;
        return query;
    }

    String EligibilityCheckQuery(int postId, int rollNo){
        // have to check if he already casted the vote and if he is eligible to vote
        String query=new String();
        if(postId<=8){
            query="Select count(*) from Student " +
                    "where RollNo in (" +
                    "Select RollNo from ElectedBody) and " +
                    "RollNo not in (" +
                    "Select RollNo from VotesPolled " +
                    "where PostId=" +postId+
                    ") and RollNo="+rollNo;
        }
        else{
            query="Select count(*) from Student " +
                    "where SectionId=" +postId+
                    " and " +
                    "RollNo not in (" +
                    "Select RollNo from VotesPolled " +
                    "where PostId=" +postId+
                    ") and RollNo="+rollNo;
        }
        return query;
    }
}
