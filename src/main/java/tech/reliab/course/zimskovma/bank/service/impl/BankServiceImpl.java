package tech.reliab.course.zimskovma.bank.service.impl;

import java.math.BigDecimal;
import java.util.Random;

import tech.reliab.course.zimskovma.bank.entity.Bank;
import tech.reliab.course.zimskovma.bank.service.BankService;


public class BankServiceImpl implements BankService {

    @Override
    public Bank create(Bank bank) {
        if (bank == null) {
            return null;
        }

        Bank newBank = new Bank(bank.getId(), bank.getName());

        final Random random = new Random();

        newBank.setRating((byte) random.nextInt(Bank.MAX_RATING.intValue() + 1));
        BigDecimal rand = BigDecimal.valueOf(Math.random());
        newBank.setTotalMoney(rand.multiply(Bank.MAX_TOTAL_MONEY));
        calculateInterestRate(newBank);

        return newBank;
    }

    @Override
    public BigDecimal calculateInterestRate(Bank bank) {
        if (bank != null) {
            final BigDecimal rating = BigDecimal.valueOf(bank.getRating());

            BigDecimal rand_1 = BigDecimal.valueOf(Math.random());
            final BigDecimal centralBankInterestRate = rand_1.multiply(Bank.MAX_INTEREST_RATE);
            final BigDecimal maxBankInterestRateMargin = Bank.MAX_INTEREST_RATE.subtract(centralBankInterestRate);

            BigDecimal rand_2 = BigDecimal.valueOf(Math.random());
            final BigDecimal bankInterestRateMargin = rand_2.multiply(maxBankInterestRateMargin)
                    .multiply((new BigDecimal("110").subtract(rating).divide(new BigDecimal("100"))));
            final BigDecimal interestRate = centralBankInterestRate.add(bankInterestRateMargin);

            bank.setInterestRate(interestRate);
            return interestRate;
        }
        return new BigDecimal("0");
    }
}
