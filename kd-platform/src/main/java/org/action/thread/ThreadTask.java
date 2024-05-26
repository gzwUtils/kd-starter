package org.action.thread;

import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author gzw
 * @description：
 * @since：2024/4/24 02:18
 */

@SuppressWarnings("unused")
@Component
public class ThreadTask {

    @Resource
    private ThreadPoolExecutor kdThreadPool;


    public void send (){
        kdThreadPool.execute(() -> System.out.println("----------------------------"+Thread.currentThread().getName()));
    }
}
