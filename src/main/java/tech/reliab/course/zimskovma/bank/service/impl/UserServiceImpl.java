package tech.reliab.course.zimskovma.bank.service.impl;

import java.math.BigDecimal;
import java.util.Random;

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
        final Random random = new Random();
        final double monthlyIncome = random.nextDouble() * (User.MAX_MONTHLY_INCOME);
        createdclient.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(createdclient);

        return createdclient;
    }

    @Override
    public double calculateCreditRating(User user) {
        user.setCreditRating(
                user.getMonthlyIncome() / 1000000 );
        return user.getCreditRating();
    }
}
