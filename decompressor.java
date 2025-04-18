import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class decompressor {
        public static void method(File file) throws IOException{
            String fileName = file.getName();
            String outFileName;
            if (fileName.toLowerCase().endsWith(".gz")) {
                outFileName = fileName.substring(0, fileName.length() - 3);
            } else {
                throw new IOException("Not a GZIP file");
            }
            
            // Create output file in same directory
            File outputFile = new File(file.getParent(), outFileName);
            
            // Decompress
            try (GZIPInputStream gzipIS = new GZIPInputStream(new FileInputStream(file));
                FileOutputStream fos = new FileOutputStream(outputFile)) {
                
                byte[] buffer = new byte[1024];
                int len;
                while ((len = gzipIS.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
        }
}
