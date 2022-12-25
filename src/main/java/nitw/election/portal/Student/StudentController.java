package nitw.election.portal.Student;

import nitw.election.portal.Admin.SectionActions;
import nitw.election.portal.Admin.Year;
import nitw.election.portal.Entities.Nomination;
import nitw.election.portal.Entities.Student;
import nitw.election.portal.Entities.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/student/create-account")
    Student newStudent(@RequestBody Student newStudent) {
        System.out.println("/student/create-account");
        StudentAccount.getInstance().addStudent(newStudent,jdbcTemplate);
        return newStudent;
    }

    @GetMapping("/student")
    int sectionDetails(){
        return  0;
    }

    @PostMapping(value = "/student/nominate")
    Nomination newNomination(@RequestBody Nomination newNomination) {
//        System.out.println("/student/create-account");
        Nominations.getInstance().addNomination(jdbcTemplate,newNomination);
        return newNomination;
    }

    @PostMapping(value = "/student/cast-vote")
    String castVote(@RequestBody JSONObject vote) {
//        System.out.println("/student/create-account");
        return Voting.getInstance().castVote(jdbcTemplate,vote);
//        return newNomination;
    }

}
