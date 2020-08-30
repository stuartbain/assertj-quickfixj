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
public class FieldShouldHaveValue extends BasicErrorMessageFactory {

	public static final String FIELD_SHOULD_HAVE_VALUE =
			"Expecting field <%s> with tag <%s> in Message:%n"
					+ " <%s>%n"
					+ "to have value:%n"
					+ " <%s>%n"
					+ "but was:%n"
					+ " <%s>";

	public static final String FIELD_SHOULD_HAVE_VALUE_WITHOUT_CLASS =
			"Expecting field with tag <%s> in Message:%n"
					+ " <%s>%n"
					+ "to have value:%n"
					+ " <%s>%n"
					+ "but was:%n"
					+ " <%s>";

	private FieldShouldHaveValue(Object message, Object fieldClass, Object fieldTag, Object actualValue, Object expectedValue) {
		super(FIELD_SHOULD_HAVE_VALUE, fieldClass, fieldTag, message, expectedValue, actualValue);
	}

	private FieldShouldHaveValue(Object message, Object fieldTag, Object actualValue, Object expectedValue) {
		super(FIELD_SHOULD_HAVE_VALUE_WITHOUT_CLASS, fieldTag, message, expectedValue, actualValue);
	}

	public static ErrorMessageFactory fieldShouldHaveValue(Object message, Object fieldClass, Object fieldTag, Object actualValue, Object expectedValue) {
		return new FieldShouldHaveValue(message, fieldClass, fieldTag, actualValue, expectedValue);
	}

	public static ErrorMessageFactory fieldShouldHaveValue(Object message, Object fieldTag, Object actual, Object expected) {
		return new FieldShouldHaveValue(message, fieldTag, actual, expected);
	}
}
