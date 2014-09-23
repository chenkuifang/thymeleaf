/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package org.thymeleaf.engine.markup.handler;

/**
 *
 * @author Daniel Fern&aacute;ndez
 * @since 3.0.0
 *
 */
final class MarkupSelectorAndItem implements IMarkupSelectorItem {

    final IMarkupSelectorItem left;
    final IMarkupSelectorItem right;


    MarkupSelectorAndItem(final IMarkupSelectorItem left, final IMarkupSelectorItem right) {
        super();
        if ((right.anyLevel() && !left.anyLevel()) || (!right.anyLevel() && left.anyLevel())) {
            throw new IllegalArgumentException("Left and right items must have the same value for ''anyLevel': " + left.toString() + " && " + right.toString());
        }
        this.left = left;
        this.right = right;
    }

    public boolean anyLevel() {
        // left and right should be equal
        return this.left.anyLevel();
    }

    public boolean matchesText() {
        return this.left.matchesText() && this.right.matchesText();
    }

    public boolean matches(final int markupBlockIndex, final ElementBuffer elementBuffer, final MarkupSelectorFilter.MarkupBlockMatchingCounter markupBlockMatchingCounter) {
        return this.left.matches(markupBlockIndex, elementBuffer, markupBlockMatchingCounter) &&
                this.right.matches(markupBlockIndex, elementBuffer, markupBlockMatchingCounter);
    }

    public String toString() {
        return "(" + this.left.toString() + " && " + this.right + ")";
    }

}