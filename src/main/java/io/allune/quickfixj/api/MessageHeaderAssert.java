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

import static io.allune.quickfixj.error.ShouldBeEqual.shouldBeEqual;
import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;

import io.allune.quickfixj.internal.Messages;
import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.field.BeginString;
import quickfix.field.BodyLength;
import quickfix.field.OnBehalfOfCompID;
import quickfix.field.SenderCompID;
import quickfix.field.TargetCompID;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageHeaderAssert extends AbstractMessageAssert<MessageHeaderAssert, Message> {

	protected Messages messages = Messages.instance();

	/**
	 * Creates a new <code>{@link AbstractMessageAssert}</code>.
	 *
	 * @param message the actual value to verify
	 */
	protected MessageHeaderAssert(Message message) {
		super(MessageHeaderAssert.class, message);
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

	public MessageHeaderAssert hasBodyLength(int expectedBodyLength) {
		isNotNull();
		if (actual.bodyLength() != expectedBodyLength)
			throw failures.failure(info, shouldBeEqual(actual, BodyLength.class, BodyLength.FIELD, actual.bodyLength(), expectedBodyLength));
		return this;
	}

	// TODO: Rename to isOfType
	public MessageHeaderAssert hasMessageType(String expectedMsgType) {
		messages.assertMessageIsOfType(info, actual, expectedMsgType);
		return this;
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
