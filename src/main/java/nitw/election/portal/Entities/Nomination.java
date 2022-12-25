package nitw.election.portal.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nomination {
    @Id
    public int postId;
//    @Id
    public int rollNo;
    public String status;
    public int votesPolled;

    private int getPostId(){
        return this.postId;
    }

    private int getRollNo(){
        return this.rollNo;
    }

    private String getStatus(){
        return this.status;
    }

    private int getVotesPolled(){
        return this.votesPolled;
    }

    private void setStatus(){
        this.status="PENDING";
    }

    private void setVotesPolled(){
        this.votesPolled=0;
    }

    public String insertQuery(){
        this.setStatus();
        this.setVotesPolled();
        return "insert into Nominations " +
                "values (" +this.getPostId()+
                ", " +this.getRollNo()+
                ", '" +this.getStatus()+
                "', " +this.getVotesPolled()+
                ");";
    }
}
