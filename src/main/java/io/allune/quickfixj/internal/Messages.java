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

import static io.allune.quickfixj.error.FieldShouldHaveTag.fieldShouldHaveTag;
import static io.allune.quickfixj.error.FieldShouldHaveValue.fieldShouldHaveValue;
import static io.allune.quickfixj.error.ShouldBeOfType.shouldBeOfType;
import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;
import static quickfix.MessageUtils.getMessageType;

import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import io.allune.quickfixj.exception.ThrownByLambdaException;
import quickfix.BooleanField;
import quickfix.CharField;
import quickfix.DataDictionary;
import quickfix.DecimalField;
import quickfix.DefaultDataDictionaryProvider;
import quickfix.DoubleField;
import quickfix.Field;
import quickfix.FieldMap;
import quickfix.FieldNotFound;
import quickfix.IntField;
import quickfix.InvalidMessage;
import quickfix.Message;
import quickfix.StringField;
import quickfix.UtcDateOnlyField;
import quickfix.UtcTimeOnlyField;
import quickfix.UtcTimeStampField;
import quickfix.field.ApplVerID;
import quickfix.field.BeginString;
import quickfix.field.MsgType;

/**
 * @author Eduardo Sanchez-Ros
 */
public class Messages {

	private static final Messages INSTANCE = new Messages();

	private static final String FIELD_TAG_FIELD_NAME = "FIELD";

	private static final DefaultDataDictionaryProvider dataDictionaryProvider = new DefaultDataDictionaryProvider();

	//	private static final DefaultMessageFactory messageFactory = new DefaultMessageFactory();

	private static final Map<Class<? extends Field<?>>, ThrowingBiFunction<Message, Integer, Object>> quickfixClassToObject =
			new HashMap<Class<? extends Field<?>>, ThrowingBiFunction<Message, Integer, Object>>() {{

				put(StringField.class, FieldMap::getString);
				put(BooleanField.class, FieldMap::getBoolean);
				put(CharField.class, FieldMap::getChar);
				put(IntField.class, FieldMap::getInt);
				put(DoubleField.class, FieldMap::getDouble);
				put(DecimalField.class, FieldMap::getDecimal);
				put(UtcTimeStampField.class, FieldMap::getUtcTimeStamp);
				put(UtcTimeOnlyField.class, FieldMap::getUtcTimeOnly);
				put(UtcDateOnlyField.class, FieldMap::getUtcDateOnly);
			}};

	public static Messages instance() {
		return INSTANCE;
	}

	Objects objects = Objects.instance();

	Failures failures = Failures.instance();

	Messages() {
	}

	public static DataDictionary getSessionDataDictionary(String beginString) {
		return dataDictionaryProvider.getSessionDataDictionary(beginString);
	}

	//	@SuppressWarnings("unchecked")
	//	public static <T extends Message> T createFromMessage(String beginString, String msgType, String messageData) {
	//		Message message = messageFactory.create(beginString, msgType);
	//		DataDictionary dataDictionary = dataDictionaryProvider.getSessionDataDictionary(beginString);
	//
	//		try {
	//			message.fromString(messageData, dataDictionary, true);
	//			dataDictionary.validate(message);
	//		} catch (InvalidMessage | FieldNotFound | IncorrectTagValue | IncorrectDataFormat e) {
	//			throw new InvalidMessageException(e.getMessage(), e);
	//		}
	//		return (T) message;
	//	}

	public void assertMessageIsOfType(AssertionInfo info, Message actual, String expectedMessageType) {
		objects.assertNotNull(info, actual);

		try {
			String actualMessageType = getMessageType(actual.toString());
			if (!actualMessageType.equals(expectedMessageType)) {
				throw failures.failure(info, shouldBeOfType(actual, actualMessageType, expectedMessageType));
			}
		} catch (InvalidMessage invalidMessage) {
			throw failures.failure(info, shouldHaveField(actual, MsgType.FIELD));
		}
	}

	public <T> void assertFieldHasValue(AssertionInfo info, Message actual, Class<? extends Field<T>> actualFieldClass, T expectedFieldValue) {
		objects.assertNotNull(info, actual);

		int actualFieldTag = getFieldTagFromFieldClass(info, actualFieldClass);
		Object actualFieldValue = getActualValue(info, actual, actualFieldClass, actualFieldTag);
		if (!actualFieldValue.equals(expectedFieldValue)) {
			throw failures.failure(info, fieldShouldHaveValue(actual, actualFieldClass, actualFieldTag, actualFieldValue, expectedFieldValue));
		}
	}

	public <T> void assertFieldHasValue(AssertionInfo info, Message actual, Field<T> actualField, Object expectedFieldValue) {
		if (!actualField.getObject().equals(expectedFieldValue))
			throw failures.failure(info, fieldShouldHaveValue(actual, actualField.getTag(), actualField.getObject(), expectedFieldValue));
	}

	private int getFieldTagFromFieldClass(AssertionInfo info, Class<? extends Field<?>> actualFieldClass) {
		try {
			return actualFieldClass.getField(FIELD_TAG_FIELD_NAME).getInt(null);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw failures.failure(info, fieldShouldHaveTag(actualFieldClass));
		}
	}

	private Object getActualValue(AssertionInfo info, Message actual, Class<? extends Field<?>> actualFieldClass, int actualFieldTag) {
		ThrowingBiFunction<Message, Integer, Object> fieldValueFunction = quickfixClassToObject.get(actualFieldClass.getSuperclass());
		if (fieldValueFunction == null) {
			// TODO: Field type not supported
			throw failures.failure(info, fieldShouldHaveTag(actualFieldClass));
		}

		try {
			return fieldValueFunction.apply(actual, actualFieldTag);
		} catch (ThrownByLambdaException e) {
			if (e.getCause().getClass().equals(FieldNotFound.class)) {
				throw failures.failure(info, shouldHaveField(actualFieldClass, actualFieldTag));
			} else {
				// TODO: other exception throw failure
				throw failures.failure(info, shouldHaveField(actualFieldClass, actualFieldTag));
			}
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
}
