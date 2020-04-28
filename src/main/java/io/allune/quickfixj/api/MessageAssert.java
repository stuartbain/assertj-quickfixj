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

import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.field.MsgType.ORDER_SINGLE;

import io.allune.quickfixj.api.newordersingle.AbstractNewOrderSingleAssert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertFactory;
import quickfix.Message;

/**
 * @author Eduardo Sanchez-Ros
 */
@SuppressWarnings("rawtypes")
public class MessageAssert extends AbstractMessageAssert<MessageAssert, Message> {

	NewOrderSingleAssertFactory assertFactory = new NewOrderSingleAssertFactory();

	protected MessageAssert(Message message) {
		super(MessageAssert.class, message);
	}

	public AbstractNewOrderSingleAssert isNewOrderSingle40() {
		hasVersion(BEGINSTRING_FIX40);
		assertSameMessageType(ORDER_SINGLE);
		return assertFactory.newOrderSingleAssertFromFixVersion(info, BEGINSTRING_FIX40, actual.toRawString());
	}

	public AbstractNewOrderSingleAssert isNewOrderSingle41() {
		hasVersion(BEGINSTRING_FIX41);
		assertSameMessageType(ORDER_SINGLE);
		return assertFactory.newOrderSingleAssertFromFixVersion(info, BEGINSTRING_FIX41, actual.toRawString());
	}

	public AbstractNewOrderSingleAssert isNewOrderSingle42() {
		hasVersion(BEGINSTRING_FIX42);
		assertSameMessageType(ORDER_SINGLE);
		return assertFactory.newOrderSingleAssertFromFixVersion(info, BEGINSTRING_FIX42, actual.toRawString());
	}

	public AbstractNewOrderSingleAssert isNewOrderSingle43() {
		hasVersion(BEGINSTRING_FIX43);
		assertSameMessageType(ORDER_SINGLE);
		return assertFactory.newOrderSingleAssertFromFixVersion(info, BEGINSTRING_FIX43, actual.toRawString());
	}

	public AbstractNewOrderSingleAssert isNewOrderSingle44() {
		hasVersion(BEGINSTRING_FIX44);
		assertSameMessageType(ORDER_SINGLE);
		return assertFactory.newOrderSingleAssertFromFixVersion(info, BEGINSTRING_FIX44, actual.toRawString());
	}
}
