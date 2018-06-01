package jmy.com.newsschool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MapCopyUtil {
    public static int copyFiles(String fromDir,String toDir){
        File fromFile = new File(fromDir);
        if(!fromFile.exists()){
            return -1;//源文件夹不存在，检查u盘中是否有该文件夹
        }
        File toFile = new File(toDir);
        if(!toFile.exists()){
            toFile.mkdirs();
        }
        File[] listFile = fromFile.listFiles();
        if(listFile==null||listFile.length==0){
            return -2;//文件夹下没有文件
        }
        for(File file :listFile){
            if(!copyFile(file.getAbsolutePath(),toDir+"/"+file.getName())){
                delete(toDir);
                return 1;
            }
        }
        return 0;
    }

    public static void delete(String toDir) {
        File toFile = new File(toDir);
        if(!toFile.exists()){
            return;
        }
        for (File f : toFile.listFiles()){
            f.delete();
        }
        toFile.delete();
    }

    private static boolean copyFile(String fromFile, String toFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] b = new byte[1024];
        try {
            inputStream = new FileInputStream(fromFile);
            outputStream = new FileOutputStream(toFile);
            while (inputStream.read(b) > 0) {
                outputStream.write(b, 0, b.length);
            }
            inputStream.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }
}
