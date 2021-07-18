package com.test.repo;

import com.test.common.repo.AbstractRepo;
import com.test.model.Employee;
import com.test.apiResponse.EmployeeResponse;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import java.util.List;

public class EmployeeRepo extends AbstractRepo<Employee> {
    public EmployeeRepo(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public List<EmployeeResponse> getAllEmployees(){
        String sql="select id, name, department, date(created) as created, date(modified) as modified from employee";
        NativeQuery<EmployeeResponse> query = currentSession().createSQLQuery(sql);
        query.addScalar("id",StandardBasicTypes.LONG);
        query.addScalar("name", StandardBasicTypes.STRING);
        query.addScalar("department", StandardBasicTypes.STRING);
        query.addScalar("created", StandardBasicTypes.DATE);
        query.addScalar("modified", StandardBasicTypes.DATE);
        query.setResultTransformer(Transformers.aliasToBean(EmployeeResponse.class));
        return query.getResultList();
//        query.setParameter("name", name);
//        query.addEntity(Series.class);
//        return Optional.ofNullable((Series) query.uniqueResult());

    }
}
