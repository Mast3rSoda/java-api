package pl.wi.xd.api.db.services.interfaces;

public interface IEmailService {
    void SendEmail(String emailTo, String Subject, String content);
}
