package tech.reliab.course.zimskovma.bank.exception;

public class IncomException extends Exception {
    public IncomException() {
        super("Ошибка: доход  слишком низкий");
    }
}
