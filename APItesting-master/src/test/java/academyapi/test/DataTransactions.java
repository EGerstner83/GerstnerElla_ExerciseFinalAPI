package academyapi.test;

import academyapi.controlador.CreateDataTransaction;
import academyapi.controlador.Transaction;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

/**
 * Class where are the data that will be used as parameters or methods invoqued by FirstTest.java
 *
 * @author Ella Gerstner
 */
public class DataTransactions {

    private static String content = "application/json";
    private static String baseURL = "https://608432f89b2bed0017040ccc.mockapi.io/BankTransactions";
    private static Response response;
    Faker faker = new Faker();
    CreateDataTransaction creation = new CreateDataTransaction();

    /**
     * Class to return the account number that will be changed in the case 4
     *
     * @return Object[]
     * @author Ella Gerstner
     */
    @DataProvider(name = "updateAccount")
    public Object[] numberAccount() {
        Object[] data = new Object[1];
        data[0] = "0870450762";
        return data;
    }

    /**
     * Class to return the email that will be compared with the data existing
     *
     * @return Object[]
     * @author Ella Gerstner
     */
    @DataProvider(name = "EmailRepeat")
    public Object[] emailRepeat() {
        Object[] data = new Object[1];
        data[0] = "api5@automatationapi.com";
        return data;
    }

    /**
     * Method that delete the data
     *
     * @author Ella Gerstner
     */
    public void testDeleteData(Transaction[] transactions) throws InterruptedException {
        for (int a = 0; a < transactions.length; a++) {
            Thread.sleep(1000);
            given().contentType("application/json").when().delete(baseURL + "/" + transactions[a].getId()).then().statusCode(200);
        }
    }

    /**
     * Class to let it know if the email that the user wants to add exist or not
     *
     * @return boolean
     * @author Ella Gerstner
     */
    public boolean testGetEmails(Transaction[] transactions, String correoE) throws Exception {
        String correo;
        Boolean respue = true;
        for (int a = 0; a < transactions.length; ) {
            Thread.sleep(1000);
            response = given().contentType("application/json").when().get(baseURL + "/" + transactions[a].getId());
            correo = response.jsonPath().getString("email");
            if (correo.equals(correoE)) {
                return false;
            } else {
                a++;
            }
        }
        return respue;
    }

    /**
     * Method to return a int number generated random
     *
     * @return int
     * @author Ella Gerstner
     */
    public int position(Transaction[] transactions) {
        int total = transactions.length;
        Random aleatorio = new Random(System.currentTimeMillis());
        return aleatorio.nextInt(total);
    }

    /**
     * Method to create the data to populate the transactions
     *
     * @author Ella Gerstner
     */
    public void populateData(Transaction[] data) throws InterruptedException {
        Transaction[] datos = data;
        Map<String, Object> map = new HashMap<String, Object>();
        for (int a = 0; a < datos.length; a++) {
            map.put("first_name", data[a].getFirst_name());
            map.put("last_name", data[a].getLast_name());
            map.put("account_numer", data[a].getAccount_numer());
            map.put("amount", data[a].getAmount());
            map.put("transaction_type", data[a].getTransaction_type());
            map.put("email", data[a].getEmail());
            map.put("status", data[a].getStatus());
            map.put("country", data[a].getCountry());
            map.put("telephone", data[a].getTelephone());
            JSONObject request = new JSONObject(map);
            Thread.sleep(1000);
            given().contentType("application/json").body(request).when().post(baseURL).then().statusCode(201);
            Thread.sleep(1000);
            map.clear();
        }
    }

    /**
     * Method to create the data to validate if the email is repeated or not
     *
     * @author Ella Gerstner
     */
    public JSONObject createTsansaction(String email) {
        JSONObject param = new JSONObject();
        param.put("first_name", faker.name().firstName());
        param.put("last_name", faker.name().lastName());
        param.put("account_numer", faker.number().randomNumber(8, true));
        param.put("amount", faker.number().randomDouble(2, 0, 1000));
        param.put("transaction_type", creation.typeTransactionReturn());
        param.put("email", email);
        param.put("status", faker.bool().bool());
        param.put("country", faker.address().country());
        param.put("telephone", faker.phoneNumber().cellPhone());
        return param;
    }
}
