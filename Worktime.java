import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Worktime implements Serializable {

	private static final long serialVersionUID = 6274368384671447493L;

	private Date begintime;

	private Date endtime;

	private String format;

	public Worktime(String begintime, String endtime, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		this.setSignin(sdf.parse(begintime));
		this.setSignout(sdf.parse(endtime));
		this.setFormat(format);
	}

	public Worktime(Date begintime, Date endtime, String format) {
		this.setSignin(begintime);
		this.setSignout(endtime);
		this.setFormat(format);
	}

	public Date getSignin() {
		return begintime;
	}

	public void setSignin(Date signin) {
		this.begintime = signin;
	}

	public Date getSignout() {
		return endtime;
	}

	public void setSignout(Date signout) {
		this.endtime = signout;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
