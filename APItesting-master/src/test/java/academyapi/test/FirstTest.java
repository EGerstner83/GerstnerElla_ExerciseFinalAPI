package academyapi.test;

import java.util.HashMap;
import java.util.Map;

import academyapi.controlador.*;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * First API test
 *
 * @author Ella Gerstner
 */

public class FirstTest extends DataTransactions {

    private static String content = "application/json";
    private static String baseURL = "https://608432f89b2bed0017040ccc.mockapi.io/BankTransactions";
    private static Response response;

    /**
     * Test to execute the cleaning of the data
     *
     * @author Ella Gerstner
     */
    @Description(value = "Validate the system has not data ID:0001")
    @Test(priority = 0)
    public void deleteData() throws InterruptedException {
        int code = 200;
        response = given().get(baseURL);
        Transaction[] transactions = given().get(baseURL).then().extract().as(Transaction[].class);
        if (transactions.length > 0) {
            System.out.println("With data");
            testDeleteData(transactions);
            response = given().get(baseURL);
            String respu = response.jsonPath().getString("id");
            Assert.assertEquals("[]", respu);
        } else {
            String respu = response.jsonPath().getString("id");
            Assert.assertEquals("[]", respu);
            System.out.println("Without data");
        }
    }

    /**
     * Test to execute the population of the data
     *
     * @author Ella Gerstner
     */
    @Description(value = "Populate data ID:0002")
    @Test(priority = 1)
    public void createdata() throws Exception {
        CreateDataTransaction crete = new CreateDataTransaction();
        Transaction[] data = crete.createTransaction();
        populateData(data);
        Transaction[] transactions = given().get(baseURL).then().extract().as(Transaction[].class);
        Assert.assertEquals(data.length, transactions.length);
    }

    /**
     * Class validate if the email that the user wants to use is repetead or not
     *
     * @author Ella Gerstner
     */
    @Description(value = "Create a transaction ID:0003")
    @Test(dataProvider = "EmailRepeat", priority = 2)
    public void updateEmailRepeated(String correo) throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        map.put("email", correo);
        response = given().get(baseURL);
        Transaction[] transactions = given().get(baseURL).then().extract().as(Transaction[].class);
        Boolean answer = testGetEmails(transactions, correo);
        if (answer) {
            JSONObject request = createTsansaction(correo);
            given().contentType("application/json").body(request).when().post(baseURL).then().statusCode(201);
        } else {
            System.out.println("The email had been  used by another transaction, it is not posible create a transaction");
        }
    }

    /**
     * Class to update the account number of one transaction
     *
     * @author Ella Gerstner
     */
    @Description(value = "Update a transaction ID:0004")
    @Test(dataProvider = "updateAccount", priority = 2)
    public void updateAccountNumber(String account) {
        Transaction[] transactions = given().contentType("application/json").when().get(baseURL).then().extract().as(Transaction[].class);
        int intAletorio = position(transactions);
        if (intAletorio == 0) {
            System.out.println("Not exist information to update");
        } else {
            JSONObject param = new JSONObject();
            param.put("account_numer", account);
            given().contentType("application/json").when().body(param.toJSONString()).put(baseURL + "/" + transactions[intAletorio].getId()).then().statusCode(200);
            response = given().get(baseURL + "/" + transactions[intAletorio].getId());
            String numberA = response.jsonPath().getString("account_numer");
            Assert.assertEquals(account, numberA);
        }
    }
}

