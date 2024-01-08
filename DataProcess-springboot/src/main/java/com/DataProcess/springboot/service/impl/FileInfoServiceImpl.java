package com.DataProcess.springboot.service.impl;

import com.DataProcess.springboot.entity.FileInfo;
import com.DataProcess.springboot.mapper.FileInfoMapper;
import com.DataProcess.springboot.service.IFileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pada
 * @since 2023-12-22
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {
    @Autowired
    private  FileInfoMapper fileInfoMapper  ;
    @Override
    public void addFile(String file_name, String file_path, Date date, String remark) {
        fileInfoMapper.addFile(file_name,file_path,date,remark);
    }
    @Override
    public void insertFile(FileInfo fileInfo){
        fileInfoMapper.insert(fileInfo);
    }
@Override
    public List<FileInfo> getFileList() {
        return  fileInfoMapper.getFileList();
    }
}
