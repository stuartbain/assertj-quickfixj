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

import static io.allune.quickfixj.error.ShouldHaveFixVersionEqualTo.shouldHaveFixVersionEqualTo;
import static io.allune.quickfixj.error.ShouldHaveHeaderField.shouldHaveHeaderFieldEqualTo;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.FixVersions.BEGINSTRING_FIXT11;

import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.field.BeginString;

/**
 * @author Simon Lewis
 */
public class QuickfixVersionAssert extends MessageAssert<QuickfixVersionAssert> {

	protected QuickfixVersionAssert(Class<QuickfixVersionAssert> selfType, Message actual) {
		super(selfType, actual);
	}

	protected QuickfixVersionAssert(Class<QuickfixVersionAssert> selfType, Message actual, String version) {
		super(selfType, actual);
		fix(version);
	}

	public QuickfixVersionAssert fixt11() {
		return fix(BEGINSTRING_FIXT11);
	}

	public QuickfixVersionAssert fix40() {
		return fix(BEGINSTRING_FIX40);
	}

	public QuickfixVersionAssert fix41() {
		return fix(BEGINSTRING_FIX41);
	}

	public QuickfixVersionAssert fix42() {
		return fix(BEGINSTRING_FIX42);
	}

	public QuickfixVersionAssert fix43() {
		return fix(BEGINSTRING_FIX43);
	}

	public QuickfixVersionAssert fix44() {
		return fix(BEGINSTRING_FIX44);
	}

	// TODO
	//	String FIX50 = "FIX.5.0";
	//	String FIX50SP1 = "FIX.5.0SP1";
	//	String FIX50SP2 = "FIX.5.0SP2";

	public QuickfixVersionAssert fix(String version) {
		isNotNull();
		String begin = getBeginString();
		if (!version.equals(begin)) {
			throw failures.failure(info, shouldHaveFixVersionEqualTo(actual, begin, version));
		}
		return this;
	}

	private String getBeginString() {
		try {
			return actual.getHeader().getString(BeginString.FIELD);
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveHeaderFieldEqualTo(BeginString.class, BeginString.FIELD));
		}
	}
}
