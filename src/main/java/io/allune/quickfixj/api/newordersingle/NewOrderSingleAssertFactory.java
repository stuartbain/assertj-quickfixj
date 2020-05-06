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
package io.allune.quickfixj.api.newordersingle;

import static io.allune.quickfixj.error.ShouldBeValidMessage.shouldBeValidMessage;
import static io.allune.quickfixj.error.ShouldBeValidVersion.shouldBeValidVersion;
import static io.allune.quickfixj.internal.Messages.createFromMessage;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.FixVersions.FIX50;
import static quickfix.FixVersions.FIX50SP1;
import static quickfix.FixVersions.FIX50SP2;
import static quickfix.field.MsgType.ORDER_SINGLE;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Failures;

import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.NewOrderSingle40Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.NewOrderSingle41Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.NewOrderSingle42Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.NewOrderSingle43Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.NewOrderSingle44Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.NewOrderSingle50Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.NewOrderSingle50sp1Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.NewOrderSingle50sp2Assert;
import io.allune.quickfixj.internal.InvalidMessageException;

/**
 * @author Eduardo Sanchez-Ros
 */
public class NewOrderSingleAssertFactory {

	protected Failures failures = Failures.instance();

	final static Map<String, Function<String, AbstractNewOrderSingleAssert<?, ?>>> map = new HashMap<String, Function<String, AbstractNewOrderSingleAssert<?, ?>>>() {

		{
			put(BEGINSTRING_FIX40, messageData -> new NewOrderSingle40Assert(createFromMessage(BEGINSTRING_FIX40, ORDER_SINGLE, messageData)));
			put(BEGINSTRING_FIX41, messageData -> new NewOrderSingle41Assert(createFromMessage(BEGINSTRING_FIX41, ORDER_SINGLE, messageData)));
			put(BEGINSTRING_FIX42, messageData -> new NewOrderSingle42Assert(createFromMessage(BEGINSTRING_FIX42, ORDER_SINGLE, messageData)));
			put(BEGINSTRING_FIX43, messageData -> new NewOrderSingle43Assert(createFromMessage(BEGINSTRING_FIX43, ORDER_SINGLE, messageData)));
			put(BEGINSTRING_FIX44, messageData -> new NewOrderSingle44Assert(createFromMessage(BEGINSTRING_FIX44, ORDER_SINGLE, messageData)));
			put(FIX50, messageData -> new NewOrderSingle50Assert(createFromMessage(FIX50, ORDER_SINGLE, messageData)));
			put(FIX50SP1, messageData -> new NewOrderSingle50sp1Assert(createFromMessage(FIX50SP1, ORDER_SINGLE, messageData)));
			put(FIX50SP2, messageData -> new NewOrderSingle50sp2Assert(createFromMessage(FIX50SP2, ORDER_SINGLE, messageData)));
		}
	};

	public AbstractNewOrderSingleAssert<?, ?> newOrderSingleAssertFromFixVersion(AssertionInfo info, String beginString, String messageData) {
		try {
			Function<String, AbstractNewOrderSingleAssert<?, ?>> newOrderSingleAssertFunction = map.get(beginString);
			if (newOrderSingleAssertFunction == null) {
				throw failures.failure(info, shouldBeValidVersion(beginString));
			}

			return newOrderSingleAssertFunction.apply(messageData);
		} catch (InvalidMessageException e) {
			throw failures.failure(info, shouldBeValidMessage(messageData, e.getMessage()));
		}
	}
}
