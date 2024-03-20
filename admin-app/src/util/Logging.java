package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logging {
	private static final String LOGS_PATH = "/home/keks/Documents/ip/fitness-app/admin-app/WebContent/WEB-INF/logs/log.txt";
	private static final ZoneId zone = ZoneId.systemDefault();
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
	
	private static File getLogsFile() throws IOException{
		File file = new File(LOGS_PATH);
		file.createNewFile();
		
		return file;
	}
	
	public static List<String> readLogs() {
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		try {
			File file = getLogsFile();
			BufferedReader reader = Files.newBufferedReader(file.toPath());
			reader.lines().forEach(l -> {
				if (l.contains("---")) {
					list.add(sb.toString());
					sb.setLength(0);
				}
				else {
					sb.append(l).append(System.lineSeparator());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void addLog(String log) {
		try {
			File logsFile = getLogsFile();
			LocalDate fileLastModified = getLocalDate(logsFile.lastModified());
			String content = buildLog(LocalDateTime.now().format(dateFormatter), log);
			boolean append = !LocalDate.now().isAfter(fileLastModified);
			FileWriter writer = new FileWriter(logsFile, append);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static LocalDate getLocalDate(long millis) {
		Instant instant = Instant.ofEpochMilli(millis);
		return instant.atZone(zone).toLocalDate();
	}
	
	private static String buildLog(String timestamp, String log) {
		StringBuilder sb = new StringBuilder();
		sb.append(timestamp)
			.append(System.lineSeparator())
			.append(log)
			.append(System.lineSeparator())
			.append("-----")
			.append(System.lineSeparator());
		
		return sb.toString();
	}
}
