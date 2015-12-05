package com.example.whugis.gis4;

import android.os.Environment;

import java.io.File;

/**
 * Created by Liu Tongshuo on 2015/11/27.
     */
public class SDCardHelper
{
    public static final String[] mapFiles=  {"/LibraryMap/1.线.shp", "/LibraryMap/路1.shp", "/LibraryMap/路节点1.shp"};

    public String getSDPath()
    {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if   (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            return sdDir.toString();
        }
        else
        {
            return "";
        }
     }

}
