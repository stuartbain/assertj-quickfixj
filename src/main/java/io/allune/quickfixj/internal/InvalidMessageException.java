package io.allune.quickfixj.internal;

/**
 * @author Eduardo Sanchez-Ros
 */
public class InvalidMessageException extends RuntimeException {

	public InvalidMessageException(String message, Exception exception) {
		super(message, exception);
	}
}
