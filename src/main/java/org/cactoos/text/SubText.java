/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2018 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.text;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Extract a substring from a Text.
 *
 * <p>There is no thread-safety guarantee.
 * @since 0.11
 */
public final class SubText extends TextEnvelope {

    /**
     * Ctor.
     * @param text The String
     * @param strt Start position in the text
     */
    public SubText(final String text, final int strt) {
        this(new TextOf(text), strt);
    }

    /**
     * Ctor.
     * @param text The String
     * @param strt Start position in the text
     * @param finish End position in the text
     */
    public SubText(final String text, final int strt, final int finish) {
        this(new TextOf(text), strt, finish);
    }

    /**
     * Ctor.
     * @param text The Text
     * @param strt Start position in the text
     */
    public SubText(final Text text, final int strt) {
        this(text, () -> strt, () -> text.asString().length());
    }

    /**
     * Ctor.
     * @param text The Text
     * @param strt Start position in the text
     * @param finish End position in the text
     */
    public SubText(final Text text, final int strt, final int finish) {
        this(text, () -> strt, () -> finish);
    }

    /**
     * Ctor.
     * @param text The Text
     * @param strt Start position in the text
     * @param finish End position in the text
     */
    public SubText(final Text text, final Scalar<Integer> strt,
        final Scalar<Integer> finish) {
        this(text, new UncheckedScalar<>(strt), new UncheckedScalar<>(finish));
    }

    /**
     * Ctor.
     * @param text The Text
     * @param start Start position in the text
     * @param end End position in the text
     */
    @SuppressWarnings({"PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors"})
    public SubText(final Text text, final UncheckedScalar<Integer> start,
        final UncheckedScalar<Integer> end) {
        super((Scalar<String>) () -> {
            int begin = start.value();
            if (begin < 0) {
                begin = 0;
            }
            int finish = end.value();
            final String origin = text.asString();
            if (origin.length() < finish) {
                finish = origin.length();
            }
            return origin.substring(begin, finish);
        });
    }
}
