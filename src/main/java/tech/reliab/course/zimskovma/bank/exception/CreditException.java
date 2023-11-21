package tech.reliab.course.zimskovma.bank.exception;

public class CreditException extends RuntimeException  {
    public CreditException() {
        super("Ошибка: невозможно отдать должное");
    }
}
