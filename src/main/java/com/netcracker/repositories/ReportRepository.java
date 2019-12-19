package com.netcracker.repositories;

import com.netcracker.hibernate.EntityManagerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.netcracker.entities.Report;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.EntityManager;

@Repository
public class ReportRepository extends AbstractRepository<Report> {


    public ReportRepository() {
        super(Report.class);
    }

    /*public List<Report> findAllReportOfModerator(Long moderatorId) {

        EntityManager entityManager = EntityManagerConfiguration.getInstance().getEntityManager();

        String sql = "SELECT * FROM Report WHERE Moderator_ID = " + moderatorId + " ; ";
        List<Report> reports = entityManager.createNativeQuery(
                sql, Report.class )
                .getResultList();

        return reports;
    }*/
}
