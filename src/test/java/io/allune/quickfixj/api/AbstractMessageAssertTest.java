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

import org.junit.Test;
import quickfix.InvalidMessage;
import quickfix.Message;
import quickfix.field.Account;
import quickfix.field.BeginString;
import quickfix.field.BodyLength;
import quickfix.field.CheckSum;
import quickfix.field.ClOrdID;
import quickfix.field.MsgSeqNum;
import quickfix.field.MsgType;
import quickfix.field.OrdType;
import quickfix.field.SenderCompID;
import quickfix.field.SendingTime;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TargetCompID;

import static io.allune.quickfixj.api.Assertions.assertThat;

/**
 * @author Eduardo Sanchez-Ros
 */
public class AbstractMessageAssertTest {

	@Test
	public void shouldAssertMessageHasField() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.0\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000138=1000\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000155=GBP/USD\u000159=0\u000160=20100225-19:39:52.020\u000110=074\u0001");

		// When/Then
		//@formatter:off
		assertThat(message)
				.isVersion40()
				.header()
					.hasField(BeginString.FIELD)
					.hasField(BodyLength.FIELD)
					.hasField(MsgType.FIELD)
					.hasField(MsgSeqNum.FIELD)
					.hasField(SenderCompID.FIELD)
					.hasField(SendingTime.FIELD)
					.hasField(TargetCompID.FIELD)
				.and()
				.trailer()
					.hasField(CheckSum.FIELD)
				.and()
					.hasField(Account.FIELD)
					.hasField(ClOrdID.FIELD)
					.hasField(Side.FIELD)
					.hasField(Symbol.FIELD)
					.hasField(OrdType.FIELD)
		;
		//@formatter:on
	}

	@Test
	public void shouldFailToAssertMessageHasField() {
		// TODO
	}


	@Test
	public void shouldFailToAssertMessageHasFieldValue() {
		// TODO
	}

	@Test
	public void shouldAssertMessageHasMessageType() throws InvalidMessage {
		// Given
		Message message = new Message(
				"8=FIX.4.0\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000138=1000\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000155=GBP/USD\u000159=0\u000160=20100225-19:39:52.020\u000110=074\u0001");

		// When/Then
		assertThat(message)
				.hasMsgType("D");
	}

	@Test
	public void shouldAssertIsEquals() throws InvalidMessage {
		Message message1 = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
		Message message2 = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");

		// TODO
		//		assertThat(message1).isEqualTo(message2);
	}
}