package com.base.api;

import com.base.R;

/**
 * Created by shayan4shayan on 3/15/18.
 */

public class ErrorHandler {
    public static int getErrorStirng(int code) {
        switch (code) {
            case 0:
                return R.string.no_error;
            case 1:
                return R.string.error_parameter_wrong;
            case 2:
                return R.string.error_json_format;
            case 3:
                return R.string.error_phone_not_found;
            case 4:
                return R.string.error_server;
            case 5:
                return R.string.error_authentication;
            case 6:
                return R.string.error_code_invalid;
            case 7:
                return R.string.error_server;
            case 8:
                return R.string.error_authentication;
            case 9:
                return R.string.error_user_access;
            case 10:
                return R.string.error_user_access;
            case 11:
                return R.string.error_status;
            case 12:
                return R.string.error_retry_code;
            case 13:
                return R.string.error_application;
            case 14:
                return R.string.error_application;
            case 15:
                return R.string.error_repeative_load;
            case 16:
                return R.string.error_balance;
            case 17:
                return R.string.error_charge;
            default:
                return R.string.error_unknown;
        }
    }
}
