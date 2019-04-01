

package cn.emergentdesign.dcf.cli;

import cn.emergentdesign.dcf.stat.resource.MemoryUsage;
import picocli.CommandLine;
import picocli.CommandLine.PicocliException;

public class Main {

	public static void main(String[] args) {
		try {
			CliParameters app = CommandLine.populateCommand(new CliParameters(), args);
			if (app.help) {
				CommandLine.usage(new CliParameters(), System.out);
				System.exit(0);
			}
			executeCommand(app);
		} catch (Exception e) {
			if (e instanceof PicocliException) {
				CommandLine.usage(new CliParameters(), System.out);
			}else {
				System.err.println("Exception encountered. If it is a design error, please report issue to us." );
				e.printStackTrace();
			}
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	private static void executeCommand(CliParameters params) {

		long startTime = System.currentTimeMillis();

		App app = new App(params);
		app.run();
		long endTime = System.currentTimeMillis();
		System.out.println("Consumed time: " + (float) ((endTime - startTime) / 1000.00) + " s,  or "
				+ (float) ((endTime - startTime) / 60000.00) + " min.");
	}

}
