import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

//public class LoginAction implements Action{
public class LoginAction extends ActionSupport {// extend action support to send
												// error
	String name, pass;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String execute() throws Exception {

		if (validateString(getName()) && validateString(getPass())) {
			if (getName().equals("Manu") && getPass().equals("Manu1234")) {
				return "SUCCESS";
			}

			else {
				addActionError(getText("errors.name.empty"));
				addActionError(getText("errors.pass.empty"));
				return "ERROR";
			}
		}

		else {
			addActionError(getText("errors.name.empty"));
			addActionError(getText("errors.pass.empty"));
			return "ERROR";
		}

	}

	private boolean validateString(String str) {

		if (str != null && !str.equals(""))

			return true;

		return false;
	}

}
