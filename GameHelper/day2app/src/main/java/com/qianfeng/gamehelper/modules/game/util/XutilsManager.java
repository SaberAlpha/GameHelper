package com.qianfeng.gamehelper.modules.game.util;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class XutilsManager {

    private static XutilsManager instance;

    private DbManager dbManager;


    private XutilsManager() {

    }

    public static XutilsManager getInstance() {
        if (instance == null) {
            synchronized (XutilsManager.class) {
                if (instance == null) {
                    instance = new XutilsManager();
                }
            }
        }
        return instance;
    }

    public DbManager getDbManager() {
        if (dbManager == null) {
            initDbManager();
        }
        return dbManager;
    }

    private void initDbManager() {
        File dbFile = new File("/sdcard/xutils/db");
        if (!dbFile.exists()) {
            dbFile.mkdirs();
        }
        DbManager.DaoConfig config = new DbManager.DaoConfig().setDbDir(dbFile).setDbName("xutil").setDbVersion(1).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                //TODO 数据库更新操作
            }
        });
        dbManager = x.getDb(config);
    }
}
