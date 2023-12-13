package tech.reliab.course.zimskovma.bank.exception;

public class ExportException extends RuntimeException {
    public ExportException(String msg) {
        super("Ошибка: ExportException, " + msg);
    }

}