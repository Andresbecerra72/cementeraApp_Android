package app.absoftware.cementera.models;

public class Reporte {
    private int id;
    private String Q1;
    private String Q2;
    private String Q3;
    private String Q4;
    private String Q5;
    private String Q6;
    private String Q7;
    private String Q8;
    private String Q9;
    private boolean Q10_chk_1;
    private boolean Q10_chk_2;
    private boolean Q10_chk_3;
    private boolean Q10_chk_4;
    private boolean Q10_chk_5;
    private boolean Q10_chk_6;
    private boolean Q10_chk_7;
    private String Q11;
    private String site;
    private String facility;
    private String insp_date;
    private String conductedby;
    private String time;
    private String data;
    private String spare;
    private String source;

    // contructor con parametros

    public Reporte(int id, String q1, String q2, String q3, String q4, String q5, String q6, String q7, String q8, String q9, boolean q10_chk_1, boolean q10_chk_2, boolean q10_chk_3, boolean q10_chk_4, boolean q10_chk_5, boolean q10_chk_6, boolean q10_chk_7, String q11, String site, String facility, String insp_date, String conductedby, String time, String data, String spare, String source) {
        this.id = id;
        Q1 = q1;
        Q2 = q2;
        Q3 = q3;
        Q4 = q4;
        Q5 = q5;
        Q6 = q6;
        Q7 = q7;
        Q8 = q8;
        Q9 = q9;
        Q10_chk_1 = q10_chk_1;
        Q10_chk_2 = q10_chk_2;
        Q10_chk_3 = q10_chk_3;
        Q10_chk_4 = q10_chk_4;
        Q10_chk_5 = q10_chk_5;
        Q10_chk_6 = q10_chk_6;
        Q10_chk_7 = q10_chk_7;
        Q11 = q11;
        this.site = site;
        this.facility = facility;
        this.insp_date = insp_date;
        this.conductedby = conductedby;
        this.time = time;
        this.data = data;
        this.spare = spare;
        this.source = source;
    }

    // contructor vacio
    public Reporte() {

    }

    // metodos getter y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQ1() {
        return Q1;
    }

    public void setQ1(String q1) {
        Q1 = q1;
    }

    public String getQ2() {
        return Q2;
    }

    public void setQ2(String q2) {
        Q2 = q2;
    }

    public String getQ3() {
        return Q3;
    }

    public void setQ3(String q3) {
        Q3 = q3;
    }

    public String getQ4() {
        return Q4;
    }

    public void setQ4(String q4) {
        Q4 = q4;
    }

    public String getQ5() {
        return Q5;
    }

    public void setQ5(String q5) {
        Q5 = q5;
    }

    public String getQ6() {
        return Q6;
    }

    public void setQ6(String q6) {
        Q6 = q6;
    }

    public String getQ7() {
        return Q7;
    }

    public void setQ7(String q7) {
        Q7 = q7;
    }

    public String getQ8() {
        return Q8;
    }

    public void setQ8(String q8) {
        Q8 = q8;
    }

    public String getQ9() {
        return Q9;
    }

    public void setQ9(String q9) {
        Q9 = q9;
    }

    public boolean isQ10_chk_1() {
        return Q10_chk_1;
    }

    public void setQ10_chk_1(boolean q10_chk_1) {
        Q10_chk_1 = q10_chk_1;
    }

    public boolean isQ10_chk_2() {
        return Q10_chk_2;
    }

    public void setQ10_chk_2(boolean q10_chk_2) {
        Q10_chk_2 = q10_chk_2;
    }

    public boolean isQ10_chk_3() {
        return Q10_chk_3;
    }

    public void setQ10_chk_3(boolean q10_chk_3) {
        Q10_chk_3 = q10_chk_3;
    }

    public boolean isQ10_chk_4() {
        return Q10_chk_4;
    }

    public void setQ10_chk_4(boolean q10_chk_4) {
        Q10_chk_4 = q10_chk_4;
    }

    public boolean isQ10_chk_5() {
        return Q10_chk_5;
    }

    public void setQ10_chk_5(boolean q10_chk_5) {
        Q10_chk_5 = q10_chk_5;
    }

    public boolean isQ10_chk_6() {
        return Q10_chk_6;
    }

    public void setQ10_chk_6(boolean q10_chk_6) {
        Q10_chk_6 = q10_chk_6;
    }

    public boolean isQ10_chk_7() {
        return Q10_chk_7;
    }

    public void setQ10_chk_7(boolean q10_chk_7) {
        Q10_chk_7 = q10_chk_7;
    }

    public String getQ11() {
        return Q11;
    }

    public void setQ11(String q11) {
        Q11 = q11;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getInsp_date() {
        return insp_date;
    }

    public void setInsp_date(String insp_date) {
        this.insp_date = insp_date;
    }

    public String getConductedby() {
        return conductedby;
    }

    public void setConductedby(String conductedby) {
        this.conductedby = conductedby;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSpare() {
        return spare;
    }

    public void setSpare(String spare) {
        this.spare = spare;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
} // END class
