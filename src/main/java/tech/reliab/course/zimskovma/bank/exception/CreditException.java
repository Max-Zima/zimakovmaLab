package tech.reliab.course.zimskovma.bank.exception;

public class CreditException extends Exception {
    public CreditException() {
        super("Ошибка: невозможно отдать должное");
    }
}
