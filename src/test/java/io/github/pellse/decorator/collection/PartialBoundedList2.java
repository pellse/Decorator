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
package io.github.pellse.decorator.collection;

import java.util.List;

import javax.inject.Inject;

public abstract class PartialBoundedList2<E> implements List<E> {

	private final int maxNbItems;

	@Inject
	protected List<E> delegate;

	public PartialBoundedList2(int maxNbItems) {
		this.maxNbItems = maxNbItems;
	}

	@Override
	public boolean add(E e) {
		checkSize(1);
		return delegate.add(e);
	}

	@Override
	public void add(int index, E element) {
		checkSize(1);
		delegate.add(index, element);
	}

	protected void checkSize(int addCount) {
		if (delegate.size() + addCount >= maxNbItems)
			throw new IllegalStateException("Size of list (" + delegate.size() + ") greater than maxNbItems (" + maxNbItems + ")");
	}
}
