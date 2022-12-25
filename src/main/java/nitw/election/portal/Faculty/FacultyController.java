package nitw.election.portal.Faculty;

import nitw.election.portal.Admin.SectionActions;
import nitw.election.portal.Student.Voting;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

@RestController
public class FacultyController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/faculty/pending-student-accounts")
    @ResponseBody
    List<Integer>  sectionDetails(@RequestParam(required = false) String id){
        return AccountVerification.getInstance().pendingStudents(
                jdbcTemplate,id
        );
    }

    @GetMapping("/faculty/verify-student-account")
    @ResponseBody
    String verifyStudentAccount(@RequestParam Integer rollNo){
         AccountVerification.getInstance().verifyAccount(
                jdbcTemplate,rollNo
         );
         return rollNo+" account Verified";
    }

    @GetMapping("/faculty/pending-nominations")
    @ResponseBody
    List<Map<String ,Object>>  pendingNominations(@RequestParam(required = false) String id){
        return VerifyNominations.getInstance().pendingVerifications(
                jdbcTemplate,id
        );
    }

    @PostMapping(value = "/faculty/verify-nomination")
    JSONObject verifyNominations(@RequestBody JSONObject nominationUpdate) {
        VerifyNominations.getInstance().verifyNomination(jdbcTemplate,nominationUpdate);
        return nominationUpdate;
    }

    @PostMapping(value = "/faculty/open-voting")
    String openVoting(@RequestBody JSONObject sectionDetails) {
        return Election.getInstance().openVoting(sectionDetails,jdbcTemplate);
    }

    @PostMapping(value = "/faculty/close-voting")
    String closeVoting(@RequestBody JSONObject sectionDetails) {
        return Election.getInstance().closeVoting(sectionDetails,jdbcTemplate);
    }

    @PostMapping(value = "/faculty/declare-results")
    JSONArray declareResults(@RequestBody JSONObject sectionDetails) {
        return Election.getInstance().declareWinner(sectionDetails,jdbcTemplate);
    }

}
