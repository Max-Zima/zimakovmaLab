package tech.reliab.course.zimskovma.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.zimskovma.bank.entity.User;
import tech.reliab.course.zimskovma.bank.service.UserService;


public class UserServiceImpl implements UserService {

    @Override
    public User create(User user) {
        if (user == null) {
            return null;
        }

        if (user.getBank() == null) {
            System.err.println("Error: User doesn't have bank");
            return null;
        }

        User createdclient = new User(user);
        BigDecimal random = BigDecimal.valueOf(Math.random());
        final BigDecimal monthlyIncome = random.multiply(User.MAX_MONTHLY_INCOME);
        createdclient.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(createdclient);

        return createdclient;
    }

    @Override
    public BigDecimal calculateCreditRating(User user) {
        user.setCreditRating(
                user.getMonthlyIncome().divide(new BigDecimal("1000").multiply(new BigDecimal("100"))));
        return user.getCreditRating();
    }
}
