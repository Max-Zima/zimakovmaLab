package tech.reliab.course.zimskovma.bank.exception;

public class PaymentException extends RuntimeException  {
    public PaymentException() {
        super("Ошибка: недостаточно баланса.");
    }
}