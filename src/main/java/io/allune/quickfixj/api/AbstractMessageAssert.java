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
import static quickfix.MessageUtils.getMessageType;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import quickfix.Field;
import quickfix.InvalidMessage;
import quickfix.Message;
import quickfix.field.MsgType;

/**
 * @author Eduardo Sanchez-Ros
 */
@SuppressWarnings("unchecked")
public class AbstractMessageAssert<SELF extends AbstractMessageAssert<SELF, ACTUAL>, ACTUAL extends Message> extends AbstractAssert<SELF, ACTUAL> {

	protected Objects objects = Objects.instance();

	protected Failures failures = Failures.instance();

	/**
	 * Creates a new <code>{@link AbstractMessageAssert}</code>.
	 *
	 * @param actual the actual value to verify
	 */
	protected AbstractMessageAssert(ACTUAL actual) {
		super(actual, AbstractMessageAssert.class);
	}

	/**
	 * Creates a new <code>{@link AbstractMessageAssert}</code>.
	 *
	 * @param selfType the "self type"
	 * @param actual   the actual value to verify
	 */
	protected AbstractMessageAssert(Class<SELF> selfType, ACTUAL actual) {
		super(actual, selfType);
	}

	public QuickfixVersionAssert hasVersion() {
		return new QuickfixVersionAssert(QuickfixVersionAssert.class, actual);
	}

	public QuickfixVersionAssert hasVersion(String version) {
		return new QuickfixVersionAssert(QuickfixVersionAssert.class, actual, version);
	}

	public SELF hasBodyLength(int expected) {
		isNotNull();
		objects.assertEqual(info, actual.bodyLength(), expected);
		return (SELF) this;
	}

	public SELF containsField(int expected) {
		isNotNull();
		objects.assertEqual(info, actual.isSetField(expected), true);
		return (SELF) this;
	}

	public SELF containsField(Field<?> expected) {
		return containsField(expected.getField());
	}

	//	public <T> SELF hasFieldWithValue(int field, T value) {
	//		hasField(field);
	//
	//		actual.getField()
	//	}
	//
	//	public SELF hasFieldWithValue(Field<?> field, Object value) {
	//
	//	}

	public SELF isMessageType(String msgType) {
		assertSameMessageType(msgType);
		return (SELF) this;
	}

	public void assertSameMessageType(String msgType) {
		try {
			objects.assertEqual(info, msgType, getMessageType(actual.toString()));
		} catch (InvalidMessage invalidMessage) {
			throw failures.failure(info, shouldHaveField(MsgType.class, MsgType.FIELD));
		}
	}

	@Override
	public SELF isEqualTo(Object expected) {
		// TODO
		objects.assertEqual(info, actual, expected);
		return (SELF) this;
	}
}
