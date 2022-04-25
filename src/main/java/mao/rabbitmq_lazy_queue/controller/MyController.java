package mao.rabbitmq_lazy_queue.controller;

import mao.rabbitmq_lazy_queue.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Project name(项目名称)：rabbitMQ_lazy_queue
 * Package(包名): mao.rabbitmq_lazy_queue.controller
 * Class(类名): MyController
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 22:06
 * Version(版本): 1.0
 * Description(描述)：
 * 默认队列内存消耗：
 * Process memory 	656 MiB
 * 懒惰队列内存消耗：
 * Process memory 	1.2 MiB
 */

@Controller
public class MyController
{
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public String test()
    {
        //------------------------------------------------------
        long startTime = System.currentTimeMillis();    //获取开始时间
        //------------------------------------------------------
        log.info("开始发送1000000条数据测试");
        for (int i = 0; i < 1000000; i++)
        {
            String message = "消息" + (i + 1);
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, "", message);
            if (i % 1000 == 0)
            {
                log.info("已发送第" + i / 1000 + "个1000条消息");
            }
        }
        log.info("发送完成");
        //------------------------------------------------------
        long endTime = System.currentTimeMillis();    //获取结束时间
        log.info("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        //------------------------------------------------------
        return "发送完成，" + "程序运行时间：" + (endTime - startTime) + "ms";
        //程序运行时间：52596ms
    }
}
