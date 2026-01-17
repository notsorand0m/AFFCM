package com.affcm;

import java.io.File;

// Class for json format
public class Data{
    //Date date;
    public String log;
    public String AIModel;
    public String theme;
    public String org_level;
    public String archive_interval;
    public String multithreading;
    public String autoSleepControl;
    public String thermalOptimization;
    public String fileView;
    public String automaticBackup;
    public String fileVault;
    public String tempFolder;
    public String rawFile;

    public Data(String log, String theme, String AIModel, String org_level, String archive_interval, String multithreading, String autoSleepControl, String thermalOptimization, String fileView, String automaticBackup, String fileVault, String temoFolder, String rawFile){
        //this.date = date;
        this.log = log;
        this.theme = theme;
        this.AIModel = AIModel;
        this.org_level = org_level;
        this.archive_interval = archive_interval;
        this.multithreading = multithreading;
        this.autoSleepControl = autoSleepControl;
        this.thermalOptimization = thermalOptimization;
        this.fileView = fileView;
        this.automaticBackup = automaticBackup;
        this.fileVault = fileVault;
        this.tempFolder = temoFolder;
        this.rawFile = rawFile;
    }

    public Data() {
        this.log = "";
        this.theme = "";
        this.AIModel = "";
        this.org_level = "";
        this.archive_interval = "";
        this.multithreading = "";
        this.autoSleepControl = "";
        this.thermalOptimization = "";
        this.fileView = "";
        this.automaticBackup = "";
        this.fileVault = "";
        this.tempFolder = "";
        this.rawFile = "";
    }
}
