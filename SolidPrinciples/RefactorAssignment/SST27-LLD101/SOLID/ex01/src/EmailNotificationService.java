public class EmailNotificationService implements NotificationService {
    private EmailClient emailClient = new EmailClient();

    public void notify(String to, String body) {
        emailClient.send(to, body);
    }
}
