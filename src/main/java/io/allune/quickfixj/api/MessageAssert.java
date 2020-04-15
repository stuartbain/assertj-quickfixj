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

import static io.allune.quickfixj.error.ShouldHaveFixVersionEqualTo.shouldHaveFixVersionEqualTo;
import static io.allune.quickfixj.error.ShouldHaveHeaderField.shouldHaveHeaderFieldEqualTo;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.field.BeginString;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssert extends AbstractAssert<MessageAssert, Message> {

	private Objects objects = Objects.instance();

	private Failures failures = Failures.instance();

	/**
	 * Creates a new <code>{@link io.allune.quickfixj.api.MessageAssert}</code>.
	 *
	 * @param selfType the "self type"
	 * @param actual   the actual value to verify
	 */
	protected MessageAssert(Class<MessageAssert> selfType, Message actual) {
		super(actual, selfType);
	}

	@Override
	public MessageAssert isEqualTo(Object expected) {
		// TODO
		objects.assertEqual(info, actual, expected);
		return this;
	}

	public MessageAssert isVersionFix40() {
		isNotNull();
		String beginStringValue = getBeginString();
		if (!BEGINSTRING_FIX40.equals(beginStringValue)) {
			throw failures.failure(info, shouldHaveFixVersionEqualTo(actual, beginStringValue, BEGINSTRING_FIX40));
		}
		return this;
	}

	public MessageAssert isFixVersion41() {
		isNotNull();
		String beginStringValue = getBeginString();
		if (!BEGINSTRING_FIX41.equals(beginStringValue)) {
			throw failures.failure(info, shouldHaveFixVersionEqualTo(actual, beginStringValue, BEGINSTRING_FIX41));
		}
		return this;
	}

	public MessageAssert isFixVersion42() {
		isNotNull();
		String beginStringValue = getBeginString();
		if (!BEGINSTRING_FIX42.equals(beginStringValue)) {
			throw failures.failure(info, shouldHaveFixVersionEqualTo(actual, beginStringValue, BEGINSTRING_FIX42));
		}
		return this;
	}

	public MessageAssert hasBodyLength(int expected) {
		objects.assertEqual(info, actual.bodyLength(), expected);
		return this;
	}

	private String getBeginString() {
		try {
			return actual.getHeader().getString(BeginString.FIELD);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveHeaderFieldEqualTo(BeginString.class, BeginString.FIELD));
		}
	}
}
