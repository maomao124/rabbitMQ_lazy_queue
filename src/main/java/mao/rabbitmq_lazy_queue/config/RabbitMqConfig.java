package mao.rabbitmq_lazy_queue.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Project name(项目名称)：rabbitMQ_lazy_queue
 * Package(包名): mao.rabbitmq_lazy_queue.config
 * Class(类名): RabbitMqConfig
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 22:06
 * Version(版本): 1.0
 * Description(描述)： 无
 */


@Configuration
public class RabbitMqConfig
{
    /**
     * The constant EXCHANGE.
     */
    //交换机
    public static final String EXCHANGE = "exchange";
    /**
     * The constant DEFAULT_QUEUE.
     */
    //队列
    public static final String DEFAULT_QUEUE = "default_queue";
    /**
     * The constant LAZY_QUEUE.
     */
    //惰性队列
    public static final String LAZY_QUEUE = "lazy_queue";

    /**
     * Exchange fanout exchange.
     *
     * @return the fanout exchange
     */
    @Bean
    public FanoutExchange exchange()
    {
        return new FanoutExchange(EXCHANGE, false, false, null);
    }

    /**
     * Default queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue default_queue()
    {
        return new Queue(DEFAULT_QUEUE, false, false, false, null);
    }

    /**
     * Lazy queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue lazy_queue()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("x-queue-mode", "lazy");
        return new Queue(LAZY_QUEUE, false, false, false, map);
    }

    /**
     * Exchange bind default queue binding.
     *
     * @param exchange      the exchange
     * @param default_queue the default queue
     * @return the binding
     */
    @Bean
    public Binding exchange_bind_default_queue(@Qualifier("exchange") FanoutExchange exchange,
                                               @Qualifier("default_queue") Queue default_queue)
    {
        return BindingBuilder.bind(default_queue).to(exchange);
    }

    /**
     * Exchange bind lazy queue binding.
     *
     * @param exchange   the exchange
     * @param lazy_queue the lazy queue
     * @return the binding
     */
    @Bean
    public Binding exchange_bind_lazy_queue(@Qualifier("exchange") FanoutExchange exchange,
                                               @Qualifier("lazy_queue") Queue lazy_queue)
    {
        return BindingBuilder.bind(lazy_queue).to(exchange);
    }
}
