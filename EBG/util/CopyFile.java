package EBG.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;

public class CopyFile
{
  public static File file(String path)
  {
    return new File(path);
  }

  
  /**Copies the file from the source to the destination.*/
  public static void copy(File source, File destination) {
    try {
      FileInputStream fileInputStream = new FileInputStream(source);
      FileOutputStream fileOutputStream = new FileOutputStream(
        destination);

      FileChannel inputChannel = fileInputStream.getChannel();
      FileChannel outputChannel = fileOutputStream.getChannel();

      transfer(inputChannel, outputChannel, source.length(), 33554432L, true, true);

      fileInputStream.close();
      fileOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**Transfers the file and shows the progress.*/
  public static void transfer(FileChannel fileChannel, ByteChannel byteChannel, long lengthInBytes, long chunckSizeInBytes, boolean verbose, boolean fromFile)
    throws IOException
  {
    long overallBytesTransfered = 0L;
    long time = -System.currentTimeMillis();
    while (overallBytesTransfered < lengthInBytes)
    {
      long bytesTransfered = 0L;

      if (fromFile)
        bytesTransfered = fileChannel.transferTo(0L, Math.min(
          chunckSizeInBytes, lengthInBytes - 
          overallBytesTransfered), byteChannel);
      else {
        bytesTransfered = fileChannel.transferFrom(byteChannel, 
          overallBytesTransfered, Math.min(chunckSizeInBytes, 
          lengthInBytes - overallBytesTransfered));
      }

      overallBytesTransfered += bytesTransfered;

      if (verbose) {
        System.out.printf(
          "overall bytes transfered: %s progress %s%%\n", new Object[] { 
          Long.valueOf(overallBytesTransfered), 
          Long.valueOf(Math.round(overallBytesTransfered / 
          lengthInBytes * 100.0D)) });
      }
    }

    time += System.currentTimeMillis();

    if (verbose) {
      System.out.printf("Transfered: %s bytes in: %s s -> %s kbytes/s", new Object[] { 
        Long.valueOf(overallBytesTransfered), Long.valueOf(time / 1000L), 
        Double.valueOf(overallBytesTransfered / 1024.0D / (time / 1000.0D)) });
    }
    System.out.println();
  }
}