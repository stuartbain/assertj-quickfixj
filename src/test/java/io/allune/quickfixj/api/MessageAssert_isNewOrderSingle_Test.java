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

import static io.allune.quickfixj.api.Assertions.assertThat;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author Eduardo Sanchez-Ros
 */
public class MessageAssert_isNewOrderSingle_Test {

	@Test
	public void shouldAssertMessageTypeIsNewOrderSingle() throws InvalidMessage {
		// Given
		Message message = new Message("8=FIX.4.0\u00019=112\u000135=D\u000134=1\u000149=BANZAI\u000152=20200727-12:28:02.659\u000156=EXEC\u00011=Marcel\u000111=13346\u000121=1\u000138=1000\u000140=2\u000144=300\u000154=1\u000155=GBP/USD\u000110=018\u0001");

		// When/Then
		assertThat(message)
				.isNewOrderSingle();
	}

	@Test
	public void shouldFailToAssertIsNewOrderSingleGivenMessageIsAdvertisement() throws InvalidMessage {
		try {
			// Given
			Message message = new Message("8=FIX.4.0\u00019=254\u000135=7\u000134=3\u000149=ACCEPT\u000150=SSubID\u000152=20200726-11:20:01\u000156=INIT\u000157=TSubID\u000190=6\u000191=qwerty\u0001115=OBOCID\u0001116=OBOSID\u0001128=DTCID\u0001129=DTSID\u00012=1234\u00013=123123\u00014=B\u00015=N\u000115=GBP\u000122=1\u000144=12.21\u000148=SecurityID\u000153=1221\u000155=GBP\u000158=Text here\u000165=SymbolSfx\u0001106=Issuer\u0001107=SecurityDesc\u000110=110\u0001");

			// When
			assertThat(message)
					.isNewOrderSingle();
		} // Then
		catch (AssertionError e) {
			org.assertj.core.api.Assertions.assertThat(e).hasMessage(format(
					"Expecting Message:%n"
							+ " <8=FIX.4.0\u00019=254\u000135=7\u000134=3\u000149=ACCEPT\u000150=SSubID\u000152=20200726-11:20:01\u000156=INIT\u000157=TSubID\u000190=6\u0001115=OBOCID\u0001116=OBOSID\u0001128=DTCID\u0001129=DTSID\u00012=1234\u00013=123123\u00014=B\u00015=N\u000115=GBP\u000122=1\u000144=12.21\u000148=SecurityID\u000153=1221\u000155=GBP\u000158=Text here\u000165=SymbolSfx\u000191=qwerty\u0001106=Issuer\u0001107=SecurityDesc\u000110=110\u0001>%n"
							+ "to be of type <\"D\">%n"
							+ "but was:%n"
							+ " <\"7\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}
}
