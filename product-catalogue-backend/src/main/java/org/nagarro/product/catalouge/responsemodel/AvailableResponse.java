package org.nagarro.product.catalouge.responsemodel;

public class AvailableResponse {

    public boolean result;

    public AvailableResponse(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
