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
package io.allune.quickfixj.api;

import static io.allune.quickfixj.error.FieldShouldHaveValue.fieldShouldHaveValue;
import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;
import static org.assertj.core.util.Preconditions.checkArgument;
import static quickfix.FixVersions.BEGINSTRING_FIXT11;
import static quickfix.MessageUtils.toBeginString;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import io.allune.quickfixj.internal.Messages;
import io.allune.quickfixj.internal.Versions;
import quickfix.DataDictionary;
import quickfix.DefaultDataDictionaryProvider;
import quickfix.FieldNotFound;
import quickfix.FieldType;
import quickfix.FixVersions;
import quickfix.IncorrectDataFormat;
import quickfix.Message;
import quickfix.field.ApplVerID;

/**
 * @author Eduardo Sanchez-Ros
 */
public abstract class AbstractMessageAssert<SELF extends AbstractMessageAssert<SELF, ACTUAL>, ACTUAL extends Message>
		extends AbstractAssert<SELF, ACTUAL> {

	private final String beginString;

	protected Objects objects = Objects.instance();

	protected Failures failures = Failures.instance();

	protected Messages messages = Messages.instance();

	protected Versions versions = Versions.instance();

	/**
	 * Creates a new <code>{@link AbstractMessageAssert}</code>.
	 *
	 * @param selfType the "self type"
	 * @param actual   the actual value to verify
	 */
	protected AbstractMessageAssert(Class<SELF> selfType, ACTUAL actual) {
		super(actual, selfType);
		this.beginString = determineMessageVersion(actual);
	}

	private String determineMessageVersion(ACTUAL actual) {
		isNotNull();
		String beginString = messages.getBeginString(info, actual);

		if (beginString.equals(BEGINSTRING_FIXT11)) {
			String applVerId = messages.getApplVerId(info, actual);
			return toBeginString(new ApplVerID(applVerId));
		} else {
			return beginString;
		}
	}

	public SELF hasHeaderField(int expectedFieldTag) {
		isNotNull();
		if (actual.getHeader() == null || !actual.getHeader().isSetField(expectedFieldTag))
			throw failures.failure(info, shouldHaveField(actual, expectedFieldTag));
		return (SELF) this;
	}

	public SELF hasBodyField(int expectedTag) {
		isNotNull();
		if (!actual.isSetField(expectedTag))
			throw failures.failure(info, shouldHaveField(actual, expectedTag));
		return (SELF) this;
	}

	public SELF hasBodyFields(int... expectedFieldTags) {
		// TODO: Iterate through all fields, gather the errors and custom error message
		isNotNull();
		for (int field : expectedFieldTags) {
			hasBodyField(field);
		}
		return (SELF) this;
	}

	public SELF hasTrailerField(int expectedFieldTag) {
		isNotNull();
		if (actual.getTrailer() == null || !actual.getTrailer().isSetField(expectedFieldTag))
			throw failures.failure(info, shouldHaveField(actual, expectedFieldTag));
		return (SELF) this;
	}

	public SELF hasField(int expectedFieldTag) {
		isNotNull();
		if (!actual.isSetField(expectedFieldTag) &&
				(actual.getHeader() == null || !actual.getHeader().isSetField(expectedFieldTag)) &&
				(actual.getTrailer() == null || !actual.getTrailer().isSetField(expectedFieldTag))) {
			throw failures.failure(info, shouldHaveField(actual, expectedFieldTag));
		}

		return (SELF) this;
	}

	public SELF hasFields(int... expectedFieldTags) {
		// TODO: Iterate through all fields, gather the errors and custom error message
		isNotNull();
		for (int field : expectedFieldTags) {
			hasField(field);
		}
		return (SELF) this;
	}

	// TODO: Move to Messages or create DataDictionaries class, initialise with default and add custom one from `usingDataDictionary`
	private static final DefaultDataDictionaryProvider dataDictionaryProvider = new DefaultDataDictionaryProvider();

	// TODO: Rename to containsField(fieldTag, expectedValue)
	public SELF hasFieldValue(int expectedFieldTag, Object expectedFieldValue) {
		isNotNull();
		//		    Objects.instance().assertNotNull(info, actual);
		checkArgument(expectedFieldTag > 0, "The rows to look for should not be empty.");
		checkArgument(expectedFieldValue != null, "The rows to look for should not be null.");

		hasField(expectedFieldTag);

		try {
			Object actualFieldValue = getFieldValue(expectedFieldTag);
			if (!expectedFieldValue.equals(actualFieldValue))
				throw failures.failure(info, fieldShouldHaveValue(actual, expectedFieldTag, actualFieldValue, expectedFieldValue));
		} catch (Exception ex) {

		}

		return (SELF) this;
	}

	private Object getFieldValue(int fieldTag)
			throws IncorrectDataFormat {
		DataDictionary dataDictionary = dataDictionaryProvider.getSessionDataDictionary(beginString);
		//		FieldType fieldType = dataDictionary.getFieldType(expectedFieldTag);
		//		System.out.println("Field type: " + fieldType.toString());
		//
		//		boolean hasFieldValue = dataDictionary.hasFieldValue(expectedFieldTag);
		//		System.out.println("Has field value: " + hasFieldValue);
		//
		//		boolean msgType = dataDictionary.isMsgType("BLAH");
		//		System.out.printf("Is message type: " + msgType);
		//
		//		System.out.println("Version: " + dataDictionary.getVersion());

		FieldType fieldType = dataDictionary.getFieldType(fieldTag);
		if (fieldType == null) {
			return null;
		}

		try {
			switch (fieldType) {
				case STRING:
				case MULTIPLEVALUESTRING:
				case MULTIPLESTRINGVALUE:
				case EXCHANGE:
				case LOCALMKTDATE:
				case DATA:
				case MONTHYEAR:
				case DAYOFMONTH:
				case COUNTRY:
					// String
					return actual.getString(fieldTag);
				case INT:
				case NUMINGROUP:
				case SEQNUM:
				case LENGTH:
					return actual.getInt(fieldTag);
				case PRICE:
				case AMT:
				case QTY:
				case FLOAT:
				case PRICEOFFSET:
				case PERCENTAGE:
					return actual.getDouble(fieldTag);
				case BOOLEAN:
					return actual.getBoolean(fieldTag);
				case UTCDATE:
					return actual.getUtcDateOnly(fieldTag);
				case UTCTIMEONLY:
					return actual.getUtcTimeOnly(fieldTag);
				case UTCTIMESTAMP:
				case TIME:
					return actual.getUtcTimeStamp(fieldTag);
				case CHAR:
					if (beginString.compareTo(FixVersions.BEGINSTRING_FIX41) > 0) {
						return actual.getChar(fieldTag);
					} // otherwise it's a String, for older FIX versions
					return actual.getString(fieldTag);
				default:
					throw new RuntimeException("Unsupported");
			}
		} catch (FieldNotFound e) {
			throw new IncorrectDataFormat(fieldTag, "");
		}
	}

	@Override
	public SELF isEqualTo(Object expected) {
		// TODO
		return (SELF) this;
	}

	public SELF usingDataDictionary(DataDictionary dataDictionary) {
		return (SELF) this;
	}

	protected String getBeginString() {
		return beginString;
	}
}
