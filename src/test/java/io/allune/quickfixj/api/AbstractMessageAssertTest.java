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

import static io.allune.quickfixj.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

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

/**
 * @author Eduardo Sanchez-Ros
 */
public class AbstractMessageAssertTest {

	@Test
	public void shouldAssertHasBodyLength() throws Exception {
		Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
		assertThat(message)
				.hasHeader()
				.hasBodyLength(61);
	}

	@Test
	public void shouldFailToAssertHasBodyLength() throws Exception {
		// Given
		Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");

		try {
			// When
			assertThat(message)
					.hasHeader()
					.hasBodyLength(60);
		} catch (AssertionError e) {
			// Then
			assertThat(e).hasMessage("Expecting value for field <quickfix.field.BodyLength> (tag=<9>) in Message:\n"
					+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>\n"
					+ "to be:\n"
					+ " <60>\n"
					+ "but was:\n"
					+ " <61>");
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldAssertMessageHasField() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.0\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000138=1000\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000155=GBP/USD\u000159=0\u000160=20100225-19:39:52.020\u000110=074\u0001");

		// When/Then
		assertThat(message)
				.hasHeaderField(BeginString.FIELD)
				.hasHeaderField(BodyLength.FIELD)
				.hasHeaderField(MsgType.FIELD)
				.hasHeaderField(MsgSeqNum.FIELD)
				.hasHeaderField(SenderCompID.FIELD)
				.hasHeaderField(SendingTime.FIELD)
				.hasHeaderField(TargetCompID.FIELD)
				.hasBodyField(Account.FIELD)
				.hasBodyField(ClOrdID.FIELD)
				.hasBodyField(Side.FIELD)
				.hasBodyField(Symbol.FIELD)
				.hasBodyField(OrdType.FIELD)
				.hasTrailerField(CheckSum.FIELD);
	}

	@Test
	public void shouldFailToAssertMessageHasField() {
		// TODO
	}

	@Test
	public void shouldAssertMessageHasFields() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.0\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000138=1000\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000155=GBP/USD\u000159=0\u000160=20100225-19:39:52.020\u000110=074\u0001");

		// When/Then
		assertThat(message)
				.hasFields(
						BeginString.FIELD,
						BodyLength.FIELD,
						MsgType.FIELD,
						MsgSeqNum.FIELD,
						SenderCompID.FIELD,
						SendingTime.FIELD,
						TargetCompID.FIELD,
						Account.FIELD,
						ClOrdID.FIELD,
						Side.FIELD,
						Symbol.FIELD,
						OrdType.FIELD,
						CheckSum.FIELD);
	}

	@Test
	public void shouldFailToAssertMessageHasFields() {
		// TODO
	}

	@Test
	public void shouldAssertMessageHasFieldValue() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.0\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000138=1000\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000155=GBP/USD\u000159=0\u000160=20100225-19:39:52.020\u000110=074\u0001");

		// When/Then
		assertThat(message)
				.hasFieldValue(BeginString.FIELD, "FIX.4.0")
				.hasFieldValue(BodyLength.FIELD, "122")
				.hasFieldValue(MsgType.FIELD, "D")
				.hasFieldValue(MsgSeqNum.FIELD, "215")
				.hasFieldValue(SenderCompID.FIELD, "CLIENT12")
				.hasFieldValue(SendingTime.FIELD, "20100225-19:41:57.316")
				.hasFieldValue(TargetCompID.FIELD, "B")
				.hasFieldValue(Account.FIELD, "Marcel")
				.hasFieldValue(ClOrdID.FIELD, "13346")
				.hasFieldValue(Side.FIELD, "1")
				.hasFieldValue(Symbol.FIELD, "GBP/USD")
				.hasFieldValue(OrdType.FIELD, "2")
				.hasFieldValue(CheckSum.FIELD, "074");
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
				.hasHeader()
				.hasMessageType("D");
	}

	@Test
	public void shouldAssertIsEquals() throws InvalidMessage {
		Message message1 = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
		Message message2 = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");

		// TODO
		//		assertThat(message1).isEqualTo(message2);
	}
}