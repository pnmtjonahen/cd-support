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
package nl.tjonahen.rs.logging;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Philippe Tjon - A - Hen
 */
@Provider
public class RequestLoggerFilter implements ContainerRequestFilter {

    private final static Logger LOGGER = Logger.getLogger(RequestLoggerFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOGGER.info(() -> {
            final StringBuilder sb = new StringBuilder();
            
            sb.append("Request:\nurl     : ").append(requestContext.getUriInfo().getAbsolutePath().getPath());
            sb.append("\nMethod  : ").append(requestContext.getRequest().getMethod());
            sb.append("\nHeaders :\n");
            requestContext.getHeaders().entrySet().forEach((e) -> {
                sb.append(e.getKey()).append(" : ").append(e.getValue().toString()).append("\n");
            });
            sb.append("\nCookies :\n");
            requestContext.getCookies().entrySet().forEach((e) -> {
                sb.append(e.getKey()).append(" : ").append(e.getValue()).append("\n");
            });
            
            return sb.toString();
        });
        
    }

}
