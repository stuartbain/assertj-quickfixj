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

import io.allune.quickfixj.api.AbstractMessageAssert;
import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.field.Account;
import quickfix.field.ClOrdID;

/**
 * @author Eduardo Sanchez-Ros
 */
public class AbstractNewOrderSingle<ACTUAL extends AbstractMessageAssert<ACTUAL, SELF>, SELF extends Message> extends AbstractMessageAssert<ACTUAL, SELF> {

	protected AbstractNewOrderSingle(Class<ACTUAL> selfType, SELF self) {
		super(selfType, self);
	}

	public AbstractNewOrderSingle<ACTUAL, SELF> hasClOrdID(String clOrdID) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getString(ClOrdID.FIELD), clOrdID);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(ClOrdID.class, ClOrdID.FIELD));
		}
		return this;
	}

	public AbstractNewOrderSingle<ACTUAL, SELF> hasAccount(String account) {
		isNotNull();
		try {
			objects.assertEqual(info, actual.getString(Account.FIELD), account);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(Account.class, Account.FIELD));
		}
		return this;
	}
}
