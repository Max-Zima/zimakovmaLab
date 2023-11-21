package tech.reliab.course.zimskovma.bank.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.zimskovma.bank.entity.BankAtm;
import tech.reliab.course.zimskovma.bank.service.AtmService;
import tech.reliab.course.zimskovma.bank.service.BankOfficeService;

public class AtmServiceImpl implements AtmService {

    Map<Integer, BankAtm> atmTable = new HashMap<Integer, BankAtm>();

    private final BankOfficeService bankOfficeService;

    public AtmServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public List<BankAtm> getAllBankAtms() {
        return new ArrayList<>(atmTable.values());
    }

    @Override
    public BankAtm getBankAtmById(int id) {
        BankAtm atm = atmTable.get(id);
        if (atm == null) {
            System.err.println("Банкомат не найден");
        }
        return atm;
    }

    @Override
    public BankAtm create(BankAtm bankAtm) {
        if (bankAtm == null) {
            return null;
        }
        if (bankAtm.getTotalMoney() < 0) {
            System.err.println("Error: cannot create BankAtm - totalMoney doesn't be negative");
            return null;
        }
        if (bankAtm.getMaintenanceCost() < 0) {
            System.err.println("Error: cannot create BankAtm - maintenanceCost doesn't be negative");
            return null;
        }
        if (bankAtm.getBankOffice() == null) {
            System.err.println("Error: cannot create BankAtm - bankOffice can't be null");
            return null;
        }

        BankAtm newAtm = new BankAtm(bankAtm);
        atmTable.put(newAtm.getId(), newAtm);
        bankOfficeService.addAtm(newAtm.getBankOffice().getId(), newAtm);

        return newAtm;
    }

    @Override
    public boolean isAtmSuitable(BankAtm bankAtm, double money) {
        return bankAtm.getTotalMoney() >= money;
    }

    @Override
    public boolean addMoney(BankAtm bankAtm, double amount) {
        bankAtm.setTotalMoney(bankAtm.getTotalMoney() + amount);
        return true;
    }

}