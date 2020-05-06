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

import quickfix.Message;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssert extends AbstractMessageAssert<MessageAssert, Message> {

	public MessageAssert(Message message) {
		super(MessageAssert.class, message);
	}

	public MessageAssert isVersion40() {
		versions.assertMessageIsVersionFix40(info, actual);
		return this;
	}

	public MessageAssert isVersion41() {
		versions.assertMessageIsVersionFix41(info, actual);
		return this;
	}

	public MessageAssert isVersion42() {
		versions.assertMessageIsVersionFix42(info, actual);
		return this;
	}

	public MessageAssert isVersion43() {
		versions.assertMessageIsVersionFix43(info, actual);
		return this;
	}

	public MessageAssert isVersion44() {
		versions.assertMessageIsVersionFix44(info, actual);
		return this;
	}

	public MessageAssert isVersion50() {
		versions.assertMessageIsVersionFix50(info, actual);
		return this;
	}

	public MessageAssert isVersion50sp1() {
		versions.assertMessageIsVersionFix50sp1(info, actual);
		return this;
	}

	public MessageAssert isVersion50sp2() {
		versions.assertMessageIsVersionFix50sp2(info, actual);
		return this;
	}
}
