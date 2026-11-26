import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@Tag("api")
public class FirstApiTest {

    RestApiBuilder api = new RestApiBuilder();
    RequestSpecification spec = api.requestSpecification;
    @Test
    public void addGoods() {

        String json = "{\n + \"name\": \"apple\" + \"price\": \"100\n" + "}";

        Response response = api.post("/goods/add", json);

        assertThat(response.getStatusCode())
                .as("Проверяем добавление товара")
                .isEqualTo(200);
    }


    @Test
    @Tag("api")
    public void getGoodsList(){
        Response response =
        given()
                .spec(api.spec)
        .when()
                .get("/goods/list");
        response.then()
                .log().all()
                .statusCode(200);
        assertThat(response.asString()).as("body is not null").isNotEmpty();
    }

    @Test
    @Tag("api")
    public void getGoodsListRequestSpecification(){
        Response response =api.get("/goods/list");
        response.then()
                .log().all()
                .statusCode(200);
        assertThat(response.asString()).as("body is not null").isNotEmpty();
    }

    @Test
    @Tag("api")
    public void getGoodsList(){
        Response response =
                given()
                        .spec(api.requestSpecification)
                        .when()
                        .get("/goods/list");
        response.then()
                .log().all()
                .statusCode(200);
        assertThat(response.asString()).as("body is not null").isNotEmpty();
    }

    @Test
    @Tag("api")
    public void addGoodsListRestAssured(){
        String json = "{\n + \"name\": \"apple\" + \"price\": \"100\n" + "}";
        api.post("/goods/list",json)
                .then()
                .log().all()
                .statusCode(200);
        Response response = api.get("/goods/list");
        response.then()
                .log().all()
                .statusCode(200);
        assertThat(response.asString()).as("list is not empty").isNotEmpty();}

        @Test
        @Tag("api")
        public void addGoodsAssertJ() {

            String json = "{\n + \"name\": \"orange\" + \"price\": \"700\n" + "}";

            Response response = api.post("/goods/add", json);

            assertThat(response.getStatusCode()).as("check add product").statusCode(200);;

            Response listResponse = api.get("/goods/list");

            assertThat(listResponse.getStatusCode()).as("get list").statusCode(200);;
            assertThat(listResponse.asString()).as("list have orange").contains("orange");
        }


        @Test
        @Tag("api")
        public void getGoodsById() {

            Response response = api.get("/goods/1");

            assertThat(response.getStatusCode()).as("check product ID").statusCode(200);;
            assertThat(response.asString()).as("body is not null").isNotEmpty();
        }

        @Test
        @Tag("api")
        public void deleteGoods() {

            Response response = api.delete("/goods/1");

            assertThat(response.getStatusCode()).as("delete product").statusCode(200);
        }


        @Test
        @Tag("api")
        public void updateGoods() {

            String json = "{\n + \"price\": \"1000000\n" + "}";

            Response response = api.patch("/goods/1", json);

            assertThat(response.getStatusCode()).as("update product").statusCode(200);
        }

        @Test
        @Tag("api")
        public void getGoodsList() {

            Response response = api.get("/goods/list");

            assertThat(response.getStatusCode()).as("get list product").statusCode(200);
            assertThat(response.asString()).as("body response").isNotEmpty();
        }


    @Test
    public void addGoodsBadRequest() {

        String json = "{\n + \"name\": \"grape\" + \"price\": \"!@$#$\n" + "}";

        Response response = api.post("/goods/add", json);

        assertThat(response.getStatusCode()).as("Check request incorrect").statusCode(400);
    }

    @Test
    public void getGoodsNotFound() {

        Response response = api.get("/goods/77777777");

        assertThat(response.getStatusCode()).as("Product not found").statusCode(404);
    }

    @Test
    public void deleteGoodsNotFound() {

        Response response = api.delete("/goods/456321456");

        assertThat(response.getStatusCode()).as("удаление товара , который отсутствует").statusCode(404);
    }

    @Test
    public void updateGoodsBadRequest() {

        String json = "{\n +  \"price\": \"№;%:?\n" + "}";

        Response response = api.patch("/goods/1", json);

        assertThat(response.getStatusCode()).as("incorrect update").statusCode(400);
    }

    @Test
    public void updateGoodsNotFound() {

        String json = "{\n + \"name\": \"orange\" + \"price\": \"56\n" + "}";

        Response response = api.patch("/goods/999999", json);

        assertThat(response.getStatusCode()) .as("обновление товара, который отсутсвует").statusCode(404);
    }

}


