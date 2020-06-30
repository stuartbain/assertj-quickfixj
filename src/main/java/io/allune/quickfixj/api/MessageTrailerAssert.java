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
public class MessageTrailerAssert extends AbstractMessageAssert<MessageTrailerAssert, Message> {

	/**
	 * Creates a new <code>{@link AbstractMessageAssert}</code>.
	 *
	 * @param message the actual value to verify
	 */
	protected MessageTrailerAssert(Message message) {
		super(MessageTrailerAssert.class, message);
	}

	///  <trailer>
	//    <field name="SignatureLength" required="N"/>
	//    <field name="Signature" required="N"/>
	//    <field name="CheckSum" required="Y"/>
	//  </trailer>

	public MessageTrailerAssert hasGroup(int expectedGroupTag) {
		actual.getHeader().hasGroup(expectedGroupTag);
		return this;
	}
}
