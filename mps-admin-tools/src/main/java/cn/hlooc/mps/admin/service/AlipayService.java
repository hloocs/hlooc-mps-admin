package cn.hlooc.mps.admin.service;

import cn.hlooc.mps.admin.domain.AlipayConfig;
import cn.hlooc.mps.admin.domain.vo.TradeVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author hlooc
 * @date 2018-12-31
 */
public interface AlipayService {

    /**
     * 处理来自PC的交易请求
     * @param alipay 支付宝配置
     * @param trade 交易对象
     * @return 交易结果
     * @throws Exception 异常信息
     */
    String toPayAsPC(AlipayConfig alipay, TradeVo trade) throws Exception;

    /**
     * 处理来自手机网页的交易请求
     * @param alipay 支付宝配置
     * @param trade 交易对象
     * @return 交易结果
     * @throws Exception 异常信息
     */
    String toPayAsWeb(AlipayConfig alipay, TradeVo trade) throws Exception;

    /**
     * 查询配置
     * @return
     */
    @Cacheable(key = "'1'")
    AlipayConfig find();

    /**
     * 更新配置
     * @param alipayConfig
     * @return
     */
    @CachePut(key = "'1'")
    AlipayConfig update(AlipayConfig alipayConfig);
}
