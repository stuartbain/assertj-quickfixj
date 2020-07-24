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

import io.allune.quickfixj.internal.Dictionaries;
import io.allune.quickfixj.internal.Messages;
import io.allune.quickfixj.internal.Versions;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import quickfix.DataDictionary;
import quickfix.FixVersions;
import quickfix.Message;
import quickfix.field.BodyLength;

import static io.allune.quickfixj.error.FieldShouldHaveValue.fieldShouldHaveValue;
import static io.allune.quickfixj.error.ShouldBeAdminMessage.shouldBeAdminMessage;
import static io.allune.quickfixj.error.ShouldBeAppMessage.shouldBeAppMessage;
import static io.allune.quickfixj.error.ShouldBeEmptyMessage.shouldBeEmptyMessage;
import static io.allune.quickfixj.error.ShouldBeEqual.shouldBeEqual;
import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;
import static io.allune.quickfixj.error.ShouldHaveHeader.shouldHaveHeader;
import static io.allune.quickfixj.error.ShouldHaveTrailer.shouldHaveTrailer;
import static org.assertj.core.util.Preconditions.checkArgument;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.FixVersions.BEGINSTRING_FIXT11;
import static quickfix.field.ApplVerID.FIX50;
import static quickfix.field.ApplVerID.FIX50SP1;
import static quickfix.field.ApplVerID.FIX50SP2;
import static quickfix.field.MsgType.HEARTBEAT;
import static quickfix.field.MsgType.ORDER_SINGLE;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssert extends AbstractAssert<MessageAssert, Message> {

	Objects objects = Objects.instance();

	Failures failures = Failures.instance();

	Messages messages = Messages.instance();

	Versions versions = Versions.instance();

	Dictionaries dictionaries = Dictionaries.instance();

	private final String beginString;

	public MessageAssert(Message message) {
		super(message, MessageAssert.class);
		this.beginString = messages.determineBeginString(info, actual);
	}

	protected Message getActual() {
		return actual;
	}

	/**
	 * Verifies that the actual {@link Message}'s FIX version number is {@link FixVersions#BEGINSTRING_FIX40}.
	 * Example:
	 * <pre>
	 * 	<code class='java'>
	 * 		Message message = new Message(&quot;8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001&quot;);
	 * 		assertThat(message).isVersion40();
	 * 	</code>
	 * </pre>
	 *
	 * @return this assertion object.
	 * @throws AssertionError if the actual {@code Message} is {@code null}.
	 * @throws AssertionError if the actual {@code Message}'s FIX version number is not {@link FixVersions#BEGINSTRING_FIX40}.
	 */
	public MessageAssert isVersion40() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX40);
		return this;
	}

	/**
	 * Verifies that the actual {@link Message}'s FIX version number is {@link FixVersions#BEGINSTRING_FIX41}.
	 * Example:
	 * <pre>
	 * 	<code class='java'>
	 * 		Message message = new Message(&quot;8=FIX.4.1\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=018\u0001&quot;);
	 * 		assertThat(message).isVersion41();
	 * 	</code>
	 * </pre>
	 *
	 * @return this assertion object.
	 * @throws AssertionError if the actual {@code Message} is {@code null}.
	 * @throws AssertionError if the actual {@code Message}'s FIX version number is not {@link FixVersions#BEGINSTRING_FIX41}.
	 */
	public MessageAssert isVersion41() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX41);
		return this;
	}

	/**
	 * Verifies that the actual {@link Message}'s FIX version number is {@link FixVersions#BEGINSTRING_FIX42}.
	 * Example:
	 * <pre>
	 * 	<code class='java'>
	 * 		Message message = new Message(&quot;8=FIX.4.2\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=017\u0001&quot;);
	 * 		assertThat(message).isVersion42();
	 * 	</code>
	 * </pre>
	 *
	 * @return this assertion object.
	 * @throws AssertionError if the actual {@code Message} is {@code null}.
	 * @throws AssertionError if the actual {@code Message}'s FIX version number is not {@link FixVersions#BEGINSTRING_FIX42}.
	 */
	public MessageAssert isVersion42() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX42);
		return this;
	}

	/**
	 * Verifies that the actual {@link Message}'s FIX version number is {@link FixVersions#BEGINSTRING_FIX43}.
	 * Example:
	 * <pre>
	 * 	<code class='java'>
	 * 		Message message = new Message(&quot;8=FIX.4.3\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=018\u0001&quot;);
	 * 		assertThat(message).isVersion43();
	 * 	</code>
	 * </pre>
	 *
	 * @return this assertion object.
	 * @throws AssertionError if the actual {@code Message} is {@code null}.
	 * @throws AssertionError if the actual {@code Message}'s FIX version number is not {@link FixVersions#BEGINSTRING_FIX43}.
	 */
	public MessageAssert isVersion43() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX43);
		return this;
	}

	/**
	 * @return this assertion object.
	 */
	public MessageAssert isVersion44() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX44);
		return this;
	}

	/**
	 * @return this assertion object.
	 */
	public MessageAssert isVersion50() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIXT11, FIX50);
		return this;
	}

	/**
	 * @return this assertion object.
	 */
	public MessageAssert isVersion50sp1() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIXT11, FIX50SP1);
		return this;
	}

	/**
	 * @return this assertion object.
	 */
	public MessageAssert isVersion50sp2() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIXT11, FIX50SP2);
		return this;
	}

	@Override
	public MessageAssert isEqualTo(Object expected) {
		// TODO
		return this;
	}

	public MessageHeaderAssert header() {
		isNotNull();
		if (actual.getHeader() == null)
			throw failures.failure(info, shouldHaveHeader(actual));
		return new MessageHeaderAssert(actual.getHeader(), this);
	}

	public MessageTrailerAssert trailer() {
		isNotNull();
		if (actual.getTrailer() == null)
			throw failures.failure(info, shouldHaveTrailer(actual));
		return new MessageTrailerAssert(actual.getTrailer(), this);
	}

	public MessageAssert hasField(int expectedTag) {
		isNotNull();
		if (!actual.isSetField(expectedTag))
			throw failures.failure(info, shouldHaveField(actual, expectedTag));
		return this;
	}

	public MessageAssert hasFields(int... expectedFieldTags) {
		// TODO: Iterate through all fields, gather the errors and custom error message
		isNotNull();
		for (int field : expectedFieldTags) {
			hasField(field);
		}
		return this;
	}

