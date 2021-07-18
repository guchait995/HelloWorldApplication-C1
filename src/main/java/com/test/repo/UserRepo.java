package com.test.repo;

import com.test.apiResponse.UserResponse;
import com.test.common.repo.AbstractRepo;
import com.test.model.SelfAssessment;
import com.test.model.AppUser;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class UserRepo extends AbstractRepo {
    public UserRepo(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public int insertUser(AppUser user){
        String sql="insert into user values(" +
                ":name, " +
                ":phoneNumber," +
                ":pinCode, " +
                ":isAdmin, " +
                ":isPositive " +
                ":created," +
                ":modified)";

        NativeQuery<UserResponse> query=currentSession().createNativeQuery(sql);
        query.setParameter("name", user.getName())
                .setParameter("phoneNumber", user.getPhoneNumber())
                .setParameter("pinCode", user.getPhoneNumber())
                .setParameter("isAdmin", user.getPhoneNumber())
                .setParameter("isPositive", user.getPhoneNumber())
                .setParameter("created", Calendar.getInstance().getTime())
                .setParameter("modified",  Calendar.getInstance().getTime());

        return query.executeUpdate();
    }
    public List<UserResponse> getAllUsers() {
        String sql= "select name, phoneNumber, pinCode, isAdmin, isPositive from 'user'";
        NativeQuery<UserResponse> query=currentSession().createNativeQuery(sql);
        query.addScalar("name", StandardBasicTypes.STRING)
                .addScalar("phoneNumber", StandardBasicTypes.STRING)
                .addScalar("pinCode", StandardBasicTypes.STRING)
                .addScalar("isAdmin", StandardBasicTypes.BOOLEAN)
                .addScalar("isPositive", StandardBasicTypes.STRING);

        query.setResultTransformer(Transformers.aliasToBean(UserResponse.class));
        return query.getResultList();
    }

    public Optional<Integer> getAllUsersForPinCode(String pinCode) {
        String sql= "select count(*) from user where pinCode= :pin";
        NativeQuery<Integer> query=currentSession().createNativeQuery(sql);
        query.setParameter("pin", pinCode);
        return query.uniqueResultOptional();
    }


    public int addSelfAssessment(SelfAssessment selfAssement){
        String sql="insert into selfAssement values(" +
                ":userId, " +
                ":symptomsCount," +
                ":travelHistory, " +
                ":contactWithCovidPatient, " +
                ":created," +
                ":modified)";

        NativeQuery<UserResponse> query=currentSession().createNativeQuery(sql);
        query.setParameter("name", selfAssement.getUserId())
                .setParameter("symptomsCount", selfAssement.getSymptomsCount())
                .setParameter("travelHistory", selfAssement.isTravelHistory())
                .setParameter("contactWithCovidPatient", selfAssement.isContactWithCovidPatient())
                .setParameter("created", Calendar.getInstance().getTime())
                .setParameter("modified",  Calendar.getInstance().getTime());

        return query.executeUpdate();
    }
}
