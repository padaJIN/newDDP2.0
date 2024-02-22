package com.DataProcess.springboot.config.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class pathConfig {
    @Value("${pythonPath.transAPI}")
    private String transAPIPath;
    @Value("${pythonPath.splitSentenceAPI}")
    private String splitSentencePath;
    @Value("${pythonPath.deleteMessAPI}")
    private String deleteMessPath;
    @Value("${pythonPath.deleteNetAPI}")
    private String deleteNetPath;
    @Value("${pythonPath.deleteshortAPI}")
    private String deleteshortPath;
    @Value("${pythonPath.deletelongAPI}")
    private String deletlongPath;
    @Value("${filePath.fileupload}")
    private String FileUploadPath;
    @Value("${filePath.processedFile}")
    private String processedFilePath;
    @Value("filePath.pdfuploadDir")
    private String pdfuploadDirPath;
    @Value("filePath.transPDFDir")
    private String transPDFDirPath;
    public String getTransAPIPath() {
        return transAPIPath;
    }
    public String getSplitSentencePath() {
        return splitSentencePath;
    }
    public String getDeleteMessPath() {
        return deleteMessPath;
    }
    public String getDeleteNetPath() {
        return deleteNetPath;
    }
    public String getDeleteshortPath() {
        return deleteshortPath;
    }
    public String getDeletlongPath() {
        return deletlongPath;
    }
    public String getFileUploadPath(){
        return FileUploadPath;
    }
    public String getProcessedFilePath() {return processedFilePath;}

    public String getPdfuploadDirPath() {return pdfuploadDirPath;}

    public String getTransPDFDirPath() {return transPDFDirPath;}
}
