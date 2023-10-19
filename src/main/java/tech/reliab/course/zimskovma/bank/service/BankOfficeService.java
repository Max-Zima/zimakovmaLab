package tech.reliab.course.zimskovma.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.zimskovma.bank.entity.BankAtm;
import tech.reliab.course.zimskovma.bank.entity.BankOffice;
import tech.reliab.course.zimskovma.bank.entity.Employee;

public interface BankOfficeService {
    BankOffice create(BankOffice bankOffice);

}