//	public MessageAssert hasMessageField(int expectedFieldTag) {
//		isNotNull();
//		if (!actual.isSetField(expectedFieldTag) &&
//				(actual.getHeader() == null || !actual.getHeader().isSetField(expectedFieldTag)) &&
//				(actual.getTrailer() == null || !actual.getTrailer().isSetField(expectedFieldTag))) {
//			throw failures.failure(info, shouldHaveField(actual, expectedFieldTag));
//		}
//
//		return this;
//	}
//
//	public MessageAssert hasMessageFields(int... expectedFieldTags) {
//		// TODO: Iterate through all fields, gather the errors and custom error message
//		isNotNull();
//		for (int field : expectedFieldTags) {
//			hasMessageField(field);
//		}
//		return this;
//	}

	// TODO: Rename to containsField(fieldTag, expectedValue)
	public MessageAssert hasFieldValue(int expectedFieldTag, Object expectedFieldValue) {
		isNotNull();
		//		    Objects.instance().assertNotNull(info, actual);
		checkArgument(expectedFieldTag > 0, "'expectedFieldTag' must be greater than 0.");
		checkArgument(expectedFieldValue != null, "'expectedFieldValue' must not be null.");

		hasField(expectedFieldTag);

		try {
			Object actualFieldValue = messages.getFieldValue(expectedFieldTag, this.beginString, this.actual);
			if (!expectedFieldValue.equals(actualFieldValue))
				throw failures.failure(info, fieldShouldHaveValue(actual, expectedFieldTag, actualFieldValue, expectedFieldValue));
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}

		return this;
	}

	public MessageAssert isAdmin() {
		isNotNull();
		if (!actual.isAdmin())
			throw failures.failure(info, shouldBeAdminMessage(actual));
		return this;
	}

	public MessageAssert isApp() {
		isNotNull();
		if (!actual.isApp())
			throw failures.failure(info, shouldBeAppMessage(actual));
		return this;
	}

	public MessageAssert isEmpty() {
		isNotNull();
		if (!actual.isEmpty())
			throw failures.failure(info, shouldBeEmptyMessage(actual));
		return this;
	}

	public MessageAssert isHeartbeat() {
		messages.assertMessageIsOfType(info, actual, HEARTBEAT);
		return this;
	}

	public MessageAssert isNewOrderSingle() {
		messages.assertMessageIsOfType(info, actual, ORDER_SINGLE);
		return this;
	}

	// TODO: Rename to isOfType
	public MessageAssert hasMsgType(String expectedMsgType) {
		messages.assertMessageIsOfType(info, actual, expectedMsgType);
		return this;
	}

	// TODO: Rename to isOfType
	public MessageAssert hasMsgTypeName(String expectedMsgTypeName) {
		String msgType = Dictionaries.getSessionDataDictionary(beginString).getMsgType(expectedMsgTypeName);
		System.out.println(msgType);
//		messages.assertMessageIsOfTypeName(info, actual, expectedMsgTypeName);
		return this;
	}

	public MessageAssert hasBodyLength(int expectedBodyLength) {
		isNotNull();

		if (actual.bodyLength() != expectedBodyLength)
			throw failures.failure(info, shouldBeEqual(actual, BodyLength.class, BodyLength.FIELD, actual.bodyLength(), expectedBodyLength));
		return this;
	}

	public MessageAssert usingDataDictionary(String beginString, DataDictionary dataDictionary) {
		checkArgument(dataDictionary != null, "'dataDictionary' must not be null");

		dictionaries.addDataDictionary(beginString, dataDictionary);
		return this;
	}
}
