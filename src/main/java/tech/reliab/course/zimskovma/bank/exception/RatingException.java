package tech.reliab.course.zimskovma.bank.exception;

public class RatingException extends RuntimeException  {
    public RatingException() {
        super("Ошибка: рейтинг слишком низкий.");
    }

}
