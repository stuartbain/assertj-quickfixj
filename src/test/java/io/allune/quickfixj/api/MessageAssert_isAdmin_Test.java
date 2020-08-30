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

public class MessageAssert_isAdmin_Test {

	@Test
	public void shouldAssertMessageTypeIsLogon40() throws InvalidMessage {
		// Given
		Message message = new Message("8=FIX.4.0\u00019=62\u000135=A\u000134=10\u000149=INIT\u000152=20200727-10:20:35\u000156=ACCEPT\u000198=0\u0001108=30\u000110=063\u0001");

		// When/Then
		assertThat(message)
				.isLogon();
	}
}
