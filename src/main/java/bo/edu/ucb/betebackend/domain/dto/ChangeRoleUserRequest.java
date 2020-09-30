package bo.edu.ucb.betebackend.domain.dto;

public class ChangeRoleUserRequest {
    private Integer typeOf;
    private String payment;

    public ChangeRoleUserRequest(Integer typeOf, String payment) {
        this.typeOf = typeOf;
        this.payment = payment;
    }

    public Integer getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(Integer typeOf) {
        this.typeOf = typeOf;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
