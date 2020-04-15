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
package io.allune.quickfixj.api.message.version;

import static io.allune.quickfixj.api.Assertions.assertThat;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;

import quickfix.InvalidMessage;
import quickfix.Message;

/**
 * @author Simon Lewis
 */
public class QuickfixVersionAssertCustomVersionTest {

	@Test
	public void shouldAssertIsCustomVersion() throws InvalidMessage {
		Message message = new Message("8=FIX.SOME.OTHER.VERSION\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=181\u0001");
		assertThat(message).hasVersion("FIX.SOME.OTHER.VERSION");
	}

	@Test
	public void shouldFailToAssertIsCustomVersionGivenMismatch() throws InvalidMessage {
		try {
			Message message = new Message("8=FIX.SOME.OTHER.VERSION\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=181\u0001");
			assertThat(message).hasVersion("FIX.ANOTHER.OTHER.VERSION");
		} catch (AssertionError e) {
			assertThat(e).hasMessage(format("%n"
					+ "Expecting:%n"
					+ "  <8=FIX.SOME.OTHER.VERSION\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=181\u0001>%n"
					+ "to have FIX version:%n"
					+ "  <\"FIX.ANOTHER.OTHER.VERSION\">%n"
					+ "but was:%n"
					+ "  <\"FIX.SOME.OTHER.VERSION\">"));
			return;
		}
		fail("Should have thrown AssertionError");
	}
}
