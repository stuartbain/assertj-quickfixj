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

import static quickfix.FixVersions.BEGINSTRING_FIXT11;
import static quickfix.MessageUtils.toBeginString;
import static quickfix.field.MsgType.ORDER_SINGLE;

import java.util.Iterator;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import io.allune.quickfixj.api.newordersingle.AbstractNewOrderSingleAssert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertFactory;
import io.allune.quickfixj.internal.Messages;
import io.allune.quickfixj.internal.Versions;
import quickfix.Field;
import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.StringField;
import quickfix.field.ApplVerID;
import quickfix.field.BeginString;

/**
 * @author Eduardo Sanchez-Ros
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractMessageAssert<SELF extends AbstractMessageAssert<SELF, ACTUAL>, ACTUAL extends Message> extends AbstractAssert<SELF, ACTUAL> {

	private final String beginString;

	protected Objects objects = Objects.instance();

	protected Failures failures = Failures.instance();

	protected Messages messages = Messages.instance();

	protected Versions versions = Versions.instance();

	protected NewOrderSingleAssertFactory assertFactory = new NewOrderSingleAssertFactory();

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
		String beginString = messages.getBeginString(info, actual);

		if (beginString.equals(BEGINSTRING_FIXT11)) {
			String applVerId = messages.getApplVerId(info, actual);
			return toBeginString(new ApplVerID(applVerId));
		} else {
			return beginString;
		}
	}

	public SELF hasBeginString(String expected) {
		// TODO: Custom error message
		isNotNull();
		try {
			objects.assertEqual(info, actual.getString(BeginString.FIELD), expected);
		} catch (FieldNotFound fieldNotFound) {
			fieldNotFound.printStackTrace();
		}
		return (SELF) this;
	}

	public SELF hasBodyLength(int expected) {
		// TODO: Custom error message
		isNotNull();
		objects.assertEqual(info, actual.bodyLength(), expected);
		return (SELF) this;
	}

	public SELF hasMessageType(String msgType) {
		// TODO: Custom error message
		messages.assertSameMessageType(info, actual, msgType);
		return (SELF) this;
	}

	public SELF hasFields(int... fields) {
		// TODO: Custom error message
		for (int field : fields) {
			hasField(field);
		}
		return (SELF) this;
	}

	public SELF hasField(int field) {
		isNotNull();
		// TODO: Custom error message
		objects.assertEqual(info,
				actual.isSetField(field) ||
						actual.getHeader().isSetField(field) ||
						actual.getTrailer().isSetField(field), true);
		return (SELF) this;
	}

	public <T> SELF hasFieldValue(int expectedField, Object expected) {
		hasField(expectedField);
		Iterator<Field<?>> iterator = actual.iterator();
		while (iterator.hasNext()) {
			final StringField actualField = (StringField) iterator.next();
			if (expectedField != actualField.getField())
				continue;

			// TODO: Custom error message
			objects.assertEqual(info, actualField.getValue(), expected);
		}

		return (SELF) this;
	}

	@Override
	public SELF isEqualTo(Object expected) {
		// TODO
		return (SELF) this;
	}

	public AbstractNewOrderSingleAssert isNewOrderSingle() {
		messages.assertSameMessageType(info, actual, ORDER_SINGLE);
		return assertFactory.newOrderSingleAssertFromFixVersion(info, getBeginString(), actual.toRawString());
	}

	protected String getBeginString() {
		return beginString;
	}
}
