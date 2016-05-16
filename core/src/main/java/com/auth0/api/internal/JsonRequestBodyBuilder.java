/*
 * JsonRequestBodyBuilder.java
 *
 * Copyright (c) 2015 Auth0 (http://auth0.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.auth0.api.internal;

import com.auth0.api.RequestBodyBuildException;
import com.auth0.util.moshi.MoshiObjectWriter;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.lang.reflect.Type;

/**
 * Converts a POJO to JSON stored in a {@link com.squareup.okhttp.RequestBody}
 */
abstract class JsonRequestBodyBuilder {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static <T> RequestBody createBody(T pojo, Type typeOfT, MoshiObjectWriter writer) throws RequestBodyBuildException {
        try {
            return RequestBody.create(JSON, writer.writeValueAsBytes(pojo,typeOfT));
        } catch (Exception e) {
            throw new RequestBodyBuildException("Failed to convert " + pojo.getClass().getName() + " to JSON", e);
        }
    }
}