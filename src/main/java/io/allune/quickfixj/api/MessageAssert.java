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

import static io.allune.quickfixj.error.ShouldBeAdminMessage.shouldBeAdminMessage;
import static io.allune.quickfixj.error.ShouldBeAppMessage.shouldBeAppMessage;
import static io.allune.quickfixj.error.ShouldBeEmptyMessage.shouldBeEmptyMessage;
import static io.allune.quickfixj.error.ShouldHaveHeader.shouldHaveHeader;
import static io.allune.quickfixj.error.ShouldHaveTrailer.shouldHaveTrailer;
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

import quickfix.FixVersions;
import quickfix.Message;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssert extends AbstractMessageAssert<MessageAssert, Message> {

	public MessageAssert(Message message) {
		super(MessageAssert.class, message);
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

	public MessageHeaderAssert hasHeader() {
		isNotNull();
		if (actual.getHeader() == null)
			throw failures.failure(info, shouldHaveHeader(actual));
		return new MessageHeaderAssert(actual);
	}

	public MessageTrailerAssert hasTrailer() {
		isNotNull();
		if (actual.getTrailer() == null)
			throw failures.failure(info, shouldHaveTrailer(actual));
		return new MessageTrailerAssert(actual);
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
}
