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

import io.allune.quickfixj.internal.Messages;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import quickfix.FieldMap;

import static io.allune.quickfixj.error.FieldShouldHaveValue.fieldShouldHaveValue;
import static io.allune.quickfixj.error.ShouldHaveField.shouldHaveField;
import static org.assertj.core.util.Preconditions.checkArgument;

/**
 * @param <SELF>
 * @param <ACTUAL>
 * @author Eduardo Sanchez-Ros
 */
public abstract class AbstractFieldMapAssert<SELF extends AbstractFieldMapAssert<SELF, ACTUAL>, ACTUAL extends FieldMap>
		extends AbstractAssert<SELF, ACTUAL> {

	//	protected Objects objects = Objects.instance();
//
	protected Failures failures = Failures.instance();
	//
	protected Messages messages = Messages.instance();
//
//	protected Versions versions = Versions.instance();

	/**
	 * Creates a new <code>{@link AbstractFieldMapAssert}</code>.
	 *
	 * @param actual   the actual value to verify
	 * @param selfType the "self type"
	 */
	protected AbstractFieldMapAssert(ACTUAL actual, Class<SELF> selfType) {
		super(actual, selfType);
	}

	public SELF hasField(int expectedTag) {
		isNotNull();
		if (!actual.isSetField(expectedTag))
			throw failures.failure(info, shouldHaveField(actual, expectedTag));
		return (SELF) this;
	}

	public SELF hasFields(int... expectedFieldTags) {
		// TODO: Iterate through all fields, gather the errors and custom error message
		isNotNull();
		for (int field : expectedFieldTags) {
			hasField(field);
		}
		return (SELF) this;
	}

	// TODO: Rename to containsField(fieldTag, expectedValue)
	public SELF hasFieldValue(int expectedFieldTag, Object expectedFieldValue) {
		isNotNull();
		//		    Objects.instance().assertNotNull(info, actual);
		checkArgument(expectedFieldTag > 0, "'expectedFieldTag' must be greater than 0.");
		checkArgument(expectedFieldValue != null, "'expectedFieldValue' must not be null.");

		hasField(expectedFieldTag);

		try {
			Object actualFieldValue = messages.getFieldValue(expectedFieldTag, getBeginString(), this.actual);
			if (!expectedFieldValue.equals(actualFieldValue))
				throw failures.failure(info, fieldShouldHaveValue(actual, expectedFieldTag, actualFieldValue, expectedFieldValue));
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}

		return (SELF) this;
	}

	public abstract String getBeginString();
}
