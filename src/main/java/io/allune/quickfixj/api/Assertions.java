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

import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert40Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert41Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert42Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert43Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert44Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert50Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert50sp1Assert;
import io.allune.quickfixj.api.newordersingle.NewOrderSingleAssert.NewOrderSingleAssert50sp2Assert;
import quickfix.Message;

/**
 * @author Eduardo Sanchez-Ros
 */
public class Assertions {

	public static MessageAssert assertThat(Message message) {
		return new MessageAssert(message);
	}

	public static NewOrderSingleAssert40Assert assertThat(quickfix.fix40.NewOrderSingle newOrderSingle) {
		return new NewOrderSingleAssert40Assert(newOrderSingle);
	}

	public static NewOrderSingleAssert41Assert assertThat(quickfix.fix41.NewOrderSingle newOrderSingle) {
		return new NewOrderSingleAssert41Assert(newOrderSingle);
	}

	public static NewOrderSingleAssert42Assert assertThat(quickfix.fix42.NewOrderSingle newOrderSingle) {
		return new NewOrderSingleAssert42Assert(newOrderSingle);
	}

	public static NewOrderSingleAssert43Assert assertThat(quickfix.fix43.NewOrderSingle newOrderSingle) {
		return new NewOrderSingleAssert43Assert(newOrderSingle);
	}

	public static NewOrderSingleAssert44Assert assertThat(quickfix.fix44.NewOrderSingle newOrderSingle) {
		return new NewOrderSingleAssert44Assert(newOrderSingle);
	}

	public static NewOrderSingleAssert50Assert assertThat(quickfix.fix50.NewOrderSingle newOrderSingle) {
		return new NewOrderSingleAssert50Assert(newOrderSingle);
	}

	public static NewOrderSingleAssert50sp1Assert assertThat(quickfix.fix50sp1.NewOrderSingle newOrderSingle) {
		return new NewOrderSingleAssert50sp1Assert(newOrderSingle);
	}

	public static NewOrderSingleAssert50sp2Assert assertThat(quickfix.fix50sp2.NewOrderSingle newOrderSingle) {
		return new NewOrderSingleAssert50sp2Assert(newOrderSingle);
	}

	protected Assertions() {
		// empty
	}
}
