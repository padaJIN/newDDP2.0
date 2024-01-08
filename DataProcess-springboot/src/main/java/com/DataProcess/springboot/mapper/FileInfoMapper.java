package com.DataProcess.springboot.mapper;

import com.DataProcess.springboot.entity.FileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pada
 * @since 2023-12-22
 */
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    void addFile(String fileName, String filePath, Date date, String remark);


    List<FileInfo> getFileList();
}
