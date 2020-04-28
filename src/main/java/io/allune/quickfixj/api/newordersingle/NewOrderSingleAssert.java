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

import quickfix.fix40.NewOrderSingle;

/**
 * @author Eduardo Sanchez-Ros
 */
public class NewOrderSingleAssert {

	public static class NewOrderSingleAssert40Assert extends AbstractNewOrderSingleAssert<NewOrderSingleAssert40Assert, NewOrderSingle> {

		public NewOrderSingleAssert40Assert(NewOrderSingle actual) {
			super(NewOrderSingleAssert40Assert.class, actual);
		}
	}

	public static class NewOrderSingleAssert41Assert extends AbstractNewOrderSingleAssert<NewOrderSingleAssert41Assert, quickfix.fix41.NewOrderSingle> {

		public NewOrderSingleAssert41Assert(quickfix.fix41.NewOrderSingle actual) {
			super(NewOrderSingleAssert41Assert.class, actual);
		}
	}

	public static class NewOrderSingleAssert42Assert extends AbstractNewOrderSingleAssert<NewOrderSingleAssert42Assert, quickfix.fix42.NewOrderSingle> {

		public NewOrderSingleAssert42Assert(quickfix.fix42.NewOrderSingle actual) {
			super(NewOrderSingleAssert42Assert.class, actual);
		}
	}

	public static class NewOrderSingleAssert43Assert extends AbstractNewOrderSingleAssert<NewOrderSingleAssert43Assert, quickfix.fix43.NewOrderSingle> {

		public NewOrderSingleAssert43Assert(quickfix.fix43.NewOrderSingle actual) {
			super(NewOrderSingleAssert43Assert.class, actual);
		}
	}

	public static class NewOrderSingleAssert44Assert extends AbstractNewOrderSingleAssert<NewOrderSingleAssert44Assert, quickfix.fix44.NewOrderSingle> {

		public NewOrderSingleAssert44Assert(quickfix.fix44.NewOrderSingle actual) {
			super(NewOrderSingleAssert44Assert.class, actual);
		}
	}

	public static class NewOrderSingleAssert50Assert extends AbstractNewOrderSingleAssert<NewOrderSingleAssert50Assert, quickfix.fix50.NewOrderSingle> {

		public NewOrderSingleAssert50Assert(quickfix.fix50.NewOrderSingle actual) {
			super(NewOrderSingleAssert50Assert.class, actual);
		}
	}

	public static class NewOrderSingleAssert50sp1Assert extends AbstractNewOrderSingleAssert<NewOrderSingleAssert50sp1Assert, quickfix.fix50sp1.NewOrderSingle> {

		public NewOrderSingleAssert50sp1Assert(quickfix.fix50sp1.NewOrderSingle actual) {
			super(NewOrderSingleAssert50sp1Assert.class, actual);
		}
	}

	public static class NewOrderSingleAssert50sp2Assert extends AbstractNewOrderSingleAssert<NewOrderSingleAssert50sp2Assert, quickfix.fix50sp2.NewOrderSingle> {

		public NewOrderSingleAssert50sp2Assert(quickfix.fix50sp2.NewOrderSingle actual) {
			super(NewOrderSingleAssert50sp2Assert.class, actual);
		}
	}
}
