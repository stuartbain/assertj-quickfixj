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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for <code>{@link Assertions#assertThat(Message)}</code>.
 *
 * @author Eduardo Sanchez-Ros
 */
public class Assertions_assertThat_Message_Test {

	@Test
	public void should_create_MessageAssert() {
		// Given
		Message message = new Message();

		// When
		MessageAssert messageAssert = Assertions.assertThat(message);

		// Then
		assertThat(messageAssert).isNotNull();
		assertThat(messageAssert.getClass()).isEqualTo(MessageAssert.class);
	}

	@Test
	public void should_pass_actual() {
		// Given
		Message message = new Message();

		// When
		Message actual = Assertions.assertThat(message).getActual();

		// Then
		assertThat(actual).isEqualTo(message);
	}
}
