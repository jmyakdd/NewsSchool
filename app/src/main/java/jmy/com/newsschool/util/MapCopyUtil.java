package jmy.com.newsschool.util;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import jmy.com.newsschool.bean.CityData;

public class MapCopyUtil {
    public static int copyFiles(String province, String fromDir, String toDir) {
        File fromFile = new File(fromDir);
        if (!fromFile.exists()) {
            return -1;//源文件夹不存在，检查u盘中是否有该文件夹
        }
        File toFile = new File(toDir);
        if (!toFile.exists()) {
            toFile.mkdirs();
        }
        File[] listFile = fromFile.listFiles();
        if (listFile == null || listFile.length == 0) {
            return -2;//文件夹下没有文件
        }
        for (File file : listFile) {
            List<CityData> cityData1 = DataSupport.where("name = ?", file.getName()).find(CityData.class);
            if (cityData1 != null&&cityData1.size()>0) {
                continue;
            }
            copyFile(file.getAbsolutePath(), toDir + "/" + file.getName());
            CityData cityData = new CityData();
            cityData.setName(file.getName());
            cityData.setParentName(province);
            cityData.save();
        }
        return 0;
    }

    public static void delete(String toDir, String proviceKey) {
        File toFile = new File(toDir);
        if (!toFile.exists()) {
            return;
        }
        List<CityData> citys = DataSupport.where("parentName = ?",proviceKey).find(CityData.class);
        for(CityData c :citys){
            File f = new File(toDir+"/"+c.getName());
            if(f.exists()){
                f.delete();
            }
            DataSupport.delete(CityData.class,c.getId());
        }
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
