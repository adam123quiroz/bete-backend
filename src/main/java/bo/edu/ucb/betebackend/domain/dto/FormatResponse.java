package bo.edu.ucb.betebackend.domain.dto;

public class FormatResponse<T> {
    private String error;
    private T response;

    public FormatResponse(String error, T response) {
        this.error = error;
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
