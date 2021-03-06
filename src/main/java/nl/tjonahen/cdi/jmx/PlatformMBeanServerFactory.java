/*
 * Copyright (C) 2017 ordina
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

package nl.tjonahen.cdi.jmx;

import java.lang.management.ManagementFactory;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.management.MBeanServer;

/**
 * Factory for Platform MBeanServer. As CDI is not part of SE there is no need for a default platform mbeanserver within the JDK ?
 * 
 * @author Philippe Tjon - A - Hen
 */
@Singleton
public class PlatformMBeanServerFactory {

    @Produces
    public MBeanServer getPlatformMBeanServer() {
        return ManagementFactory.getPlatformMBeanServer();
    }
}
