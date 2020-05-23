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
package io.allune.quickfixj.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * @author Eduardo Sanchez-Ros
 */
public class ShouldBeValidVersion extends BasicErrorMessageFactory {

	public static ErrorMessageFactory shouldBeValidVersion(Object actual) {
		return new ShouldBeValidVersion(actual);
	}

	private ShouldBeValidVersion(Object actual) {
		super("%n"
				+ "Expecting Message version to be one of:%n"
				+ " <%s>%n"
				+ "but was:%n"
				+ " <%s>", "", actual);
	}
}
