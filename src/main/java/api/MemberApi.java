package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import objects.Member;

public class MemberApi {
    Header header = new Header("content-type", "application/x-www-form-urlencoded");
    Headers headers = new Headers(header);

    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }

    public MemberApi() {
        super();
    }

    public MemberApi(Cookies cookies) {
        super();
        this.cookies = cookies;
    }

    @Override
    public String toString() {
        return "MemberApi [cookies=" + cookies + "]";
    }

    public Member getMemberById(int memberId) {
        /* No cookies related, but leave it here just in case */
        if (cookies == null) {
            cookies = new Cookies();
        }

        Response response = ApiRequest.get("/members/" + memberId, headers, cookies);
        this.cookies = response.getDetailedCookies();
        return getMember(response.getBody().asPrettyString());
    }

    public int getTotalMember() {
        /* No cookies related, but leave it here just in case */
        if (cookies == null) {
            cookies = new Cookies();
        }

        Response response = ApiRequest.get("/members/", headers, cookies);
        this.cookies = response.getDetailedCookies();
        Member[] listMember =  getListMember(response.getBody().asPrettyString());
        return listMember.length;
    }

    private Member[] getListMember(String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(value, Member[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Member getMember(String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(value, Member.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
