package objects;

import utilities.JacksonUtils;

import java.io.IOException;
import java.util.Objects;

public class Member {

    private int id;
    private String note;
    private String firstName;
    private String lastName;
    private String title;
    private String company;
    private String phoneNumber;
    private String website;
    private String email;


    public Member() {
    }

    @Override
    public String toString() {
        return note + " [first Name= " + firstName + ", last Name= " + lastName + "]";
    }

    /* Will take the member ID and set */
    public Member(int id) throws IOException {
        Member[] members = JacksonUtils.deSerializationJSON("members.json", Member[].class);
        for (Member member : members) {
            if (member.getId() == id) {
                this.id = id;
                this.note = member.getNote();
                this.firstName = member.getFirstName();
                this.lastName = member.getLastName();
                this.title = member.getTitle();
                this.company = member.getCompany();
                this.phoneNumber = member.getPhoneNumber();
                this.website = member.getWebsite();
                this.email = member.getEmail();
            }
        }
    }

    public boolean equals(int id, String firstName, String lastName, String title, String company, String phoneNumber, String website, String email) {
        return this.id == id &&
                Objects.equals(this.firstName, firstName) &&
                Objects.equals(this.lastName, lastName) &&
                Objects.equals(this.title, title) &&
                Objects.equals(this.company, company) &&
                Objects.equals(this.phoneNumber, phoneNumber) &&
                Objects.equals(this.website, website) &&
                Objects.equals(this.email, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Member))
            return false;
        Member mem = ((Member) obj);
        return Objects.equals(this.firstName, mem.getFirstName()) &&
                Objects.equals(this.lastName, mem.getLastName()) &&
                Objects.equals(this.title, mem.getTitle()) &&
                Objects.equals(this.company, mem.getCompany()) &&
                Objects.equals(this.email, mem.getEmail()) &&
                nonMandatoryFieldsValidation(this.phoneNumber, mem.getPhoneNumber()) &&
                nonMandatoryFieldsValidation(this.website, mem.getWebsite());
    }

    private boolean nonMandatoryFieldsValidation(String actual, String expected) {
        return Objects.equals(actual, expected) || (actual.isEmpty() && expected == null);
    }


    public Member(int id, String name) {
        this.id = id;
        this.firstName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
