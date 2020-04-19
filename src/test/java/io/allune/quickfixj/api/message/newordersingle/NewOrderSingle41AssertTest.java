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
package io.allune.quickfixj.api.message.newordersingle;

import static io.allune.quickfixj.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import io.allune.quickfixj.api.MessageAssert;
import quickfix.fix40.NewOrderSingle;

/**
 * @author Eduardo Sanchez-Ros
 */
public class NewOrderSingle41AssertTest {

	private NewOrderSingle message;

	@Before
	public void setUp() throws Exception {
		message = new NewOrderSingle();
		message.fromString(
				"8=FIX.4.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=068\u0001",
				MessageAssert.getFix41Dictionary(),
				false);
	}

	@Test
	public void shouldAssertNewOrderSingle40HasClOrdID() {
		// Given

		// When/Then
		assertThat(message)
				.hasClOrdID("13346");
	}

	@Test
	public void shouldAssertNewOrderSingle41HasAccount() {
		// Given

		// When/Then
		assertThat(message)
				.hasAccount("Marcel");
	}
}
