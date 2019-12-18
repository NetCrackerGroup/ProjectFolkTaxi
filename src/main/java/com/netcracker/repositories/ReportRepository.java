package com.netcracker.repositories;

import com.netcracker.entities.Report;
import com.netcracker.hibernate.EntityManagerConfiguration;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ReportRepository extends AbstractRepository<Report> {


    public ReportRepository() {
        super(Report.class);
    }

    public List<Report> findAllReportOfModerator(Long moderatorId) {

        EntityManager entityManager = EntityManagerConfiguration.getInstance().getEntityManager();

        String sql = "SELECT * FROM Report WHERE Moderator_ID = " + moderatorId + " ; ";
        List<Report> reports = entityManager.createNativeQuery(
                sql, Report.class )
                .getResultList();

        return reports;
    }
}
