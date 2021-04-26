package academyapi.controlador;

import com.github.javafaker.Faker;

/**
 * Class to populate the data base
 *
 * @author Ella Gerstner
 *
 */
public class CreateDataTransaction {
    Faker faker = new Faker();
    /**
     * Method to create random data and retunr a Transaction array
     *
     * @author Ella Gerstner
     * @return Transaction[]
     */
    public Transaction[] createTransaction() {
        Transaction[] fullingData = new Transaction[20];
        for (int i = 0; i < 20; i++) {
            Transaction transaction = new Transaction("0",
                    (faker.name().firstName()),
                    (faker.name().lastName()),
                    "" + (faker.number().randomNumber(8, true)),
                    "" + (faker.number().randomDouble(2, 0, 1000)),
                    typeTransactionReturn(),
                    "api" + i + "@automatationapi.com",
                    (faker.bool().bool()),
                    (faker.address().country()),
                    "" + (faker.phoneNumber().cellPhone()));
            fullingData[i] = transaction;
        }
        return fullingData;
    }
    /**
     * Method to create the type transaction random, return a String that is the type transaction
     *
     * @author Ella Gerstner
     * @return Stting
     */
    public String typeTransactionReturn() {
        String[] tipo = {"invoice", "withdrawal", "deposit", "payment"};
        int intAletorio = faker.number().numberBetween(1, 4);
        return tipo[intAletorio - 1];
    }
}
