/*
 * Copyright (C) 2016 Philippe Tjon - A - Hen, philippe@tjonahen.nl
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
package nl.tjonahen.rs.application;


import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Abstract Application class defines default filtering CrossOriginheaders logging and error handling for all services
 * @author Philippe Tjon - A - Hen, philippe@tjonahen.nl
 */
public abstract class AbstractApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        // following code can be used to customize Jersey 2.0 JSON provider:
//        try {
//            resources.add(Class.forName("org.glassfish.jersey.jackson.JacksonFeature"));
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        resources.add(nl.tjonahen.rs.logging.LoggingRequestFilter.class);
        resources.add(nl.tjonahen.rs.cors.CrossOriginResourceSharingFilter.class);

        resources.add(nl.tjonahen.rs.error.UnhandledExceptionMapper.class);
        resources.add(nl.tjonahen.rs.logging.LoggingResponseFilter.class);

        addRestResourceClasses(resources);


        return resources;
    }

    protected abstract void addRestResourceClasses(Set<Class<?>> resources);
}
