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
import quickfix.Message;

import static io.allune.quickfixj.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MessageAssert_hasBodyLength_Test {

	@Test
	public void shouldAssertHasBodyLength() throws Exception {
		Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");
		assertThat(message)
				.hasBodyLength(61);
	}

	@Test
	public void shouldFailToAssertHasBodyLength() throws Exception {
		// Given
		Message message = new Message("8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001");

		// When
		assertThatThrownBy(() -> assertThat(message).hasBodyLength(60))
				.isInstanceOf(AssertionError.class)
				.hasMessage("Expecting value for field <quickfix.field.BodyLength> (tag=<9>) in Message:\n"
						+ " <8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001>\n"
						+ "to be:\n"
						+ " <60>\n"
						+ "but was:\n"
						+ " <61>");
	}
}
