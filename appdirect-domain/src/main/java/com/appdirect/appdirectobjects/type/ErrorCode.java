package com.appdirect.appdirectobjects.type;

public enum ErrorCode {

	USER_ALREADY_EXISTS,  ACCOUNT_NOT_FOUND, OPERATION_CANCELLED, CONFIGURATION_ERROR,UNKNOWN_ERROR;

	public static ErrorCode fromString(String string) {
		for (ErrorCode errorCode : values()) {
			if (errorCode.toString().equals(string)) {
				return errorCode;
			}
		}
		return null;
	}
}
