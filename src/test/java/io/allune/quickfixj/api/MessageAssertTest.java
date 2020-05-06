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

import org.junit.Test;

import quickfix.Message;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssertTest {

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle40() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.0\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=068\u0001");

		// When/Then
		assertThat(message)
				.isVersion40()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle41() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=069\u0001");

		// When/Then
		assertThat(message)
				.isVersion41()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle42() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.2\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=070\u0001");

		// When/Then
		assertThat(message)
				.isVersion42()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle43() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.3\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=071\u0001");

		// When/Then
		assertThat(message)
				.isVersion43()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle44() throws Exception {
		// Given
		Message message = new Message(
				"8=FIX.4.4\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=072\u0001");

		// When/Then
		assertThat(message)
				.isVersion44()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle50() throws Exception {
		// Given
		Message message = new Message(
				"8=FIXT.1.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u00011128=7\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=215\u0001");

		// When/Then
		assertThat(message)
				.isVersion50()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle50sp1() throws Exception {
		// Given
		Message message = new Message(
				"8=FIXT.1.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u00011128=8\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=216\u0001");

		// When/Then
		assertThat(message)
				.isVersion50sp1()
				.isNewOrderSingle();
	}

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle50sp2() throws Exception {
		// Given
		Message message = new Message(
				"8=FIXT.1.1\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u00011128=9\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000159=0\u000160=20100225-19:39:52.020\u000110=217\u0001");

		// When/Then
		assertThat(message)
				.isVersion50sp2()
				.isNewOrderSingle();
	}
}
