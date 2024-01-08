package com.DataProcess.springboot.service;

import com.DataProcess.springboot.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pada
 * @since 2023-12-22
 */
public interface IFileInfoService extends IService<FileInfo> {
    /**
     *
     * @author pada
     * @since 2023-12-22
     * 新增上传文件
     */
    void addFile(String file_name, String filePath, Date date, String remark);

    void insertFile(FileInfo fileInfo);

    List<FileInfo> getFileList();

    void deleteFile(String filePath);
    List<FileInfo> getFileList(int offset, int pageSize);
}
