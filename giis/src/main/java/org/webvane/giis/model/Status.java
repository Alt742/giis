package org.webvane.giis.model;

public class Status {
    public static final String NOT_SEND = "NOT_SEND";
    public static final String NEW = "NEW";
    public static final String SENT = "SENT";
    public static final String SEND_PROCESSING = "SEND_PROCESSING";    //Отправлен проверен В ГИС НА ОБРАБОТКЕ в момент проверки
    public static final String SEND_PREPARED = "SEND_PREPARED";    //Отправлен проверен В ГИС НА обработан ответ получен
    public static final String SEND_ERROR = "SEND_ERROR";
}
