package bo.edu.ucb.betebackend.domain.dto.response;

public class FormatResponse<T> {
    private String error;
    private T response;

    public FormatResponse(T response) {
        this.response = response;
    }

    public FormatResponse(String error) {
        this.error = error;
    }

    public FormatResponse(String error, T response) {
        this.error = error;
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public FormatResponse<T> setError(String error) {
        this.error = error;
        return this;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
