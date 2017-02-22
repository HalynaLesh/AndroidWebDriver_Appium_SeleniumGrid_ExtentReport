package eleks.comEmailBuilder;

public interface IEmailBuilder {
	public EmailBuilder buildTo(String to);

	public EmailBuilder buildSubject(String subject);

	public EmailBuilder buildBody(String body);

	public Email build(); // return email

}
