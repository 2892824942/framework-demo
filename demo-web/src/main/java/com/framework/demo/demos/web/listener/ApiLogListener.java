/**
 * 默认日志处理移动到框架层,如需自定义,参考一下代码,可覆盖默认日志监听处理器
 */
//package com.framework.demo.demos.web.listener;
//
//import com.ty.mid.framework.common.util.JsonUtils;
//import com.ty.mid.framework.web.core.model.event.ApiAccessLogEvent;
//import com.ty.mid.framework.web.core.model.event.ApiErrorLogEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class ApiLogListener {
//
//    @EventListener
//    public void onApiAccessLogEvent(ApiAccessLogEvent event) {
//        log.info("api access log eventData: \r\n{}", JsonUtils.toJson(event.getSource()));
//    }
//
//    @EventListener
//    public void onApiErrorLogEvent(ApiErrorLogEvent event) {
//        log.error("api error log eventData: {}", JsonUtils.toJson(event.getSource()));
//    }
//}
