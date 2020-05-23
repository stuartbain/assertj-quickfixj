/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2020-2020 the original author or authors.
 */
package io.allune.quickfixj.internal;

import static io.allune.quickfixj.error.FieldShouldHaveValue.fieldShouldHaveValue;
import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;
import static quickfix.MessageUtils.getMessageType;

import java.time.LocalDateTime;
import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import quickfix.DataDictionary;
import quickfix.DefaultDataDictionaryProvider;
import quickfix.DefaultMessageFactory;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.InvalidMessage;
import quickfix.Message;
import quickfix.field.ApplVerID;
import quickfix.field.BeginString;
import quickfix.field.MsgType;
import quickfix.field.TransactTime;

/**
 * @author Eduardo Sanchez-Ros
 */
public class Messages {

	private static final Messages INSTANCE = new Messages();

	public static Messages instance() {
		return INSTANCE;
	}

	Messages() {
	}

	Objects objects = Objects.instance();

	Failures failures = Failures.instance();

	private static DefaultDataDictionaryProvider dataDictionaryProvider = new DefaultDataDictionaryProvider();

	private static DefaultMessageFactory messageFactory = new DefaultMessageFactory();

	public static DataDictionary getSessionDataDictionary(String beginString) {
		return dataDictionaryProvider.getSessionDataDictionary(beginString);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Message> T createFromMessage(String beginString, String msgType, String messageData) {
		Message message = messageFactory.create(beginString, msgType);
		DataDictionary dataDictionary = dataDictionaryProvider.getSessionDataDictionary(beginString);

		try {
			message.fromString(messageData, dataDictionary, true);
			dataDictionary.validate(message);
		} catch (InvalidMessage | FieldNotFound | IncorrectTagValue | IncorrectDataFormat e) {
			throw new InvalidMessageException(e.getMessage(), e);
		}
		return (T) message;
	}

	public void assertSameMessageType(AssertionInfo info, Message actual, String expected) {
		try {
			// TODO: Custom error message if not equals
			objects.assertEqual(info, getMessageType(actual.toString()), expected);
		} catch (InvalidMessage invalidMessage) {
			throw failures.failure(info, shouldHaveField(MsgType.class, MsgType.FIELD));
		}
	}

	public void assertHasStringFieldValue(AssertionInfo info, Message actual, int actualField, Class<?> actualFieldClass, String expectedValue) {
		try {
			String actualValue = actual.getString(actualField);
			if (!actualValue.equals(expectedValue)) {
				throw failures.failure(info, fieldShouldHaveValue(actualFieldClass, actualField, actualValue, expectedValue));
			}
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(actualFieldClass, actualField));
		}
	}

	public void assertHasCharFieldValue(AssertionInfo info, Message actual, int actualField, Class<?> actualFieldClass, Character expectedValue) {
		try {
			Character actualValue = actual.getChar(actualField);
			if (!actualValue.equals(expectedValue)) {
				throw failures.failure(info, fieldShouldHaveValue(actualFieldClass, actualField, actualValue, expectedValue));
			}
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(actualFieldClass, actualField));
		}
	}

	public void assertHasDoubleFieldValue(AssertionInfo info, Message actual, int actualField, Class<?> actualFieldClass, Double expectedValue) {
		try {
			Double actualValue = actual.getDouble(actualField);
			if (!actualValue.equals(expectedValue)) {
				throw failures.failure(info, fieldShouldHaveValue(actualFieldClass, actualField, actualValue, expectedValue));
			}
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(actualFieldClass, actualField));
		}
	}

	public void assertHasUtcTimeStampFieldValue(AssertionInfo info, Message actual, int actualField, Class<?> actualFieldClass, LocalDateTime expectedValue) {
		try {
			LocalDateTime actualValue = actual.getUtcTimeStamp(TransactTime.FIELD);
			if (!actualValue.equals(expectedValue)) {
				throw failures.failure(info, fieldShouldHaveValue(actualFieldClass, actualField, actualValue, expectedValue));
			}
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(actualFieldClass, actualField));
		}
	}

	public String getBeginString(AssertionInfo info, Message message) {
		return getHeaderFieldValue(info, message, BeginString.class, BeginString.FIELD);
	}

	public String getApplVerId(AssertionInfo info, Message message) {
		return getHeaderFieldValue(info, message, ApplVerID.class, ApplVerID.FIELD);
	}

	private String getHeaderFieldValue(AssertionInfo info, Message message, Class<?> actualClazz, int actualField) {
		try {
			return message.getHeader().getString(actualField);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(actualClazz, actualField));
		}
	}

	//	public <T> void assertMessageHasFieldWithValue(AssertionInfo info, Message actual, Class<?> clazz, int expectedField, T expectedValue) {
	//		try {
	//			T actualValue = getFieldValue(actual, expectedField);
	//			if (!actualValue.equals(expectedField)) {
	//				throw failures.failure(info, fieldShouldHaveValue(clazz, expectedField, actualValue, expectedValue));
	//			}
	//		} catch (FieldNotFound fieldNotFound) {
	//			throw failures.failure(info, shouldHaveField(ClOrdID.class, expectedField));
	//		}
	//	}
	//
	//	private <T> T getFieldValue(Message actual, int expectedField) throws FieldNotFound {
	//		return actual.getString(expectedField);
	//	}
}
