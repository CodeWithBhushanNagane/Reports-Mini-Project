package com.reports.runner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.reports.dao.EligibilityDetailsDao;
import com.reports.entity.EligibilityDetails;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	EligibilityDetailsDao eligibilityDetailsDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		EligibilityDetails e1 = new EligibilityDetails();
        e1.setEligibilityId(1);
        e1.setName("Alice Johnson");
        e1.setMobileNo(9876543210L);
        e1.setEmail("alice@example.com");
        e1.setGender('F');
        e1.setSsn(123456789012L);
        e1.setPlanName("Health Basic");
        e1.setPlanStatus("Active");
        e1.setPlanStartDate(LocalDate.of(2024, 1, 1));
        e1.setPlanEndDate(LocalDate.of(2024, 12, 31));
        e1.setCreateDate(LocalDate.of(2024, 1, 1));
        e1.setUpdateDate(LocalDate.of(2024, 6, 1));
        e1.setCreatedBy("admin");
        e1.setUpdatedBy("admin");
        eligibilityDetailsDao.save(e1);

        EligibilityDetails e2 = new EligibilityDetails();
        e2.setEligibilityId(2);
        e2.setName("Bob Smith");
        e2.setMobileNo(9123456780L);
        e2.setEmail("bob@example.com");
        e2.setGender('M');
        e2.setSsn(987654321098L);
        e2.setPlanName("Premium Care");
        e2.setPlanStatus("Inactive");
        e2.setPlanStartDate(LocalDate.of(2023, 3, 1));
        e2.setPlanEndDate(LocalDate.of(2024, 3, 1));
        e2.setCreateDate(LocalDate.of(2023, 2, 28));
        e2.setUpdateDate(LocalDate.of(2024, 2, 28));
        e2.setCreatedBy("system");
        e2.setUpdatedBy("bob_admin");
        eligibilityDetailsDao.save(e2);

        EligibilityDetails e3 = new EligibilityDetails();
        e3.setEligibilityId(3);
        e3.setName("Carol White");
        e3.setMobileNo(9012345678L);
        e3.setEmail("carol@example.com");
        e3.setGender('F');
        e3.setSsn(112233445566L);
        e3.setPlanName("Family Plus");
        e3.setPlanStatus("Pending");
        e3.setPlanStartDate(LocalDate.of(2024, 5, 15));
        e3.setPlanEndDate(LocalDate.of(2025, 5, 14));
        e3.setCreateDate(LocalDate.of(2024, 5, 10));
        e3.setUpdateDate(LocalDate.of(2024, 5, 11));
        e3.setCreatedBy("carol_admin");
        e3.setUpdatedBy("carol_admin");
        eligibilityDetailsDao.save(e3);

        EligibilityDetails e4 = new EligibilityDetails();
        e4.setEligibilityId(4);
        e4.setName("David Lee");
        e4.setMobileNo(9988776655L);
        e4.setEmail("david@example.com");
        e4.setGender('M');
        e4.setSsn(667788990011L);
        e4.setPlanName("Essential Health");
        e4.setPlanStatus("Active");
        e4.setPlanStartDate(LocalDate.of(2024, 7, 1));
        e4.setPlanEndDate(LocalDate.of(2025, 6, 30));
        e4.setCreateDate(LocalDate.of(2024, 6, 20));
        e4.setUpdateDate(LocalDate.of(2024, 7, 5));
        e4.setCreatedBy("admin");
        e4.setUpdatedBy("system");
        eligibilityDetailsDao.save(e4);
		
	}
	
	
}
