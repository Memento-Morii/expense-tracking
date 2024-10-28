package com.expense.expense_tracking.src.app.common;

import com.expense.expense_tracking.src.app.common.enums.ApiErrorCode;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public class CustomResponse {

    private List<ApiErrorCode> errors = new ArrayList<>();
    private RESULT result;

    public CustomResponse() {
    }

    public CustomResponse(ResponseBuilder builder) {
        this.errors = ImmutableList.copyOf(builder.errors);
        this.result = hasError() ? RESULT.FAILURE : RESULT.SUCCESS;
    }

    public static ResponseBuilder newBuilder() {
        return new ResponseBuilder();
    }

    public List<ApiErrorCode> getErrors() {
        return this.errors;
    }

    public RESULT getResult() {
        return this.result;
    }

    public boolean hasError() {
        return this.errors.size() > 0;
    }

    public List<Integer> getErrorCodes() {
        List<Integer> errorCodes = new ArrayList<Integer>();
        if (this.hasError()) {
            for (int i = 0; i < this.getErrors().size(); i++) {
                errorCodes.add(this.getErrors().get(i).getCode());
            }
        }
        return errorCodes;
    }

    public enum RESULT {
        FAILURE, SUCCESS;
    }

    public static class ResponseBuilder {

        private List<ApiErrorCode> errors = new ArrayList<>();

        public ResponseBuilder withAddError(ApiErrorCode errorCode) {
            this.errors.add(errorCode);
            return this;
        }

        public ResponseBuilder withErrorList(List<ApiErrorCode> errorList) {
            if (errorList != null) {
                this.errors = new ArrayList<>(errorList);
            }
            return this;
        }

        public boolean hasError() {
            return this.errors.size() > 0;
        }

        public CustomResponse build() {
            return new CustomResponse(this);
        }
    }
}