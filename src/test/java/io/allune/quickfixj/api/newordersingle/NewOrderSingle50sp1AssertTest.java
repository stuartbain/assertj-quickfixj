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
package io.allune.quickfixj.api.newordersingle;

import static io.allune.quickfixj.api.newordersingle.NewOrderSingleAssertions.assertThat;
import static io.allune.quickfixj.api.support.TestNewOrderSingleMessageFactory.newOrderSingleBuilder;
import static quickfix.FixVersions.FIX50SP2;
import static quickfix.field.HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import io.allune.quickfixj.api.support.NewOrderSingle.NewOrderSingleBuilder;
import quickfix.field.HandlInst;
import quickfix.field.OrdType;
import quickfix.field.Side;

/**
 * @author Eduardo Sanchez-Ros
 */
public class NewOrderSingle50sp1AssertTest {

	private NewOrderSingleBuilder<?, ?> messageBuilder;

	@Before
	public void setUp() {
		messageBuilder = newOrderSingleBuilder(FIX50SP2)
				.clientOrderId("13346")
				.handlInst(AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION)
				.symbol("GBP/USD")
				.side(Side.BUY)
				.orderQty(1000D)
				.orderType(OrdType.LIMIT)
				.price(300.00)
				.account("Marcel")
				.transactTime(LocalDateTime.now());
	}

	@Test
	public void shouldAssertNewOrderSingle50sp1HasClOrdID() {
		// Given
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasClOrdID("13346");
	}

	@Test
	public void shouldAssertNewOrderSingle50sp1HasHandlInst() {
		// Given
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasHandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION);
	}

	@Test
	public void shouldAssertNewOrderSingle50sp1HasSymbol() {
		// Given
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasSymbol("GBP/USD");
	}

	@Test
	public void shouldAssertNewOrderSingle50sp1HasSide() {
		// Given
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasSide(Side.BUY);
	}

	@Test
	public void shouldAssertNewOrderSingle50sp1HasOrderQty() {
		// Given
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasOrderQty(1000D);
	}

	@Test
	public void shouldAssertNewOrderSingle50sp1HasOrdType() {
		// Given
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasOrdType(OrdType.LIMIT);
	}

	@Test
	public void shouldAssertNewOrderSingle50sp1HasTransactTime() {
		// Given
		LocalDateTime now = LocalDateTime.now();
		messageBuilder.transactTime(now);
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasTransactTime(now);
	}

	@Test
	public void shouldAssertNewOrderSingle50sp1HasAccount() {
		// Given
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasAccount("Marcel");
	}

	@Test
	public void shouldAssertNewOrderSingleAllFields() {
		// Given
		LocalDateTime now = LocalDateTime.now();
		messageBuilder.transactTime(now);
		quickfix.fix50sp2.NewOrderSingle message = messageBuilder.build().toMessage();

		// When/Then
		assertThat(message)
				.hasClOrdID("13346")
				.hasHandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE_NO_BROKER_INTERVENTION)
				.hasSymbol("GBP/USD")
				.hasSide(Side.BUY)
				.hasOrderQty(1000D)
				.hasOrdType(OrdType.LIMIT)
				.hasTransactTime(now)
				.hasAccount("Marcel");
	}
}
