package org.action.file;

import java.io.InputStream;

/**
 * @author gzw
 * @description： 文件服务
 * &since：2025/8/2
 */

public interface FileService {

    /**
     * 文件上传
     * @param path path
     * @param fileStream stream
     * @return boolean
     */
    public boolean upload(String path, InputStream fileStream);
}
