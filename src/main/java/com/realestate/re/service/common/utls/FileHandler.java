package com.realestate.re.service.common.utls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.realestate.re.service.common.enums.MimeType;
import com.realestate.re.service.core.activitylog.ActivityLog;

public class FileHandler {

	public String multipartSingleNameResolver(MultipartFile file) {

		if (file != null) {
			if (!file.getOriginalFilename().trim().equals("")) {
				return file.getOriginalFilename();
			} else {
				return "";
			}
		} else {
			return "";
		}

	}

	public static final String uploadClientDoc(MultipartFile image, String filePath) throws IOException {

		String randomName = "doc-";

		randomName = randomName + UUID.randomUUID() + "."
				+ image.getContentType().substring(image.getContentType().lastIndexOf("/") + 1);

		String path = FilePath.getOSPath() + File.separator + filePath;

		File file = new File(path);

		if (!file.exists())
			file.mkdirs();

		LoggerUtil.logInfo(FileHandler.class, "document uploading..............." + path);

		try {
			FileCopyUtils.copy(image.getBytes(), new File(path + File.separator + randomName));
			LoggerUtil.logInfo(FileHandler.class, "document uploaded..............." + path);
			return randomName;
		} catch (Exception e) {
			LoggerUtil.logException(FileHandler.class, e);
			throw e;
		}
	}

	public static StringBuilder readFile(String path) {
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			LoggerUtil.logException(FileHandler.class, e);
			return null;
		}

		LoggerUtil.logInfo("file readed : " + contentBuilder.toString());
		return contentBuilder;
	}

	public String readFileToString(String filePath) {
		try {

			ClassLoader classLoader = getClass().getClassLoader();

			return FileUtils.readFileToString(new File(classLoader.getResource(filePath).getFile()));
		} catch (IOException e) {
			LoggerUtil.logException(FileHandler.class, e);
			return "";
		}
	}

	public void writeActivityLog(String log) {

		synchronized (FileHandler.class) {
			String path = FilePath.getOSPath() + File.separator + "realestate" + File.separator + "activitylog"
					+ File.separator;
			File filePath = new File(path);
			String fileName = DateParseUtil.getCurrentDateStr() + ".csv";

			if (!filePath.exists()) {
				filePath.mkdirs();
			}

			RandomAccessFile stream = null;
			FileChannel channel = null;
			try {

				stream = new RandomAccessFile(path + fileName, "rw");
				stream.seek(stream.length());
				channel = stream.getChannel();
				byte[] strBytes = log.getBytes();
				ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
				buffer.put(strBytes);
				buffer.flip();
				channel.write(buffer);
				stream.close();
				channel.close();

			} catch (FileNotFoundException e) {
				LoggerUtil.logException(FileHandler.class, e);
			} catch (IOException e) {
				LoggerUtil.logException(FileHandler.class, e);
			} finally {
				try {
					stream.close();
					channel.close();
				} catch (IOException e) {
					LoggerUtil.logException(FileHandler.class, e);
				}
			}
			LoggerUtil.logInfo("yahoo...!!!!");
		}

	}

	public static List<String> getLogFileNameList() {
		try {

			String path = FilePath.getOSPath() + File.separator + "realestate" + File.separator + "activitylog"
					+ File.separator;
			File filePath = new File(path);

			File[] files = filePath.listFiles();

			List<String> fileNameList = new ArrayList<>();

			for (File temp : files) {
				fileNameList.add(temp.getName());
			}

			return fileNameList;
		} catch (Exception e) {
			LoggerUtil.logException(FileHandler.class, e);
			return new ArrayList<String>();
		}
	}

	public static List<ActivityLog> getListOfActivityLog(String inputFilePath) {
		List<ActivityLog> inputList = new ArrayList<ActivityLog>();
		String path = FilePath.getOSPath() + File.separator + "realestate" + File.separator + "activitylog"
				+ File.separator;

		try {
			File inputF = new File(path + inputFilePath);
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			// skip the header of the csv
			// inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
			inputList = br.lines().map(mapToItem).collect(Collectors.toList());
			br.close();
		} catch (IOException e) {
			LoggerUtil.logException(FileHandler.class, e);
		}
		return inputList;
	}

	private static Function<String, ActivityLog> mapToItem = (line) -> {
		if (line == null) {
			return null;
		}

		String[] p = line.split(",");// a CSV has comma separated lines

		if (p.length != 6) {
			return null;
		}

		ActivityLog item = new ActivityLog(p[0], p[1], p[2], p[3], p[4], p[5]);

		return item;
	};

	public static MimeType getFileMimeType(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		switch (extension) {
		case "jpeg":
			return MimeType.JPEG;
		case "jpg":
			return MimeType.JPEG;
		case "pdf":
			return MimeType.PDF;
		case "jpe":
			return MimeType.JPE;
		case "gif":
			return MimeType.GIF;
		default:
			return null;
		}
	}

	public static long getFileSizeInKb(String filePath) {
		File file = new File(filePath);
		return file.length() / 1024;
	}
}
