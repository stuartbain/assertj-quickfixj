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

import io.allune.quickfixj.internal.Messages;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import quickfix.FieldNotFound;
import quickfix.Message.Header;
import quickfix.field.BeginString;
import quickfix.field.OnBehalfOfCompID;
import quickfix.field.SenderCompID;
import quickfix.field.TargetCompID;

import static io.allune.quickfixj.error.ShouldBeEqual.shouldBeEqual;
import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageHeaderAssert extends AbstractAssert<MessageHeaderAssert, Header> {

	Messages messages = Messages.instance();

	Failures failures = Failures.instance();

	private final MessageAssert messageAssert;

	/**
	 * Creates a new <code>{@link MessageHeaderAssert}</code>.
	 *
	 * @param header the actual value to verify
	 * @param messageAssert
	 */
	MessageHeaderAssert(Header header, MessageAssert messageAssert) {
		super(header, MessageHeaderAssert.class);
		this.messageAssert = messageAssert;
	}

	public MessageHeaderAssert hasGroup(int expectedGroupTag) {
		return this;
	}

	public MessageHeaderAssert hasBeginString(String expectedBeginString) {
		isNotNull();
		try {
			String actualBeginString = actual.getString(BeginString.FIELD);
			if (!actualBeginString.equals(expectedBeginString))
				throw failures.failure(info, shouldBeEqual(actual, BeginString.class, BeginString.FIELD, actualBeginString, expectedBeginString));
		} catch (FieldNotFound fieldNotFound) {
			throw failures.failure(info, shouldHaveField(BeginString.class, BeginString.FIELD));
		}
		return this;
	}

	public MessageHeaderAssert hasField(int expectedFieldTag) {
		isNotNull();
		if (!actual.isSetField(expectedFieldTag))
			throw failures.failure(info, shouldHaveField(actual, expectedFieldTag));
		return this;
	}

	public MessageHeaderAssert hasFields(int... expectedFieldTags) {
		// TODO: Iterate through all fields, gather the errors and custom error message
		isNotNull();
		for (int field : expectedFieldTags) {
			hasField(field);
		}
		return this;
	}

	public MessageAssert and() {
		return messageAssert;
	}

	public MessageHeaderAssert hasSenderCompID(String expectedSenderCompID) {
		isNotNull();
		messages.assertFieldHasValue(info, actual, SenderCompID.class, expectedSenderCompID);
		return this;
	}

	public MessageHeaderAssert hasTargetCompID(String expectedTargetCompID) {
		isNotNull();
		messages.assertFieldHasValue(info, actual, TargetCompID.class, expectedTargetCompID);
		return this;
	}

	public MessageHeaderAssert hasOnBehalfOfCompID(String expectedOnBehalfOfCompID) {
		isNotNull();
		messages.assertFieldHasValue(info, actual, OnBehalfOfCompID.class, expectedOnBehalfOfCompID);
		return this;
	}

	//    <field name="DeliverToCompID" required="N" />
	//    <field name="SecureDataLen" required="N" />
	//    <field name="SecureData" required="N" />
	//    <field name="MsgSeqNum" required="Y" />
	//    <field name="SenderSubID" required="N" />
	//    <field name="SenderLocationID" required="N" />
	//    <field name="TargetSubID" required="N" />
	//    <field name="TargetLocationID" required="N" />
	//    <field name="OnBehalfOfSubID" required="N" />
	//    <field name="OnBehalfOfLocationID" required="N" />
	//    <field name="DeliverToSubID" required="N" />
	//    <field name="DeliverToLocationID" required="N" />
	//    <field name="PossDupFlag" required="N" />
	//    <field name="PossResend" required="N" />
	//    <field name="SendingTime" required="Y" />
	//    <field name="OrigSendingTime" required="N" />
	//    <field name="XmlDataLen" required="N" />
	//    <field name="XmlData" required="N" />
	//    <field name="MessageEncoding" required="N" />
	//    <field name="LastMsgSeqNumProcessed" required="N" />
	//    <field name="OnBehalfOfSendingTime" required="N" />
	//    <group name="NoHops" required="N">
	//      <field name="HopCompID" required="N" />
	//      <field name="HopSendingTime" required="N" />
	//      <field name="HopRefID" required="N" />
	//    </group>

	//    <field name="ApplVerID" required="N"/>
	//    <field name="ApplExtID" required="N"/>
	//    <field name="CstmApplVerID" required="N"/>
	//    <component name="HopGrp" required="N"/>
}
