import java.io.File;
import java.text.ParseException;
import java.util.List;

public class WorktimeCalc {

	public static String FILE_PATH = "." + File.separator + "worktime.txt";

	public static String TIME_FORMAT = "HH:mm";

	public static Worktime morning = null;

	public static Worktime afternoon = null;

	public static Worktime evening = null;

	public static void main(String[] args) throws ParseException {
		init();

		List<Worktime> wkList = parseWorktimeFile(new File(FILE_PATH));
		double manhours = calcMonthWorktime(wkList);
		System.out.println("本月工时：" + manhours);
	}

	private static void init() throws ParseException {
		morning = new Worktime("8:30", "12:00", TIME_FORMAT);
		afternoon = new Worktime("13:30", "18:00", TIME_FORMAT);
		evening = new Worktime("7:00", "12:00", TIME_FORMAT);
	}

	private static List<Worktime> parseWorktimeFile(File wtfile) {
		return null;
	}

	private static double calcMonthWorktime(List<Worktime> wkList) {
		double month = 0;

		for (Worktime wk : wkList) {
			month = month + calcDayWorktime(wk);
		}

		return month;
	}

	private static double calcDayWorktime(Worktime worktime) {
		return 0;
	}

}
