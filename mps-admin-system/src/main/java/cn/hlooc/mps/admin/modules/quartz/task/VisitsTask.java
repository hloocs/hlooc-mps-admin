package cn.hlooc.mps.admin.modules.quartz.task;

import cn.hlooc.mps.admin.modules.monitor.service.VisitsService;
import cn.hlooc.mps.admin.modules.monitor.service.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hlooc
 * @date 2018-12-25
 */
@Component
public class VisitsTask {

    @Autowired
    private VisitsService visitsService;

    public void run(){
        visitsService.save();
    }
}
