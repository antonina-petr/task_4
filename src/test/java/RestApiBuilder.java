import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestApiBuilder {
    private final String login = "admin";
    private final String password = "secret123";
    private final String baseUrl = "http://localhost:8080/";

    RequestSpecification requestSpecification = new RequestSpecBuilder()
        .setBaseUri(baseUrl)
        .setContentType("application/json")
        .setAuth(
                RestAssured.basic(login, password)
        )

        .build();

    public Response get(String endPoint){
        Response response =
                given()
                        .spec(requestSpecification)
                .when()
                        .get(endPoint);
        return response;
    }

    public Response post(String endPoint , Object body){
        Response response =
                given()
                  .spec(requestSpecification)
                  .body(body)
                .when()
                   .post(endPoint);
        return response;
    }

    public Response patch(String endPoint , Object body){
        Response response =
                given()
                        .spec(requestSpecification)
                        .body(body)
                .when()
                        .patch(endPoint);
        return response;
    }

    public Response delete(String endPoint ){
        Response response =
                given()
                        .spec(requestSpecification)
                .when()
                        .delete(endPoint);
        return response;
    }

}
