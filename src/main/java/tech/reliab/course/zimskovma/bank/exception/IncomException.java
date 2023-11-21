package tech.reliab.course.zimskovma.bank.exception;

public class IncomException extends RuntimeException  {
    public IncomException() {
        super("Ошибка: доход  слишком низкий");
    }
}
