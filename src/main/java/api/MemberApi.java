package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import objects.Member;

public class MemberApi {

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
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        /* No cookies related, but leave it here just in case */
        if (cookies == null) {
            cookies = new Cookies();
        }

        Response response = ApiRequest.get("/members/" + memberId, headers, cookies);
        this.cookies = response.getDetailedCookies();
        return getMember(response);

    }

    private Member getMember(Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.getBody().asPrettyString(), Member.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
