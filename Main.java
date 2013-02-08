import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import EBG.util.CopyFile;
import EBG.util.EBGInstallException;
import EBG.util.EBGInstallMethods;
import EBG.util.ZipFileCustom;

public class Main
{
	static String path = System.getProperty("user.home") + File.separatorChar + "AppData/";
	static String mainFolder = System.getProperty("user.home") + File.separatorChar + "Appdata/Roaming/.minecraft/";
	static String outAutoPath = (path + "/Roaming/.EBGBackup/Backupped .minecraft's/AutoBackup (MinecraftFolder)/");
	static String outCustomPath = (path + "/Roaming/.EBGBackup/Backupped .minecraft's/CustomBackup (MinecraftFolder)/");

	static String minecraftString = mainFolder + "bin/minecraft.jar";
	static File mainFolderFile = new File(mainFolder);
	static File minecraft = new File(path + "Roaming/.minecraft/bin/minecraft.jar");

	static File backupFolder = new File(path + "Roaming/.EBGBackup/");
	static File backupFolderJar = new File(backupFolder + "/Backupped Jars/");
	static File backupFolderCustomJar = new File(backupFolderJar + "/CustomBackup (.jar)/");
	static File backupFolderAutoJar = new File(backupFolderJar + "/AutoBackup (.jar)/");

	static File backupFolderMc = new File(backupFolder + "/Backupped .minecraft's/");
	static File backupFolderMcAuto = new File(backupFolderMc + "/AutoBackup (MinecraftFolder)/");
	static File backupFolderMcCustom = new File(backupFolderMc + "/CustomBackup (MinecraftFolder)/");

	static Scanner scanner = new Scanner(System.in);
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

	static ArrayList<File> fileSave = new ArrayList<File>();

	static EBGInstallMethods m;
	static ZipFileCustom zipFile;
	static File readme;
	static FileWriter writer;
	static CopyFile c;

