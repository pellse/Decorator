/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.pellse.decorator.util.function;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * @author Sebastien Pelletier
 *
 */

@FunctionalInterface
public interface CheckedBiFunction<T, U, R, E extends Throwable> extends BiFunction<T, U, R> {

	R checkedApply(T t, U u) throws E;

	@Override
	default R apply(T t, U u) {
		try {
			return checkedApply(t, u);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	default <V, E1 extends Throwable> CheckedBiFunction<T, U, V, E1> andThen(CheckedFunction<? super R, ? extends V, E1> after) {
		Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
	}

	static <T1, U1, R1, E1 extends Throwable> CheckedBiFunction<T1, U1, R1, E1> of(CheckedBiFunction<T1, U1, R1, E1> biFunction) {
		return biFunction;
	}
}
