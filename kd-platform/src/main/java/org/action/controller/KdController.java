package org.action.controller;

import javax.annotation.Resource;
import org.action.thread.ThreadTask;
import org.action.utils.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gzw
 * @description：
 * @since：2024/4/24 02:23
 */

@RestController
@RequestMapping("/thr")
public class KdController {

    @Resource
    private ThreadTask threadTask;

    @RequestMapping("/send")
    public AjaxResult send (){
        threadTask.send();
        return AjaxResult.success();
    }
}
