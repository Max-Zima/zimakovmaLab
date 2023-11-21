package tech.reliab.course.zimskovma.bank.exception;

public class NotFoundException extends Exception {
    public NotFoundException(int id) {
        super("Ошибка: объект не существует");
    }
}