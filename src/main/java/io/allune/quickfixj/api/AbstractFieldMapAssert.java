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

import org.assertj.core.api.AbstractAssert;
import quickfix.FieldMap;

public abstract class AbstractFieldMapAssert<SELF extends AbstractFieldMapAssert<SELF, ACTUAL>, ACTUAL extends FieldMap>
		extends AbstractAssert<SELF, ACTUAL> {

//	protected Objects objects = Objects.instance();
//
//	protected Failures failures = Failures.instance();
//
//	protected Messages messages = Messages.instance();
//
//	protected Versions versions = Versions.instance();

	/**
	 * Creates a new <code>{@link AbstractFieldMapAssert}</code>.
	 *
	 * @param selfType the "self type"
	 * @param actual   the actual value to verify
	 */
	protected AbstractFieldMapAssert(Class<SELF> selfType, ACTUAL actual) {
		super(actual, selfType);
	}
}
