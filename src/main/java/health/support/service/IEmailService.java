package health.support.service;

public interface IEmailService {
    void send(String sendTo, String subject, String messageToSend);

    void sendWelcomeLetter(String sendTo);

    void sendWarningLetter(String sendTo);
}
