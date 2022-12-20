package api.tests;


import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.asynchttpclient.util.Assertions;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import static com.shaft.api.RestActions.RequestType.*;

public class RestfullBoker_FirstReequest {
     private SHAFT.API apiObject;

    @BeforeClass
    public void beforeClass() {

        apiObject = new SHAFT.API("https://restful-booker.herokuapp.com/");
        //Authentication
        JSONObject authentcation = new JSONObject();
        authentcation.put("username", "admin");
        authentcation.put("password", "password123");

        Response createToken = apiObject.post("auth")
                .setContentType(ContentType.JSON)
                .setRequestBody(apiObject)
                .setRequestBody(authentcation)
                .perform();

        String token = RestActions.getResponseJSONValue(createToken, "token");
        apiObject.addHeader("Cookie", "token=" + token);

    }

    @Test
    public void getBookingIds() {
        //RestActions apiObject = DriverFactory.getAPIDriver("https://restful-booker.herokuapp.com/");
        //SHAFT.API apiObject = new SHAFT.API("https://restful-booker.herokuapp.com/");
        apiObject.get("booking").perform();
    }


    @Test
    public void getBooking() {
        //RestActions apiObject = DriverFactory.getAPIDriver("https://restful-booker.herokuapp.com/");
        apiObject.get("booking/1").perform();
    }


    @Test
    public void createBooking() {
        //RestActions apiObject = DriverFactory.getAPIDriver("https://restful-booker.herokuapp.com/");
        JSONObject createBookingBody = new JSONObject();
        createBookingBody.put("firstname", "AHMED");
        createBookingBody.put("lastname", "Helaly");
        createBookingBody.put("totalprice", 500);
        createBookingBody.put("depositpaid", true);
        createBookingBody.put("bookingdates", "Jim");
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2021-01-01");
        bookingdates.put("checkout", "2022-01-01");
        createBookingBody.put("bookingdates", bookingdates);
        createBookingBody.put("additionalneeds", "Breakfast");

        Response createBookingRes = apiObject.post("booking")
                .setContentType(ContentType.JSON)
                .setRequestBody(createBookingBody)
                .perform();

        String bookingId = RestActions.getResponseJSONValue(createBookingRes, "bookingid");

        Response getBookinRes = apiObject.get("booking/" + bookingId).perform();

        RestActions.getResponseJSONValue(getBookinRes, "firstname");
        RestActions.getResponseJSONValue(getBookinRes, "lastname");
        RestActions.getResponseJSONValue(getBookinRes, "bookingdates.checkin");
        RestActions.getResponseJSONValue(getBookinRes, "bookingdates.checkout");
        RestActions.getResponseJSONValue(getBookinRes, "totalprice");


//        apiObject.assertThatResponse().extractedJsonValue("firstname").isEqualTo("Ahmed").perform();
//        apiObject.assertThatResponse().extractedJsonValue("lastname").isEqualTo("Helaly").perform();
//        apiObject.assertThatResponse().extractedJsonValue("bookingdates.checkin").isEqualTo("2021-01-01").perform();
//        apiObject.assertThatResponse().extractedJsonValue("bookingdates.checkout").isEqualTo("2022-01-01").perform();
//        apiObject.assertThatResponse().extractedJsonValue("totalprice").isEqualTo("500").perform();


        apiObject.verifyThatResponse().extractedJsonValue("firstname").isEqualTo("AHMED").perform();
        apiObject.verifyThatResponse().extractedJsonValue("lastname").isEqualTo("Helaly").perform();
        apiObject.verifyThatResponse().extractedJsonValue("bookingdates.checkin").isEqualTo("2021-01-01").perform();
        apiObject.verifyThatResponse().extractedJsonValue("bookingdates.checkout").isEqualTo("2022-01-01").perform();
        apiObject.verifyThatResponse().extractedJsonValue("totalprice").isEqualTo("500").perform();

        apiObject.assertThatResponse()
                .isEqualToFileContent(System.getProperty("testDataFolderPath") + "RestfullBooker/Booking.json")
                .perform();
    }

    @Test(dependsOnMethods = {"createBooking"})
    public void deleteBooking() {

        //Delete request
        Response deleteBooking =  apiObject.delete("booking/" + "12")
                .setTargetStatusCode(201)
                .setContentType(ContentType.JSON)
                .perform();
//        String deleteBookingBody = RestActions.getResponseBody(deleteBooking);
//        apiObject.verifyThatResponse().extractedJsonValue("deleteBooking").isEqualTo("Created").perform();
//        Validations.assertThat().


//
//            RestActions apiObject = DriverFactory.getAPIDriver("https://restful-booker.herokuapp.com/");
//            //Authentication
//            JSONObject authentcation = new JSONObject();
//            authentcation.put("username", "admin");
//            authentcation.put("password", "password123");
//
//            Response createToken = (Response) apiObject.buildNewRequest("auth",POST)
//                    .setContentType(ContentType.JSON)
//                    .setRequestBody(authentcation)
//                    .perform();
//
//            String token = RestActions.getResponseJSONValue(createToken,"token");
//
//            Response getbookinId = apiObject.buildNewRequest("booking",GET)
//                    .setUrlArguments("firstname=AHMED&lastname=Helaly")
//                    .perform();
//
//            String bookingId = RestActions.getResponseJSONValue(getbookinId,"booking[0]");
//
//            //Delete
//            apiObject.buildNewRequest("booking/" + bookingId , DELETE)
//                    .setTargetStatusCode(201)
//                    .setContentType(ContentType.JSON)
//                    .addHeader("Cookie","token=" + token)
//                    .perform();

    }


}

