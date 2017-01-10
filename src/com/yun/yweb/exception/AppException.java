package com.yun.yweb.exception;

public class AppException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1166837819870856728L;
	protected Throwable throwable;
	protected String code;

	/**
	 * Method 'AppException'
	 * 
	 * @param message
	 */
	public AppException(String code,String message)
	{
		super(message);
		this.code=code;
	}

	/**
	 * Method 'AppException'
	 * 
	 * @param message
	 * @param throwable
	 */
	public AppException(String code,String message, Throwable throwable)
	{
		super(message);
		this.throwable = throwable;
		this.code=code;
	}

	/**
	 * Method 'getCause'
	 * 
	 * @return Throwable
	 */
	public Throwable getCause()
	{
		return throwable;
	}

	public String getCode() {
		return code;
	}
}
