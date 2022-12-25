package nitw.election.portal.Student;

import nitw.election.portal.Entities.Student;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentAccount {
    private static StudentAccount ourInstance = null;

    static StudentAccount getInstance(){
        if(ourInstance==null)
            ourInstance=new StudentAccount();
        return ourInstance;
    }

    private StudentAccount(){}

    public void addStudent(Student newStudent, JdbcTemplate jdbcTemplate){
        jdbcTemplate.execute(newStudent.insertQuery());
        jdbcTemplate.execute(newStudent.verificationQuery());
    }
}
