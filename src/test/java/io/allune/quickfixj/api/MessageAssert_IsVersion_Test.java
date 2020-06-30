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
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;

import quickfix.InvalidMessage;
import quickfix.Message;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssert_IsVersion_Test {

	@Test
	public void shouldAssertIsVersion40() throws InvalidMessage {
		Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
		assertThat(message).isVersion40();
	}

	@Test
	public void shouldFailToAssertIsVersion40GivenNullMessage() {
		try {
			assertThat((Message) null).isVersion40();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting actual not to be null"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldFailToAssertIsVersion40GivenMessageWithVersion41() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.4.1\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=016\u0001");
			assertThat(message).isVersion40();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.1\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=016\u0001>%n"
							+ "to have version:%n"
							+ " <\"FIX.4.0\">%n"
							+ "but was:%n"
							+ " <\"FIX.4.1\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldAssertIsVersion41() throws InvalidMessage {
		Message message = new Message("8=FIX.4.1\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=016\u0001");
		assertThat(message).isVersion41();
	}

	@Test
	public void shouldFailToAssertIsVersion41GivenNullMessage() {
		try {
			assertThat((Message) null).isVersion41();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting actual not to be null"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldFailToAssertIsVersion41GivenMessageWithVersion40() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
			assertThat(message).isVersion41();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>%n"
							+ "to have version:%n"
							+ " <\"FIX.4.1\">%n"
							+ "but was:%n"
							+ " <\"FIX.4.0\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldAssertIsVersion42() throws InvalidMessage {
		Message message = new Message("8=FIX.4.2\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=017\u0001");
		assertThat(message).isVersion42();
	}

	@Test
	public void shouldFailToAssertIsVersion42GivenNullMessage() {
		try {
			assertThat((Message) null).isVersion42();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting actual not to be null"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldFailToAssertIsVersion42GivenMessageWithVersion40() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
			assertThat(message).isVersion42();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>%n"
							+ "to have version:%n"
							+ " <\"FIX.4.2\">%n"
							+ "but was:%n"
							+ " <\"FIX.4.0\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldAssertIsVersion43() throws InvalidMessage {
		Message message = new Message("8=FIX.4.3\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=018\u0001");
		assertThat(message).isVersion43();
	}

	@Test
	public void shouldFailToAssertIsVersion43GivenNullMessage() {
		try {
			assertThat((Message) null).isVersion43();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting actual not to be null"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldFailToAssertIsVersion43GivenMessageWithVersion40() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
			assertThat(message).isVersion43();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>%n"
							+ "to have version:%n"
							+ " <\"FIX.4.3\">%n"
							+ "but was:%n"
							+ " <\"FIX.4.0\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldAssertIsVersion44() throws InvalidMessage {
		Message message = new Message("8=FIX.4.4\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=019\u0001");
		assertThat(message).isVersion44();
	}

	@Test
	public void shouldFailToAssertIsVersion44GivenNullMessage() {
		try {
			assertThat((Message) null).isVersion44();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting actual not to be null"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldFailToAssertIsVersion44GivenMessageWithVersion40() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
			assertThat(message).isVersion44();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>%n"
							+ "to have version:%n"
							+ " <\"FIX.4.4\">%n"
							+ "but was:%n"
							+ " <\"FIX.4.0\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldAssertIsVersion50() throws InvalidMessage {
		Message message = new Message("8=FIXT.1.1\u00019=74\u000135=2\u000149=BuySide\u000156=SellSide\u000134=4\u000152=20190605-17:09:11.495\u00011128=7\u00017=2\u000116=0\u000110=189\u0001");
		assertThat(message)
				.isVersion50();
	}

	@Test
	public void shouldFailToAssertIsVersion50GivenNullMessage() {
		try {
			assertThat((Message) null).isVersion50();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting actual not to be null"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldFailAssertIsVersion50GivenMessageWithVersion40() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
			assertThat(message).isVersion50();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>%n"
							+ "to have version:%n"
							+ " <\"FIXT.1.1\">%n"
							+ "but was:%n"
							+ " <\"FIX.4.0\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldAssertIsVersion50sp1() throws InvalidMessage {
		Message message = new Message("8=FIXT.1.1\u00019=74\u000135=2\u000149=BuySide\u000156=SellSide\u000134=4\u000152=20190605-17:09:11.495\u00011128=8\u00017=2\u000116=0\u000110=190\u0001");
		assertThat(message)
				.isVersion50sp1();
	}

	@Test
	public void shouldFailToAssertIsVersion50sp1GivenNullMessage() {
		try {
			assertThat((Message) null).isVersion50sp1();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting actual not to be null"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldFailAssertIsVersion50sp1GivenMessageWithVersion40() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
			assertThat(message).isVersion50sp1();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>%n"
							+ "to have version:%n"
							+ " <\"FIXT.1.1\">%n"
							+ "but was:%n"
							+ " <\"FIX.4.0\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldAssertIsVersion50sp2() throws InvalidMessage {
		Message message = new Message("8=FIXT.1.1\u00019=74\u000135=2\u000149=BuySide\u000156=SellSide\u000134=4\u000152=20190605-17:09:11.495\u00011128=9\u00017=2\u000116=0\u000110=191\u0001");
		assertThat(message)
				.isVersion50sp2();
	}

	@Test
	public void shouldFailToAssertIsVersion50sp2GivenNullMessage() {
		try {
			assertThat((Message) null).isVersion50sp2();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting actual not to be null"));
			return;
		}
		fail("Should have thrown AssertionError");
	}

	@Test
	public void shouldFailToAssertIsVersion50sp2GivenMessageWithVersion40() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
			assertThat(message).isVersion50sp2();
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>%n"
							+ "to have version:%n"
							+ " <\"FIXT.1.1\">%n"
							+ "but was:%n"
							+ " <\"FIX.4.0\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}
}
