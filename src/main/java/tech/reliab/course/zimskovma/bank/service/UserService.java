package tech.reliab.course.zimskovma.bank.service;


import java.math.BigDecimal;

import tech.reliab.course.zimskovma.bank.entity.User;

public interface UserService {
    User create(User user);

    double calculateCreditRating(User user);
}
