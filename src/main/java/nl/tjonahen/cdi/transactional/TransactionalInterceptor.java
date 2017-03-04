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
package nl.tjonahen.cdi.transactional;

import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


/**
 *
 * @author Philippe Tjon - A - Hen, philippe@tjonahen.nl
 */
@Transactional
@Interceptor
public class TransactionalInterceptor {

    private final static Logger LOGGER = Logger.getLogger(TransactionalInterceptor.class.getName());
    
    @PersistenceContext
    private EntityManager entityManager;

    @AroundInvoke
    public Object performTransacation(InvocationContext invocationContext)
            throws Exception {
        LOGGER.fine("Start transaction.. ");
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            final Object result = invocationContext.proceed();
            LOGGER.fine("Commit transaction..");
            entityManager.flush();
            transaction.commit();
            return result;

        } catch (Exception e) {
            LOGGER.fine("Rollback transaction..");
            transaction.rollback();
            throw e;
        }
    }
}
