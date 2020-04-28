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

import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;

import java.time.LocalDateTime;

import io.allune.quickfixj.api.AbstractMessageAssert;
import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TransactTime;

/**
 * @author Eduardo Sanchez-Ros
 */
public class AbstractNewOrderSingleAssert<ACTUAL extends AbstractMessageAssert<ACTUAL, SELF>, SELF extends Message> extends AbstractMessageAssert<ACTUAL, SELF> {

	protected AbstractNewOrderSingleAssert(Class<ACTUAL> selfType, SELF self) {
		super(selfType, self);
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasClOrdID(ClOrdID expected) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getString(ClOrdID.FIELD), expected != null ? expected.getObject() : null);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(ClOrdID.class, ClOrdID.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasClOrdID(String expected) {
		return hasClOrdID(new ClOrdID(expected));
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasHandlInst(HandlInst expected) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getChar(HandlInst.FIELD), expected != null ? expected.getObject() : null);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(HandlInst.class, HandlInst.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasHandlInst(Character expected) {
		return hasHandlInst(new HandlInst(expected));
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasSymbol(Symbol expected) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getString(Symbol.FIELD), expected != null ? expected.getObject() : null);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(Symbol.class, Symbol.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasSymbol(String expected) {
		return hasSymbol(new Symbol(expected));
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasSide(Side expected) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getChar(Side.FIELD), expected != null ? expected.getObject() : null);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(Side.class, Side.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasSide(Character expected) {
		return hasSide(new Side(expected));
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasOrderQty(OrderQty expected) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getDouble(OrderQty.FIELD), expected != null ? expected.getObject() : null);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(OrderQty.class, OrderQty.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasOrderQty(Double expected) {
		return hasOrderQty(new OrderQty(expected));
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasOrdType(OrdType expected) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getChar(OrdType.FIELD), expected != null ? expected.getObject() : null);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(OrdType.class, OrdType.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasOrdType(Character expected) {
		return hasOrdType(new OrdType(expected));
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasTransactTime(TransactTime expected) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getUtcTimeStamp(TransactTime.FIELD), expected != null ? expected.getObject() : null);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(TransactTime.class, TransactTime.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasTransactTime(LocalDateTime expected) {
		return hasTransactTime(new TransactTime(expected));
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasAccount(Account expected) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getString(Account.FIELD), expected != null ? expected.getObject() : null);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(Account.class, Account.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingleAssert<ACTUAL, SELF> hasAccount(String expected) {
		return hasAccount(new Account(expected));
	}

}
