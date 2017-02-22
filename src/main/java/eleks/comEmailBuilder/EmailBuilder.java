package eleks.comEmailBuilder;

public class EmailBuilder implements IEmailBuilder {

	Email email;

	public EmailBuilder() {
		email = new Email();
	}

	@Override
	public EmailBuilder buildTo(String to) {
		email.setTo(to);
		return this;
	}

	@Override
	public EmailBuilder buildSubject(String subject) {
		email.setSubject(subject);
		return this;
	}

	@Override
	public EmailBuilder buildBody(String body) {
		email.setBody(body);
		return this;
	}

	@Override
	public Email build() {
		return this.email;
	}

}
