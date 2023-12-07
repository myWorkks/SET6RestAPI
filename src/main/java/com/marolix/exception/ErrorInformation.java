package com.marolix.exception;

import java.time.LocalDateTime;

public class ErrorInformation {
	private String errorMessage;
	private Integer satusCode;
	private LocalDateTime occuredAt;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getSatusCode() {
		return satusCode;
	}
	public void setSatusCode(Integer satusCode) {
		this.satusCode = satusCode;
	}
	public LocalDateTime getOccuredAt() {
		return occuredAt;
	}
	public void setOccuredAt(LocalDateTime occuredAt) {
		this.occuredAt = occuredAt;
	}
	
	
}
