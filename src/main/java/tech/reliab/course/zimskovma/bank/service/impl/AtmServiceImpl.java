package tech.reliab.course.zimskovma.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.zimskovma.bank.entity.BankAtm;
import tech.reliab.course.zimskovma.bank.service.AtmService;

public class AtmServiceImpl implements AtmService {

    @Override
    public BankAtm create(BankAtm bankAtm) {
        if (bankAtm == null) {
            return null;
        }
        if (bankAtm.getTotalMoney().signum() < 0) {
            System.err.println("Error: cannot create BankAtm - totalMoney must be non-negative");
            return null;
        }
        if (bankAtm.getMaintenanceCost().signum() < 0) {
            System.err.println("Error: cannot create BankAtm - maintenanceCost must be non-negative");
            return null;
        }
        if (bankAtm.getBankOffice() == null) {
            System.err.println("Error: cannot create BankAtm - bankOffice cannot be null");
            return null;
        }
        return new BankAtm(bankAtm);
    }

}