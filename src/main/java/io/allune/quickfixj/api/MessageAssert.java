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

import static io.allune.quickfixj.error.ShouldBeValidMessage.shouldBeValidMessage;
import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;
import static io.allune.quickfixj.internal.Messages.createFromMessage;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.MessageUtils.getMessageType;
import static quickfix.field.MsgType.ORDER_SINGLE;

import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert40Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert41Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert42Assert;
import quickfix.InvalidMessage;
import quickfix.Message;
import quickfix.field.MsgType;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssert extends AbstractMessageAssert<MessageAssert, Message> {

	protected MessageAssert(Message message) {
		super(MessageAssert.class, message);
	}

	public NewOrderSingleAssert40Assert isNewOrderSingle40() {
		hasVersion(BEGINSTRING_FIX40);
		assertSameMessageType(ORDER_SINGLE);
		try {
			return new NewOrderSingleAssert40Assert(createFromMessage(BEGINSTRING_FIX40, ORDER_SINGLE, actual.toRawString()));
		} catch (InvalidMessage e) {
			throw failures.failure(info, shouldBeValidMessage(actual.toRawString(), e.getMessage()));
		}
	}

	public NewOrderSingleAssert41Assert isNewOrderSingle41() {
		hasVersion(BEGINSTRING_FIX41);
		assertSameMessageType(ORDER_SINGLE);
		try {
			return new NewOrderSingleAssert41Assert(createFromMessage(BEGINSTRING_FIX41, ORDER_SINGLE, actual.toRawString()));
		} catch (InvalidMessage e) {
			throw failures.failure(info, shouldBeValidMessage(actual.toRawString(), e.getMessage()));
		}
	}

	public NewOrderSingleAssert42Assert isNewOrderSingle42() {
		hasVersion(BEGINSTRING_FIX42);
		assertSameMessageType(ORDER_SINGLE);
		try {
			return new NewOrderSingleAssert42Assert(createFromMessage(BEGINSTRING_FIX42, ORDER_SINGLE, actual.toRawString()));
		} catch (InvalidMessage e) {
			throw failures.failure(info, shouldBeValidMessage(actual.toRawString(), e.getMessage()));
		}
	}

	private void assertSameMessageType(String msgType) {
		try {
			objects.assertEqual(info, msgType, getMessageType(actual.toString()));
		} catch (InvalidMessage invalidMessage) {
			throw failures.failure(info, shouldHaveField(MsgType.class, MsgType.FIELD));
		}
	}
}