	@SuppressWarnings("static-access")
	/**Main menu, installing algorithms, and backupping.*/
	public static void main(String[] args) throws Exception
	{	
		m.print("EBG Installation Assistant for EBG V1.3 (1.4.7) by Florilu", 1);
		System.out.println("Options: ");
		System.out.println("Install:        [1]");
		System.out.println("Uninstall:      [2]");
		System.out.println("Install backup: [3]");
		System.out.println("Exit:           [4]");
		System.out.print("Choose Optionnumber: ");
		int num = scanner.nextInt();
		if (num == 1) {
			if (!backupFolder.exists() || !backupFolderJar.exists() || !backupFolderAutoJar.exists() || !backupFolderCustomJar.exists() || !backupFolderMc.exists() || !backupFolderMcAuto.exists() || !backupFolderMcCustom.exists()) {
				if(!backupFolder.exists()){
					install(backupFolder);
					m.print("BackupFolder didn't exist, created it!", 1);
				}if(!backupFolderJar.exists()){
					install(backupFolderJar);
					m.print("BackupFolder for Jars didn't exist, created it!", 1);
				}if(!backupFolderAutoJar.exists() && backupFolder.exists()){
					install(backupFolderAutoJar);
					m.print("AutobackupFolder for Jars didn't exist, created it!", 1);
				}if(!backupFolderCustomJar.exists() && backupFolder.exists()){
					install(backupFolderCustomJar);
					m.print("CutombackupFolder for Jars didn't exist, created it!", 1);
				}if(!backupFolderMc.exists()){
					install(backupFolderMc);
					m.print("BackupFolder for Minecraft folder didn't exist, created it!", 1);
				}if(!backupFolderMcAuto.exists()){
					install(backupFolderMcAuto);
					m.print("AutobackupFolder for Minecraft folder didn't exist, created it!", 1);
				}if(!backupFolderMcCustom.exists()){
					install(backupFolderMcCustom);
					m.print("CustombackupFolder for Minecraft folder didn't exist, created it!", 1);
				}
			}
			m.print("", 1);
			m.print("Do you want to backup .minecraft or just the jar? (1 = jar, 2 = .minecraft): ", 2);
			int mcJar = scanner.nextInt();
			if(mcJar == 1){
				m.print("Do you want to create an custom backup, or an automatic backup? (1 = auto, 2 = custom): ", 2);
				int cuAJar = scanner.nextInt();
				if(cuAJar == 1){
					if (backupFolderAutoJar.exists()) {
						File[] files = backupFolderAutoJar.listFiles();
						int  i = 0;
						for(File file :files){
							if(file.isFile()){
								i++;
							}
						}

						String mcCopy = (backupFolderAutoJar + "/backup" + i + ".jar");
						CopyFile.copy(CopyFile.file(minecraftString), CopyFile.file(mcCopy));
					}
				}else if(cuAJar == 2){
					m.print("Type in a name for your backup: ", 1);
					String customName = bf.readLine();
					String mcCopyCustom = (backupFolderCustomJar + "/" + customName + ".jar");
					CopyFile.copy(CopyFile.file(minecraftString), CopyFile.file(mcCopyCustom));
				}
				m.print("Backupped jar!", 1);
			}if(mcJar == 2){
				m.print("Do you want to create an automatic or custom backup? (1 = auto, 2 = custom): ", 2);
				int aucuFo = scanner.nextInt();
				if(aucuFo == 1){
					File[] files = backupFolderMcAuto.listFiles();
					int i = 1;
					for(File file : files){
						if(file.isFile()){
							i++;
						}
					}
					String out = (outAutoPath + "/backup" + i + ".zip");
					File outFile = new File(out);

					zipFile.zip(mainFolderFile, out);

					m.print("", 1);
					m.print("Backupped Minecraft Main Folder to: " + outFile.getAbsolutePath(), 1);
					m.print("", 1);
				}else if(aucuFo == 2){
					m.print("Type in the name of your backup: ", 2);
					String customName = bf.readLine();
					String out = (outCustomPath + "/" + customName + ".zip");
					File outFile = new File(out);
					zipFile.zip(mainFolderFile, out);
					m.print("", 1);
					m.print("Backupped Minecraft Main Folder to: " + outFile.getAbsolutePath(), 1);
					m.print("", 1);
				}
			}
			minecraft.delete();
			new Main().extractArchive(new File("files/install.zip"), new File(path + "Roaming/.minecraft/bin"));
			System.out.println("ExtraBiomesGen has been installed.");
		}
		if (num == 2) {
			m.print("Do you want to delete the backups?(1 = yes, 2 = no): ", 2);
			int delBack = scanner.nextInt();
			if (delBack == 1){
				del(backupFolder);
				m.print("Backups has been deleted.", 1);
			}
			if(!minecraft.exists()){
				new Main().extractArchive(new File("files/refresh.zip"), new File(path + "Roaming/.minecraft/bin"));
				System.out.println("EBG has been uninstalled!");
			}else{
				minecraft.delete();
				new Main().extractArchive(new File("files/refresh.zip"), new File(path + "Roaming/.minecraft/bin"));
				System.out.println("EBG has been uninstalled!");
			}
		}
		if(num == 3){
			int count = -1;
			int fileNumber = 0;
			File[] files = backupFolderAutoJar.listFiles();
			for(File file : files){
				fileSave.add(file);
				count++;
			}
			for(int i = 0; i <= count; i++){
				System.out.println(fileSave.get(i).getName() + " [" + (i) + "]");
			}
			System.out.print("Which backup should be installed? : ");
			int numberOfBackup = scanner.nextInt();
			File ask = fileSave.get(numberOfBackup);
			String finalAsk = ask.getAbsolutePath();
			System.out.println(finalAsk);
			CopyFile.copy(CopyFile.file(finalAsk), CopyFile.file(minecraftString));
			System.out.println("Installed backup!");
		}
		if (num == 4) {
			m.print("Quitting Installer...", 1);
			try{
				Thread.sleep(500);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.exit(0);
		}
	}

	/**Function for creating folders, for making the code even smaller.
	 * If something went wrong, it will throw an exception to handle the problem.*/
	public static void install(File folder){
		try{
			folder.mkdir();
		}catch(EBGInstallException e){
			throw new EBGInstallException("Something went wrong due the installation of the mod.");
		}
	}

	/**Used in the first phases of the installer for writing in a text that
	 * the the backup function is still WIP.*/
	public static void write() throws IOException
	{
		String text = "The Backupfunction is still WIP, be sure you'll backup your jar by yourself!";
		String dateiName = mainFolder + "EBGBackup/README.txt";
		FileOutputStream schreibeStrom = new FileOutputStream(dateiName);
		for (int i = 0; i < text.length(); i++) {
			schreibeStrom.write((byte)text.charAt(i));
		}
		schreibeStrom.close();
	}

	/**For extracting archives to an destination folder.*/
	public void extractArchive(File archive, File destDir) throws Exception {
		if (!destDir.exists()) {
			destDir.mkdir();
		}

		minecraft.delete();

		ZipFile zipFile = new ZipFile(archive);
		Enumeration entries = zipFile.entries();

		byte[] buffer = new byte[16384];

		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry)entries.nextElement();

			String entryFileName = entry.getName();

			if (entry.isDirectory()) {
				File dir = new File(destDir, entryFileName);

				if (!dir.exists())
					dir.mkdir();
			}
			else
			{
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(new File(destDir, entryFileName)));

				BufferedInputStream bis = new BufferedInputStream(zipFile
						.getInputStream(entry));
				int len;
				while ((len = bis.read(buffer)) > 0)
				{
					bos.write(buffer, 0, len);
				}

				bos.flush();
				bos.close();
				bis.close();
			}
		}
	}

	/**Method for deleting first files, 
	 * then folders, with recursive searching.*/
	public static boolean del(File dir){
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(File aktFile : files){
				del(aktFile);
			}
		}
		return dir.delete();
	}
}