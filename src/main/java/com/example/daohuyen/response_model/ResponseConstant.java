package com.example.daohuyen.response_model;

public class ResponseConstant {
    public static final String VI = "vi";
    public static final String EN = "en";

    public static final String MSG_OK = "Ok";

    public static class Vi {
        public static final String WRONG_EMAIL_OR_PASSWORD = "Sai Email hoặc mật khẩu";
        public static final String UNEXPECTED_ERROR = "Có lỗi xảy ra, vui lòng thử lại";
        public static final String EMAIL_EXIST = "Email đã tồn tại";
        public static final String INVALID_NAME = "Họ và tên không được rỗng hoặc null";
        public static final String NOTIFICATION_TITLE = "Thông báo nhắc việc";
        public static final String NOTIFICATION_CONTENT = "việc làm phù hợp với bạn";
        public static final String OLD_PASSWORD_MISMATCH = "Mật khẩu cũ không khớp";


    }



    public static class ErrorMessage {
        public static final String NOT_FOUND = "not found";
        public static final String EMPTY_BODY = "empty body";
        public static final String EXIST = "exist";
        public static final String INVALID_REQUEST_BODY = "invalid request body";
        public static final String NO_CONTENT = "no content";
        public static final String INVALID_FIELDS = "invalid pairFields";
        public static final String RESOURCE_EXIST = "resource exist";
        public static final String INVALID_EMAIL = "invalid email";
        public static final String ACCOUNT_NOT_VERIFIED = "account has't been verified";
        public static final String ACCOUNT_VERIFED = "account has been verified";
        public static final String DELETE_EMPLOYER_HEAD_QUARTER_BRANCH_ERROR = "cannot delete, this branch is headquarter";
        public static final String DELETE_EMPLOYER_BRANCH_ERROR = "cannot delete, some jobs are referencing to this branch";
        public static final String EMPLOYER_REJECTED = "employer rejected";
        public static final String EMPLOYER_ACCEPTED = "employer accepted";
        public static final String INTERNAL_SERVER_ERROR = "internal server error";
        public static final String JOB_NOT_EXPIRED = "job haven't expired yet";
        public static final String CANDIDATE_APPLIED_JOB = "candidate applied job";
        public static final String JOB_APPLIED = "job applied";
        public static final String JOB_EXPIRED = "job expired";
        public static final String JOB_DISABLED = "job disabled";
        public static final String PASSWORD_TOO_SHORT = "Password need to be at lease 6 character length";
    }
}