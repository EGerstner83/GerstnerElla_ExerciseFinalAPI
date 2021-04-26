package academyapi.controlador;

import java.io.Serializable;

public class Transaction implements Serializable{

    private String id;
    private String first_name;
    private String last_name;
    private String account_numer;
    private String amount;
    private String transaction_type;
    private String email;
    private Boolean status;
    private String country;
    private String telephone;

    /**
     * No args constructor for use in serialization
     *
     */
    public Transaction() {
    }

    /**
     *
     * @param transaction_type
     * @param first_name
     * @param last_name
     * @param country
     * @param amount
     * @param account_numer
     * @param telephone
     * @param id
     * @param email
     * @param status
     */
     public Transaction(String id, String first_name, String last_name, String account_numer, String amount, String transaction_type, String email, Boolean status, String country, String telephone) {
        super();
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.account_numer = account_numer;
        this.amount = amount;
        this.transaction_type = transaction_type;
        this.email = email;
        this.status = status;
        this.country = country;
        this.telephone = telephone;
    }
    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAccount_numer() {
        return account_numer;
    }

    public void setAccount_numer(String account_numer) {
        this.account_numer = account_numer;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }


    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Boolean getStatus() {
        return status;
    }


    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getTelephone() {
        return telephone;
    }


    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
