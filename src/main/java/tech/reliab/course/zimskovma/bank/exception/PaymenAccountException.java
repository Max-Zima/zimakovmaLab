package tech.reliab.course.zimskovma.bank.exception;

public class PaymenAccountException extends Exception {
    public PaymenAccountException() {
        super("Ошибка: нет платежного счета.");
    }
}