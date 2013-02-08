package EBG.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class ZipFileCustom {
 
	/**It takes a folder (.jar, .zip, .rar and so on...) and extracts it to an directory.*/
    public static boolean zip(File directory, String outputDirectory) {
        
        boolean result = false;
        
        byte[] buffer = new byte[8192];
        
            try {
                
                LinkedList<String> fileList = getAllFiles(directory);
                
                //String outFilename = "D:\\outfile.zip";
                FileOutputStream fos = new FileOutputStream(outputDirectory);
                ZipOutputStream zos = new ZipOutputStream(fos);
                
                for (String fileName : fileList) {
                    
                    FileInputStream fis = new FileInputStream(fileName);
                    zos.putNextEntry(new ZipEntry(fileName));
                    
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    
                    zos.closeEntry();
                    fis.close();
                }   
                
                zos.close();
                fos.close();
                
                result = true;
                
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
        return result;
        
    }
   public static LinkedList<String> getAllFiles(File directory) {
        
        File[] files = directory.listFiles();
        
        LinkedList<String> allFiles = new LinkedList<String>();
        
        for (File file : files) {
            
            if (file.isDirectory()) {
                LinkedList<String> moreFiles = getAllFiles(file);
                Iterator<String> iterMoreFiles = moreFiles.iterator();
                while(iterMoreFiles.hasNext()) {
                    allFiles.add(iterMoreFiles.next());
                }
                
            } else {
                allFiles.add(file.getAbsolutePath());
            }   
        }
        
        return allFiles;
    }
}