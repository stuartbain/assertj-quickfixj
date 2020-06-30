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
package io.allune.quickfixj.internal;

import java.util.function.BiFunction;

import io.allune.quickfixj.exception.ThrownByLambdaException;

/**
 * A throwing {@link BiFunction}
 *
 * @param <T> parameter type of the first argument of this bifunction
 * @param <U> parameter type of the second argument of this bifunction
 * @param <R> parameter type of the return value of this bifunction
 */
@FunctionalInterface
public interface ThrowingBiFunction<T, U, R> extends BiFunction<T, U, R> {

	R doApply(T t, U u) throws Throwable;

	@Override
	default R apply(T t, U u) {
		try {
			return doApply(t, u);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable throwable) {
			throw new ThrownByLambdaException(throwable);
		}
	}
}