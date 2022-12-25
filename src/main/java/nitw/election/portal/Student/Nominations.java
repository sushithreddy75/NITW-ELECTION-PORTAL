package nitw.election.portal.Student;

import nitw.election.portal.Entities.Nomination;
import org.springframework.jdbc.core.JdbcTemplate;

public class Nominations {
    private static Nominations ourInstance = null;

    static Nominations getInstance(){
        if(ourInstance==null)
            ourInstance=new Nominations();
        return ourInstance;
    }

    private Nominations(){}

    public void addNomination(JdbcTemplate jdbcTemplate, Nomination nomination){
        jdbcTemplate.execute(nomination.insertQuery());
    }
}
