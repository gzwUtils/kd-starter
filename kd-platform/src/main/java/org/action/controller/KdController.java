package org.action.controller;

import javax.annotation.Resource;
import org.action.thread.ThreadTask;
import org.action.utils.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gzw
 * @description： thread
 * @since：2024/4/24 02:23
 */

@RestController
@RequestMapping("/thr")
public class KdController {

    @Resource
    private ThreadTask threadTask;

    @RequestMapping(value = "/send",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public AjaxResult send (){
        threadTask.send();
        return AjaxResult.success();
    }
}
