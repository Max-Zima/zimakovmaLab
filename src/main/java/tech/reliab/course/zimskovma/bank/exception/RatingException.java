package tech.reliab.course.zimskovma.bank.exception;

public class RatingException extends Exception {
    public RatingException() {
        super("Ошибка: рейтинг слишком низкий.");
    }

}
