
import java.io.*;
import java.util.zip.GZIPOutputStream;
public class compressor{
    public static void method(File f) throws IOException{
        String outFilePath=f.getAbsolutePath();
        FileInputStream fis=new FileInputStream(f);
        FileOutputStream fos=new FileOutputStream(outFilePath+".gz");
        GZIPOutputStream gzip =new GZIPOutputStream(fos);
    
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=fis.read(buffer))!=-1){
            gzip.write(buffer,0,len);
        }
        gzip.close();
        fis.close();
        fos.close();
    }
}
