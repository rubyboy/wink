/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  
 *   http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *  
 *******************************************************************************/
package org.apache.wink.common.internal.providers.header;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate;

import org.apache.wink.common.internal.i18n.Messages;

public class NewCookieHeaderDelegate implements HeaderDelegate<NewCookie> {

    public NewCookie fromString(String cookie) throws IllegalArgumentException {
        if (cookie == null) {
            throw new IllegalArgumentException(Messages.getMessage("cookieIsNull")); //$NON-NLS-1$
        }

        String tokens[] = cookie.split(";"); //$NON-NLS-1$
        ModifiableCookie modifiableCookie = null;
        for (String token : tokens) {
            String[] subTokens = token.split("=", 2); //$NON-NLS-1$
            String name = subTokens.length > 0 ? subTokens[0] : null;
            String value = subTokens.length > 1 ? subTokens[1] : null;
            if (value != null && value.startsWith("\"") //$NON-NLS-1$
                && value.endsWith("\"") //$NON-NLS-1$
                && value.length() > 1) {
                value = value.substring(1, value.length() - 1);
            }

            // Create new NewCookie
            if (modifiableCookie == null) {
                if (name == null) {
                    throw new IllegalArgumentException(Messages.getMessage("cookieNameNotValid", name)); //$NON-NLS-1$
                }
                if (value == null) {
                    throw new IllegalArgumentException(
                                                       Messages.getMessage("cookieNameValueNotValid", value)); //$NON-NLS-1$
                }
                modifiableCookie = new ModifiableCookie();
                modifiableCookie.name = name;
                modifiableCookie.value = value;
            } else if (name.trim().startsWith("Version")) { //$NON-NLS-1$
                modifiableCookie.version = Integer.parseInt(value);
            } else if (name.trim().startsWith("Path")) { //$NON-NLS-1$
                modifiableCookie.path = value;
            } else if (name.trim().startsWith("Domain")) { //$NON-NLS-1$
                modifiableCookie.domain = value;
            } else if (name.trim().startsWith("Comment")) { //$NON-NLS-1$
                modifiableCookie.comment = value;
            } else if (name.trim().startsWith("Max-Age")) { //$NON-NLS-1$
                modifiableCookie.maxAge = Integer.parseInt(value);
            } else if (name.trim().startsWith("Secure")) { //$NON-NLS-1$
                modifiableCookie.secure = true;
            }
        }

        return buildNewCookie(modifiableCookie);

    }

    private NewCookie buildNewCookie(ModifiableCookie modifiableCookie) {
        NewCookie newCookie =
            new NewCookie(modifiableCookie.name, modifiableCookie.value, modifiableCookie.path,
                          modifiableCookie.domain, modifiableCookie.comment,
                          modifiableCookie.maxAge, modifiableCookie.secure);
        return newCookie;
    }

    public String toString(NewCookie cookie) {
        if (cookie == null) {
            throw new IllegalArgumentException(Messages.getMessage("cookieIsNull")); //$NON-NLS-1$
        }
        return buildCookie(cookie.getName(), cookie.getValue(), cookie.getPath(), cookie
            .getDomain(), cookie.getVersion(), cookie.getComment(), cookie.getMaxAge(), cookie
            .isSecure());
    }

    private String buildCookie(String name,
                               String value,
                               String path,
                               String domain,
                               int version,
                               String comment,
                               int maxAge,
                               boolean secure) {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("="); //$NON-NLS-1$
        if (value == null || value.length() == 0)
            sb.append("\"\""); //$NON-NLS-1$
        else
            sb.append(value);

        // Path=path
        sb.append(";").append("Version=").append(version); //$NON-NLS-1$ //$NON-NLS-2$
        if (path != null)
            sb.append(";Path=").append(path); //$NON-NLS-1$
        if (domain != null)
            sb.append(";Domain=").append(domain); //$NON-NLS-1$
        if (comment != null)
            sb.append(";Comment=").append(comment); //$NON-NLS-1$
        if (maxAge >= 0) {
            sb.append(";Max-Age=").append(maxAge); //$NON-NLS-1$
        }
        if (secure)
            sb.append(";Secure"); //$NON-NLS-1$

        return sb.toString();
    }

    private static class ModifiableCookie {
        public String  name;
        public String  value;
        public int     version = Cookie.DEFAULT_VERSION;
        public String  path;
        public String  domain;
        public String  comment = null;
        public int     maxAge  = NewCookie.DEFAULT_MAX_AGE;
        public boolean secure  = false;
    }
}
