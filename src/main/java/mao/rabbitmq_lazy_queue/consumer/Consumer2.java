package mao.rabbitmq_lazy_queue.consumer;

import mao.rabbitmq_lazy_queue.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Project name(项目名称)：rabbitMQ_lazy_queue
 * Package(包名): mao.rabbitmq_lazy_queue.consumer
 * Class(类名): Consumer2
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 22:55
 * Version(版本): 1.0
 * Description(描述)： 懒惰队列消费
 *
 * 程序运行时间：32249ms
 */

@Component
public class Consumer2
{
    private static final Logger log = LoggerFactory.getLogger(Consumer1.class);

    private int i = 0;
    long startTime;    //获取开始时间

    @RabbitListener(queues = {RabbitMqConfig.LAZY_QUEUE})
    public void getMessage()
    {
        synchronized (this)
        {
            if (i == 0)
            {
                log.info("消费者2开始消费懒惰队列的消息");
                startTime = System.currentTimeMillis();
            }
            if (i % 1000 == 0)
            {
                log.info("已接收第" + i / 1000 + "个1000条消息");
            }
            if (i == 999999)
            {
                long endTime = System.currentTimeMillis();    //获取结束时间
                log.info("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
            }
            i++;
        }
        //------------------------------------------------------
    }
}
