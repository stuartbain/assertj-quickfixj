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
import static java.time.LocalDateTime.now;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.FixVersions.FIX50;
import static quickfix.FixVersions.FIX50SP1;
import static quickfix.FixVersions.FIX50SP2;
import static quickfix.field.HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION;

import org.junit.Before;
import org.junit.Test;

import io.allune.quickfixj.api.support.NewOrderSingle;
import io.allune.quickfixj.api.support.NewOrderSingle.NewOrderSingleBuilder;
import quickfix.Message;
import quickfix.field.OrdType;
import quickfix.field.Side;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssertTest {

	NewOrderSingleBuilder<?, ?> messageBuilder;

	@Before
	public void setUp() {
		messageBuilder = NewOrderSingle.builder()
				.sender("BANZAI")
				.target("EXEC")
				.clientOrderId("13346")
				.handlInst(AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION)
				.symbol("GBP/USD")
				.side(Side.BUY)
				.orderQty(1000D)
				.orderType(OrdType.LIMIT)
				.price(300.00)
				.account("Marcel");
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle40() {
		// Given
		Message message = messageBuilder.build()
				.toMessage(BEGINSTRING_FIX40);

		// When/Then
		assertThat(message)
				.isVersion40()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle41() {
		// Given
		Message message = messageBuilder.build()
				.toMessage(BEGINSTRING_FIX41);
		//		Message message = new Message(
		//				"8=FIX.4.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000155=GBP/USD\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000110=19\u0001");

		// When/Then
		assertThat(message)
				.isVersion41()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle42() {
		// Given
		Message message = messageBuilder
				.transactTime(now())
				.build()
				.toMessage(BEGINSTRING_FIX42);
		//		Message message = new Message(
		//				"8=FIX.4.2\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000155=GBP/USD\u000159=0\u000160=20100225-19:39:52.020\u000110=226\u0001");

		// When/Then
		assertThat(message)
				.isVersion42()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle43() {
		// Given
		Message message = messageBuilder
				.transactTime(now())
				.build()
				.toMessage(BEGINSTRING_FIX43);
		//		Message message = new Message(
		//				"8=FIX.4.3\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000155=GBP/USD\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=227\u0001");

		// When/Then
		assertThat(message)
				.isVersion43()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle44() {
		// Given
		Message message = messageBuilder
				.transactTime(now())
				.build()
				.toMessage(BEGINSTRING_FIX44);
		//		Message message = new Message(
		//				"8=FIX.4.4\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000155=GBP/USD\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=228\u0001");

		// When/Then
		assertThat(message)
				.isVersion44()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle50() {
		// Given
		Message message = messageBuilder
				.transactTime(now())
				.build()
				.toMessage(FIX50);
		//		Message message = new Message(
		//				"8=FIXT.1.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u00011128=7\u000156=B\u00011=Marcel\u000121=1\u000140=2\u000111=13346\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=215\u0001");

		// When/Then
		assertThat(message)
				.isVersion50()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle50sp1() {
		// Given
		Message message = messageBuilder
				.transactTime(now())
				.build()
				.toMessage(FIX50SP1);
		//		Message message = new Message(
		//				"8=FIXT.1.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u00011128=8\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=216\u0001");

		// When/Then
		assertThat(message)
				.isVersion50sp1()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle50sp2() {
		// Given
		Message message = messageBuilder
				.transactTime(now())
				.build()
				.toMessage(FIX50SP2);
		//		Message message = new Message(
		//				"8=FIXT.1.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u00011128=9\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=217\u0001");

		// When/Then
		assertThat(message)
				.isVersion50sp2()
				.isNewOrderSingle();
	}
}
