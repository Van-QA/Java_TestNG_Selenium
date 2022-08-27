package api;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiRequest extends SpecBuilder {
    public static Response post(String endPoint, Headers headers, HashMap<String, Object> formParams, Cookies cookies) {

        StringWriter writerRequest = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writerRequest, StandardCharsets.UTF_8), true);

        return given(getRequestSpec()).filter(new RequestLoggingFilter(captor)).
                headers(headers).formParams(formParams).cookies(cookies).when().post(endPoint).then()
                .spec(getResponseSpec()).extract().response();
    }

    public static Response get(String endPoint, Headers headers, Cookies cookies) {
        StringWriter writerRequest = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writerRequest, StandardCharsets.UTF_8), true);

        return given(getRequestSpec()).filter(new RequestLoggingFilter(captor)).
                headers(headers).
                cookies(cookies).when().get(endPoint).then().spec(getResponseSpec()).extract().response();
    }
}
