package a01208015.lab8;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

/**
 * This is a driver class to test program's functionality
 * @author Veronica A01208105
 * @version 2020-11-14
 */
public class Lab8 {

	public static final String HORIZONTAL_LINE = "============================================================================";
	public static final String HEADER_FORMAT = "%3s. %-6s %-6s %-8s %-10s %-15s %-12s %-15s%n";
	public static final String RUNNER_FORMAT = "%-6d %-5d %-8d %-6s %-10s %-15s %5.3f %11.3f %n";

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		Instant startTime = Instant.now();
		System.out.println(startTime);

		Runner runner1 = new Runner(6, 2612, "JAM", "Bolt", "Usain", 155);
		Runner runner2 = new Runner(4, 2069, "USA", "Gatlin", "Justin", 152);
		Runner runner3 = new Runner(7, 2196, "CAN", "De Grasse", "Andre", 141);
		Runner runner4 = new Runner(9, 2611, "JAM", "Blake", "Yohan", 145);
		Runner runner5 = new Runner(3, 2909, "RSA", "Simbine", "Akani", 128);
		Runner runner6 = new Runner(8, 2245, "CIV", "Meite", "Ben Youssef", 156);

		runner1.start();
		runner2.start();
		runner3.start();
		runner4.start();
		runner5.start();
		runner6.start();
		runner6.join();

		generateReport();

		Instant endTime = Instant.now();
		System.out.println(endTime);
		System.out.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis());
	}

	/**
	 * Method to generate report
	 */
	public static void generateReport() {
		System.out.format("%s%n", HORIZONTAL_LINE);
		System.out.format(HEADER_FORMAT, "Rank", "Lane", "Bib#", "Country", "Last Name", "First Name", "Reaction",
				"Result");

		List<Long> resultsToSort = new ArrayList<Long>(Runner.runners.values());

		resultsToSort.sort(new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				return o1.compareTo(o2);
			}
		});

		for (int i = 0; i < resultsToSort.size(); i++) {
			for (Entry<Runner, Long> entry : Runner.runners.entrySet()) {
				if (entry.getValue().equals(resultsToSort.get(i))) {
					System.out.format(RUNNER_FORMAT, i + 1, // int
							entry.getKey().getLane(), // int
							entry.getKey().getBib(), // int
							entry.getKey().getCountry(), // string
							entry.getKey().getLastName(), // string
							entry.getKey().getFirstName(), // string
							(double) entry.getKey().getReaction() / 1000, // long => double
							(double) entry.getValue() / 1000); // long => double
				}
			}
		}

		System.out.format("%s%n", HORIZONTAL_LINE);
	}

}
