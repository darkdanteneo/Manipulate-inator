package org.inator.manipulate;

public class subjectID {
    String classcode;
    String department;
    String semester;
    String sub_id;
    String sub_type;
    String subname;

    public subjectID() {
    }

    public String getSemester() {
        return this.semester;
    }

    public subjectID(String str, String str2, String str3, String str4, String str5, String str6) {
        this.semester = str;
        this.department = str2;
        this.sub_id = str3;
        this.subname = str4;
        this.classcode = str5;
        this.sub_type = str6;
    }

    public void setSemester(String str) {
        this.semester = str;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String str) {
        this.department = str;
    }

    public String getSubname() {
        return this.subname;
    }

    public void setSubname(String str) {
        this.subname = str;
    }

    public String getSub_type() {
        return this.sub_type;
    }

    public void setSub_type(String str) {
        this.sub_type = str;
    }

    public String getSub_id() {
        return this.sub_id;
    }

    public void setSub_id(String str) {
        this.sub_id = str;
    }

    public String getClasscode() {
        return this.classcode;
    }

    public void setClasscode(String str) {
        this.classcode = str;
    }
}

