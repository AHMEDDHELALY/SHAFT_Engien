//package api.tests;
//
//import com.shaft.api.RestActions;
//import com.shaft.driver.SHAFT;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.json.simple.JSONObject;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//public class RestfullBooker {
//    private SHAFT.API apiObject;
//
//    @BeforeClass
//    public void beforeClass() {


//        //Authentication
//        JSONObject authentcation = new JSONObject();
//        authentcation.put("username", "admin");
//        authentcation.put("password", "password123");
//
//        Response createToken = apiObject.post("auth")
//                .setContentType(ContentType.JSON)
//                .setRequestBody(apiObject)
//                .setRequestBody(authentcation)
//                .perform();
//
//        String token = RestActions.getResponseJSONValue(createToken, "token");
//        apiObject.addHeader("Cookie", "token=" + token);
//
//    @Test
//    public void f() {
//
//    }
//
//}
