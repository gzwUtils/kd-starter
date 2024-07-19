package org.action.thread;

import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author gzw
 * @description：
 * @since：2024/4/24 02:18
 */

@SuppressWarnings("unused")
@Component
public class ThreadTask {

    public static final Logger LOGGER = LoggerFactory.getLogger(ThreadTask.class);

    @Resource
    private ThreadPoolExecutor kdThreadPool;


    public void send (){
        LOGGER.info("CompletableFuture 爬虫 k8s docker Aigc");
        kdThreadPool.execute(() ->
                        LOGGER.warn("\r\n" +
                                    "                  人生之艰难\r\n" +
                                "               就像那不息之长河\r\n" +
                           "          虽有东去大海之志 却流程缓慢 征程多坚\r\n" +
                    "    然 江河水总有入海之时，而人生之志 却常常难以实现 令人抱恨终身!!\r\n"));
    }
}
