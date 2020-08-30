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
package io.allune.quickfixj.advertisement;

import org.junit.Test;
import quickfix.InvalidMessage;
import quickfix.Message;

import static io.allune.quickfixj.api.Assertions.assertThat;

public class Advertisement40AssertTest {

	@Test
	public void should() throws InvalidMessage {
		// Given
		Message message = new Message("8=FIX.4.0\u00019=254\u000135=7\u000134=3\u000149=ACCEPT\u000150=SSubID\u000152=20200726-11:20:01\u000156=INIT\u000157=TSubID\u000190=6\u000191=qwerty\u0001115=OBOCID\u0001116=OBOSID\u0001128=DTCID\u0001129=DTSID\u00012=1234\u00013=123123\u00014=B\u00015=N\u000115=GBP\u000122=1\u000144=12.21\u000148=SecurityID\u000153=1221\u000155=GBP\u000158=Text here\u000165=SymbolSfx\u0001106=Issuer\u0001107=SecurityDesc\u000110=110\u0001");

		// When/Then
		//@formatter:off
		assertThat(message)
				.header()
					.hasSenderCompID("ACCEPT")
					.hasTargetCompID("INIT")
					.hasOnBehalfOfCompID("OBOCID")
					.hasDeliverToCompID("DTCID")
					.hasSenderSubID("SSubID")
					.hasTargetSubID("TSubID")
					.hasOnBehalfOfSubID("OBOSID")
					.hasDeliverToSubID("DTSID");
		//@formatter:on
	}
}
