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

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import quickfix.Message;

import static io.allune.quickfixj.error.ShouldHaveVersionEqualTo.shouldHaveVersionEqualTo;

/**
 * @author Simon Lewis
 * @author Eduardo Sanchez-Ros
 */
public class Versions {

	private static final Versions INSTANCE = new Versions();

	private static final Messages messages = Messages.instance();

	private static final Failures failures = Failures.instance();

	private static final Objects objects = Objects.instance();

	Versions() {
	}

	public static Versions instance() {
		return INSTANCE;
	}

	public void assertMessageIsVersion(AssertionInfo info, Message message, String expectedBeginString) {
		assertMessageIsVersion(info, message, expectedBeginString, null);
	}

	public void assertMessageIsVersion(AssertionInfo info, Message message, String expectedBeginString, String expectedApplVerId) {
		objects.assertNotNull(info, message);

		String actualBeginString = messages.getBeginString(info, message);
		if (!actualBeginString.equals(expectedBeginString)) {
			throw failures.failure(info, shouldHaveVersionEqualTo(message, actualBeginString, expectedBeginString));
		}

		if (expectedApplVerId != null && expectedApplVerId.length() > 0) {
			String actualApplVerId = messages.getApplVerId(info, message);
			if (!actualApplVerId.equals(expectedApplVerId)) {
				throw failures.failure(info, shouldHaveVersionEqualTo(message, actualApplVerId, expectedApplVerId));
			}
		}
	}

}
