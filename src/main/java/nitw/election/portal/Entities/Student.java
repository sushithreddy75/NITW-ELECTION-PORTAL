package nitw.election.portal.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    public int studentRollNo;
    public String password;
    public String studentName;
    public float cgpa;
    public int sectionId;

    public int getStudentRollNo(){
        return this.studentRollNo;
    }

    public String getPassword(){
        return this.password;
    }

    public String getStudentName(){
        return this.studentName;
    }

    public float getCgpa(){
        return this.cgpa;
    }

    public int getSectionId(){
        return this.sectionId;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String insertQuery(){
        return "insert into Student values ('"+
                this.getStudentRollNo()+
                "','"+ this.getPassword()+
                "','"+ this.getStudentName()+
                "','"+ this.getCgpa()+
                "','"+ this.getSectionId()+
                "');";
    }

    public String verificationQuery(){
        return "insert into PendingVerification values ('"+
                this.getStudentRollNo()+
                "');";
    }
}
