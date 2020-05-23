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
package io.allune.quickfixj.internal;

import static io.allune.quickfixj.error.ShouldHaveFixVersionEqualTo.shouldHaveFixVersionEqualTo;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.FixVersions.BEGINSTRING_FIXT11;
import static quickfix.field.ApplVerID.FIX50;
import static quickfix.field.ApplVerID.FIX50SP1;
import static quickfix.field.ApplVerID.FIX50SP2;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;

import quickfix.Message;

/**
 * @author Simon Lewis
 * @author Eduardo Sanchez-Ros
 */
public class Versions {

	private static final Versions INSTANCE = new Versions();

	private Messages messages = Messages.instance();

	private Failures failures = Failures.instance();

	Versions() {
	}

	public static Versions instance() {
		return INSTANCE;
	}

	public void assertMessageIsVersionFix40(AssertionInfo info, Message message) {
		assertMessageIsVersion(info, message, BEGINSTRING_FIX40);
	}

	public void assertMessageIsVersionFix41(AssertionInfo info, Message message) {
		assertMessageIsVersion(info, message, BEGINSTRING_FIX41);
	}

	public void assertMessageIsVersionFix42(AssertionInfo info, Message message) {
		assertMessageIsVersion(info, message, BEGINSTRING_FIX42);
	}

	public void assertMessageIsVersionFix43(AssertionInfo info, Message message) {
		assertMessageIsVersion(info, message, BEGINSTRING_FIX43);
	}

	public void assertMessageIsVersionFix44(AssertionInfo info, Message message) {
		assertMessageIsVersion(info, message, BEGINSTRING_FIX44);
	}

	public void assertMessageIsVersionFix50(AssertionInfo info, Message message) {
		assertMessageIsVersion(info, message, BEGINSTRING_FIXT11, FIX50);
	}

	public void assertMessageIsVersionFix50sp1(AssertionInfo info, Message message) {
		assertMessageIsVersion(info, message, BEGINSTRING_FIXT11, FIX50SP1);
	}

	public void assertMessageIsVersionFix50sp2(AssertionInfo info, Message message) {
		assertMessageIsVersion(info, message, BEGINSTRING_FIXT11, FIX50SP2);
	}

	public void assertMessageIsVersion(AssertionInfo info, Message message, String expectedBeginString) {
		assertMessageIsVersion(info, message, expectedBeginString, null);
	}

	public void assertMessageIsVersion(AssertionInfo info, Message message, String expectedBeginString, String expectedApplVerId) {
		assertNotNull(info, message);
		String actualBeginString = messages.getBeginString(info, message);
		if (!actualBeginString.equals(expectedBeginString)) {
			throw failures.failure(info, shouldHaveFixVersionEqualTo(message, actualBeginString, expectedBeginString));
		}

		if (expectedApplVerId != null && expectedApplVerId.length() > 0) {
			String actualApplVerId = messages.getApplVerId(info, message);
			if (!actualApplVerId.equals(expectedApplVerId)) {
				throw failures.failure(info, shouldHaveFixVersionEqualTo(message, actualApplVerId, expectedApplVerId));
			}
		}
	}

	private static void assertNotNull(AssertionInfo info, Message actual) {
		Objects.instance().assertNotNull(info, actual);
	}
}
