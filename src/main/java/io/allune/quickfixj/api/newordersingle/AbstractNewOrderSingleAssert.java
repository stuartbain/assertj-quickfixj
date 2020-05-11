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

import java.time.LocalDateTime;

import io.allune.quickfixj.api.AbstractMessageAssert;
import quickfix.Message;
import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TransactTime;

/**
 * @author Eduardo Sanchez-Ros
 */
public abstract class AbstractNewOrderSingleAssert<ACTUAL extends AbstractMessageAssert<ACTUAL, SELF>, SELF extends Message> extends AbstractMessageAssert<ACTUAL, SELF> {

	protected AbstractNewOrderSingleAssert(Class<ACTUAL> selfType, SELF self) {
		super(selfType, self);
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasClOrdID(String expected) {
		isNotNull();
		messages.assertHasStringFieldValue(info, actual, ClOrdID.FIELD, ClOrdID.class, expected);
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasHandlInst(Character expected) {
		isNotNull();
		messages.assertHasCharFieldValue(info, actual, HandlInst.FIELD, HandlInst.class, expected);
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasSymbol(String expected) {
		isNotNull();
		messages.assertHasStringFieldValue(info, actual, Symbol.FIELD, Symbol.class, expected);
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasSide(Character expected) {
		isNotNull();
		messages.assertHasCharFieldValue(info, actual, Side.FIELD, Side.class, expected);
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasOrderQty(Double expected) {
		isNotNull();
		messages.assertHasDoubleFieldValue(info, actual, OrderQty.FIELD, OrderQty.class, expected);
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasOrdType(Character expected) {
		isNotNull();
		messages.assertHasCharFieldValue(info, actual, OrdType.FIELD, OrdType.class, expected);
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasTransactTime(LocalDateTime expected) {
		isNotNull();
		messages.assertHasUtcTimeStampFieldValue(info, actual, TransactTime.FIELD, TransactTime.class, expected);
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasAccount(String expected) {
		isNotNull();
		messages.assertHasStringFieldValue(info, actual, Account.FIELD, Account.class, expected);
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasPrice(Double expected) {
		isNotNull();
		messages.assertHasDoubleFieldValue(info, actual, Price.FIELD, Price.class, expected);
		return this;
	}
}
