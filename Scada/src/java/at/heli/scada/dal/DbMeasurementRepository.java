/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.dal;

import at.heli.scada.dal.interfaces.MeasurementRepository;
import at.heli.scada.entities.InstallationState;
import at.heli.scada.entities.Statistic;
import at.heli.scada.dal.interfaces.DalException;
import at.heli.scada.entities.Installation;
import at.heli.scada.entities.Measurement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author daniel
 */
@Stateless
@Alternative
public class DbMeasurementRepository implements MeasurementRepository {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Measurement entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Measurement entity) {
        em.remove(entity);
    }

    @Override
    public Measurement getById(int id) {
        return em.find(Measurement.class, id);
    }

    @Override
    public List<Measurement> getAll() {
        Query q = em.createNamedQuery("Measurement.findAll");
        return q.getResultList();
    }

    @Override
    public List<Statistic> getPerDay(Installation i, Date date) throws DalException {
        List<Statistic> tmp;
        try
        {
            TypedQuery<Statistic> q = em.createQuery("SELECT NEW at.heli.scada.entities.Statistic(AVG(m.measurevalue), MAX(m.measurevalue), MIN(m.measurevalue), mt.unit, mt.description) "
                    + "FROM MeasurementType mt INNER JOIN mt.measurementList m "
                    + "WHERE m.measuredate = :measuredate AND m.installationid = :installationid "
                    + "GROUP BY m.typeid, mt.unit, mt.description", Statistic.class);
            q.setParameter("installationid", i);
            q.setParameter("measuredate", date);
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch measurements per day!", err);
        }
        return tmp;
    }

    @Override
    public List<Statistic> getPerMonth(Installation i, Date date) throws DalException {
        List<Statistic> tmp;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.DAY_OF_MONTH, 1);
        
        Date first = cal.getTime();
        
        cal.setTime(date);
        cal.add(Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        
        Date last = cal.getTime();
        try
        {
            TypedQuery<Statistic> q = em.createQuery("SELECT NEW at.heli.scada.entities.Statistic(AVG(m.measurevalue), MAX(m.measurevalue), MIN(m.measurevalue), mt.unit, mt.description) "
                    + "FROM MeasurementType mt INNER JOIN mt.measurementList m "
                    + "WHERE m.measuredate >= :first AND m.measuredate <= :last AND m.installationid = :installationid "
                    + "GROUP BY m.typeid, mt.unit, mt.description", Statistic.class);
            q.setParameter("installationid", i);
            q.setParameter("first", first);
            q.setParameter("last", last);
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch measurements per month!", err);
        }
        return tmp;
    }

    @Override
    public List<Statistic> getPerYear(Installation i, Date date) throws DalException {
        List<Statistic> tmp;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.DAY_OF_YEAR, 1);
        
        Date first = cal.getTime();
        
        cal.setTime(date);
        cal.add(Calendar.YEAR,1);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.add(Calendar.DATE, -1);
        
        Date last = cal.getTime();
        try
        {
            TypedQuery<Statistic> q = em.createQuery("SELECT NEW at.heli.scada.entities.Statistic(AVG(m.measurevalue), MAX(m.measurevalue), MIN(m.measurevalue), mt.unit, mt.description) "
                    + "FROM MeasurementType mt INNER JOIN mt.measurementList m "
                    + "WHERE m.measuredate >= :first AND m.measuredate <= :last AND m.installationid = :installationid "
                    + "GROUP BY m.typeid, mt.unit, mt.description", Statistic.class);
            q.setParameter("installationid", i);
            q.setParameter("first", first);
            q.setParameter("last", last);
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch measurements per month!", err);
        }
        return tmp;
    }

    @Override
    public List<Measurement> getCurrentValues(Installation i) throws DalException {
        List<Measurement> tmp;
        
        try
        {
            
            Query q = em.createQuery(""
                    + "SELECT DISTINCT m "
                    + "FROM Measurement m "
                    + "WHERE m.installationid = :installationid "
                    + "AND m.measuredate = (SELECT MAX(n.measuredate) FROM Measurement n WHERE n.typeid = m.typeid ORDERBY n.measuretime) "
                    + "");
            
            q.setParameter("installationid", i);
            tmp = q.getResultList();
        }
        catch(Exception err)
        {
            throw new DalException("cannot fetch measurements per month!", err);
        }
        return tmp;
    }

}
