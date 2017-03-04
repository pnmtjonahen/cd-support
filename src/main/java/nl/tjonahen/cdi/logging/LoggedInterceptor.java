/*
 * Copyright (C) 2017 Philippe Tjon - A - Hen philippe@tjonahen.nl
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nl.tjonahen.cdi.logging;

import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;



/**
 *
 * @author Philippe Tjon - A - Hen
 */
@Logged
@Interceptor
public class LoggedInterceptor {

    private final static Logger LOGGER = Logger.getLogger(LoggedInterceptor.class.getName());

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ic) throws Exception {
        LOGGER.info(() -> {
            return String.format("%s", ic.getMethod().getName());
        });
        return ic.proceed();
    }
}
