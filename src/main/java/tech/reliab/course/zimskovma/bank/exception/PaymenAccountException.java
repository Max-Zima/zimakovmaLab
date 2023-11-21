package tech.reliab.course.zimskovma.bank.exception;

public class PaymenAccountException extends RuntimeException  {
    public PaymenAccountException() {
        super("Ошибка: нет платежного счета.");
    }
}