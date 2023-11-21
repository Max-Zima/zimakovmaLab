package tech.reliab.course.zimskovma.bank.exception;

public class PaymentException extends Exception {
    public PaymentException() {
        super("Ошибка: недостаточно баланса.");
    }
}