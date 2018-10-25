package hu.elte.szakdolgozat.spms.service.impl;

import hu.elte.szakdolgozat.spms.model.entity.Company;
import hu.elte.szakdolgozat.spms.model.entity.Product;
import hu.elte.szakdolgozat.spms.repository.CompanyRepository;
import hu.elte.szakdolgozat.spms.service.CompanyAndOrderDataImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MockCompanyAndOrderDataImporter implements CompanyAndOrderDataImporter {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public void importByRange(Date fromDate, Date toDate) {


    }

    private List<Product> createProducts() {
        List<Product> prodList = new ArrayList<>();

    }
}
