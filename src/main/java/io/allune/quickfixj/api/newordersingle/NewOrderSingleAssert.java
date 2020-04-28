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

	public static class NewOrderSingle40Assert extends AbstractNewOrderSingleAssert<NewOrderSingle40Assert, NewOrderSingle> {

		public NewOrderSingle40Assert(NewOrderSingle actual) {
			super(NewOrderSingle40Assert.class, actual);
		}
	}

	public static class NewOrderSingle41Assert extends AbstractNewOrderSingleAssert<NewOrderSingle41Assert, quickfix.fix41.NewOrderSingle> {

		public NewOrderSingle41Assert(quickfix.fix41.NewOrderSingle actual) {
			super(NewOrderSingle41Assert.class, actual);
		}
	}

	public static class NewOrderSingle42Assert extends AbstractNewOrderSingleAssert<NewOrderSingle42Assert, quickfix.fix42.NewOrderSingle> {

		public NewOrderSingle42Assert(quickfix.fix42.NewOrderSingle actual) {
			super(NewOrderSingle42Assert.class, actual);
		}
	}

	public static class NewOrderSingle43Assert extends AbstractNewOrderSingleAssert<NewOrderSingle43Assert, quickfix.fix43.NewOrderSingle> {

		public NewOrderSingle43Assert(quickfix.fix43.NewOrderSingle actual) {
			super(NewOrderSingle43Assert.class, actual);
		}
	}

	public static class NewOrderSingle44Assert extends AbstractNewOrderSingleAssert<NewOrderSingle44Assert, quickfix.fix44.NewOrderSingle> {

		public NewOrderSingle44Assert(quickfix.fix44.NewOrderSingle actual) {
			super(NewOrderSingle44Assert.class, actual);
		}
	}

	public static class NewOrderSingle50Assert extends AbstractNewOrderSingleAssert<NewOrderSingle50Assert, quickfix.fix50.NewOrderSingle> {

		public NewOrderSingle50Assert(quickfix.fix50.NewOrderSingle actual) {
			super(NewOrderSingle50Assert.class, actual);
		}
	}

	public static class NewOrderSingle50sp1Assert extends AbstractNewOrderSingleAssert<NewOrderSingle50sp1Assert, quickfix.fix50sp1.NewOrderSingle> {

		public NewOrderSingle50sp1Assert(quickfix.fix50sp1.NewOrderSingle actual) {
			super(NewOrderSingle50sp1Assert.class, actual);
		}
	}

	public static class NewOrderSingle50sp2Assert extends AbstractNewOrderSingleAssert<NewOrderSingle50sp2Assert, quickfix.fix50sp2.NewOrderSingle> {

		public NewOrderSingle50sp2Assert(quickfix.fix50sp2.NewOrderSingle actual) {
			super(NewOrderSingle50sp2Assert.class, actual);
		}
	}
}
