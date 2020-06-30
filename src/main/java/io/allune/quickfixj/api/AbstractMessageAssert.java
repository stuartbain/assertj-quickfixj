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

import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;
import static quickfix.FixVersions.BEGINSTRING_FIXT11;
import static quickfix.MessageUtils.toBeginString;

import java.util.Iterator;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import io.allune.quickfixj.internal.Messages;
import io.allune.quickfixj.internal.Versions;
import quickfix.Field;
import quickfix.Message;
import quickfix.StringField;
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

	public SELF hasFieldValue(int expectedFieldTag, Object expectedFieldValue) {
		isNotNull();

		Iterator<Field<?>> iterator = actual.iterator();
		while (iterator.hasNext()) {
			final StringField actualField = (StringField) iterator.next();
			if (expectedFieldTag != actualField.getTag())
				continue;

			// field found, check value matches expected
			messages.assertFieldHasValue(info, actual, actualField, expectedFieldValue);
			//			if (!actualField.getValue().equals(expectedFieldValue))
			//				throw failures.failure(info, fieldShouldHaveValue(actual, actualField.getTag(), actualField.getValue(), expectedFieldValue));
			break;
		}

		return (SELF) this;
	}

	@Override
	public SELF isEqualTo(Object expected) {
		// TODO
		return (SELF) this;
	}

	protected String getBeginString() {
		return beginString;
	}
}
