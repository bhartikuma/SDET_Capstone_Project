package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiUtils {

    private static final Logger log = LogManager.getLogger(ApiUtils.class);

    public static boolean isSiteReachable(String url) {
        try {
            Response response = RestAssured
                .given()
                    .relaxedHTTPSValidation()
                .when()
                    .get(url)
                .then()
                    .extract().response();

            int status = response.getStatusCode();
            log.info("[API Check] " + url + " returned HTTP " + status);
            return status == 200;

        } catch (Exception e) {
            log.error("[API Check] Failed: " + e.getMessage());
            return false;
        }
    }
}