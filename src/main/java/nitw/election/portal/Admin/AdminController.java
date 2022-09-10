package nitw.election.portal.Admin;

import nitw.election.portal.Admin.Year;
import nitw.election.portal.Entities.Faculty;
import nitw.election.portal.Entities.Section;
import nitw.election.portal.Entities.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import org.json.simple.JSONObject;

import nitw.election.portal.Admin.Year;

import java.util.List;
import java.util.Map;


/*
 * author @sushithreddy75
 *
 * This class has the controller methods of Admin account
 * JDBC Template makes the changes in the database
 * JDBC Template initialised in this class is passed to other
 * singleton classes and the queries are executed in those
 * class methods
 */

@RestController
public class AdminController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // TO add a new year for elections into the database
    @PostMapping(value = "/admin/add-year",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Years newYear(@RequestBody Years newYear) {
        Year.getInstance().addYear(newYear,jdbcTemplate);
        return newYear;
    }

    // creating a new faculty account
    @PostMapping(value = "/admin/add-faculty",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Faculty newFaculty(@RequestBody Faculty newFaculty) {
        FacultyAccountActions.getInstance().addFaculty(newFaculty,jdbcTemplate);
        return newFaculty;
    }

    // adding a new section
    @PostMapping(value = "/admin/add-section",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Section newSection(@RequestBody Section newSection) {
        SectionActions.getInstance().addSection(newSection,jdbcTemplate);
        return newSection;
    }

    // this method updates the faculty advisors
    @PostMapping(value = "/admin/change-facad",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    JSONObject changeFacad(@RequestBody JSONObject updates) {
        SectionActions.getInstance().changeFacad(updates,jdbcTemplate);
        return updates;
    }

    // returns the list of sections and their faculty advisors
    @GetMapping("/admin/get-sections")
    List<Map<String, Object>> sectionDetails(){
        return  SectionActions.getInstance().getSectionDetails(jdbcTemplate);
    }
}
