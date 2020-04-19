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

import io.allune.quickfixj.api.newordersingle.NewOrderSingle40Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingle41Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingle42Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingle43Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingle44Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingle50Assert;
import quickfix.Message;

/**
 * @author Eduardo Sanchez-Ros
 */
public class Assertions {

	public static MessageAssert assertThat(Message message) {
		return new MessageAssert(message);
	}

	public static NewOrderSingle40Assert assertThat(quickfix.fix40.NewOrderSingle newOrderSingle) {
		return new NewOrderSingle40Assert(newOrderSingle);
	}

	public static NewOrderSingle41Assert assertThat(quickfix.fix41.NewOrderSingle newOrderSingle) {
		return new NewOrderSingle41Assert(newOrderSingle);
	}

	public static NewOrderSingle42Assert assertThat(quickfix.fix42.NewOrderSingle newOrderSingle) {
		return new NewOrderSingle42Assert(newOrderSingle);
	}

	public static NewOrderSingle43Assert assertThat(quickfix.fix43.NewOrderSingle newOrderSingle) {
		return new NewOrderSingle43Assert(newOrderSingle);
	}

	public static NewOrderSingle44Assert assertThat(quickfix.fix44.NewOrderSingle newOrderSingle) {
		return new NewOrderSingle44Assert(newOrderSingle);
	}

	public static NewOrderSingle50Assert assertThat(quickfix.fix50.NewOrderSingle newOrderSingle) {
		return new NewOrderSingle50Assert(newOrderSingle);
	}

	protected Assertions() {
		// empty
	}
}
