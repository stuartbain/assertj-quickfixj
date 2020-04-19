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
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;

import io.allune.quickfixj.api.newordersingle.NewOrderSingle40Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingle41Assert;
import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.InvalidMessage;
import quickfix.Message;
import quickfix.MessageUtils;
import quickfix.field.ClOrdID;
import quickfix.field.MsgType;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssert extends AbstractMessageAssert<MessageAssert, Message> {

	protected MessageAssert(Message message) {
		super(MessageAssert.class, message);
	}

	public NewOrderSingle40Assert isNewOrderSingle40() {
		try {
			hasVersion(BEGINSTRING_FIX40);
			assertIsNewOrderSingle();

			quickfix.fix40.NewOrderSingle newOrderSingle = createNewOrderSingle40FromMessage();
			return new NewOrderSingle40Assert(newOrderSingle);
		} catch (InvalidMessage invalidMessage) {
			throw failures.failure(info, shouldHaveField(ClOrdID.class, ClOrdID.FIELD));
		}
	}

	public NewOrderSingle41Assert isNewOrderSingle41() {
		try {
			hasVersion(BEGINSTRING_FIX41);
			assertIsNewOrderSingle();

			quickfix.fix41.NewOrderSingle newOrderSingle = createNewOrderSingle41FromMessage();
			return new NewOrderSingle41Assert(newOrderSingle);
		} catch (InvalidMessage invalidMessage) {
			throw failures.failure(info, shouldHaveField(ClOrdID.class, ClOrdID.FIELD));
		}
	}

	private void assertIsNewOrderSingle() throws InvalidMessage {
		objects.assertEqual(info, MsgType.ORDER_SINGLE, MessageUtils.getMessageType(actual.toString()));
	}

	private quickfix.fix40.NewOrderSingle createNewOrderSingle40FromMessage() throws InvalidMessage {
		quickfix.fix40.NewOrderSingle newOrderSingle = new quickfix.fix40.NewOrderSingle();
		newOrderSingle.fromString(actual.toRawString(), dataDictionary40, false);
		return newOrderSingle;
	}

	private quickfix.fix41.NewOrderSingle createNewOrderSingle41FromMessage() throws InvalidMessage {
		quickfix.fix41.NewOrderSingle newOrderSingle = new quickfix.fix41.NewOrderSingle();
		newOrderSingle.fromString(actual.toRawString(), dataDictionary41, false);
		return newOrderSingle;
	}

	private static DataDictionary dataDictionary40 = getFix40Dictionary();

	private static DataDictionary dataDictionary41 = getFix41Dictionary();

	public static DataDictionary getFix40Dictionary() {
		if (dataDictionary40 == null) {
			dataDictionary40 = getDictionary("FIX40.xml");
		}
		return dataDictionary40;
	}

	public static DataDictionary getFix41Dictionary() {
		if (dataDictionary41 == null) {
			dataDictionary41 = getDictionary("FIX41.xml");
		}
		return dataDictionary41;
	}

	/**
	 * Loads and returns the named data dictionary.
	 *
	 * @param fileName the data dictionary file name (e.g. "FIX40.xml")
	 * @return a new data dictionary instance
	 * @throws RuntimeException if the named data dictionary cannot be loaded
	 */
	public static DataDictionary getDictionary(String fileName) {
		try {
			return new DataDictionary(MessageAssert.class.getClassLoader().getResourceAsStream(fileName));
		} catch (ConfigError e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
