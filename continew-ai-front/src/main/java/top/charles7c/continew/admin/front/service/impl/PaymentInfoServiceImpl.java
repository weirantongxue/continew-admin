/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.charles7c.continew.admin.front.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.charles7c.continew.admin.common.enums.PayType;
import top.charles7c.continew.admin.front.mapper.PaymentInfoMapper;
import top.charles7c.continew.admin.front.model.entity.PaymentInfoDO;
import top.charles7c.continew.admin.front.model.query.PaymentInfoQuery;
import top.charles7c.continew.admin.front.model.resp.PaymentInfoDetailResp;
import top.charles7c.continew.admin.front.model.resp.PaymentInfoResp;
import top.charles7c.continew.admin.front.service.PaymentInfoService;
import top.charles7c.continew.starter.data.mybatis.plus.query.QueryWrapperHelper;
import top.charles7c.continew.starter.extension.crud.model.query.PageQuery;
import top.charles7c.continew.starter.extension.crud.model.query.SortQuery;
import top.charles7c.continew.starter.extension.crud.model.resp.PageResp;
import top.charles7c.continew.starter.file.excel.util.ExcelUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付信息业务实现
 *
 * @author weiran
 * @since 2024/03/25 23:37
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentInfoServiceImpl implements PaymentInfoService {
    private final PaymentInfoMapper paymentInfoMapper;

    /**
     * 记录支付宝的支付日志
     *
     * @param params the params 支付通知参数
     */
    @Override
    public void createPaymentInfoForAliPay(Map<String, String> params) {

        log.info("记录支付宝支付日志.....");
        //创建支付信息对象
        PaymentInfoDO paymentInfo = new PaymentInfoDO();
        paymentInfo.setOrderNo(params.get("out_trade_no"));
        paymentInfo.setPaymentType(PayType.ALIPAY.getType());
        //设置业务编号(支付宝对应的是trade_no)
        paymentInfo.setTransactionId(params.get("trade_no"));
        //设置支付的场景
        paymentInfo.setTradeType("电脑网站支付");
        //设置交易状态
        paymentInfo.setTradeState(params.get("trade_status"));
        //设置交易金额，此处依旧需要转换(支付宝端对应的是元，数据库中对应分)
        int totalAmount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal("100")).intValue();
        paymentInfo.setPayerTotal(totalAmount);

        //之后设置备注信息，需要将平台传入的map集合信息转成字符串类型存入数据库
        Gson gson = new Gson();
        String content = gson.toJson(params, HashMap.class);
        paymentInfo.setContent(content);
        //将信息插入数据库中
        paymentInfoMapper.insert(paymentInfo);

    }

    @Override
    public PageResp<PaymentInfoResp> page(PaymentInfoQuery query, PageQuery pageQuery) {
        QueryWrapper<PaymentInfoDO> queryWrapper = QueryWrapperHelper.build(query);
        IPage<PaymentInfoDO> page = paymentInfoMapper.selectPage(pageQuery.toPage(), queryWrapper);
        return PageResp.build(page, PaymentInfoResp.class);
    }

    @Override
    public List<PaymentInfoResp> list(PaymentInfoQuery query, SortQuery sortQuery) {
        QueryWrapper<PaymentInfoDO> queryWrapper = QueryWrapperHelper.build(query);
        List<PaymentInfoDO> list = paymentInfoMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(list, PaymentInfoResp.class);
    }

    @Override
    public PaymentInfoDetailResp get(Long id) {
        PaymentInfoDO paymentInfoDO = paymentInfoMapper.selectById(id);
        return BeanUtil.copyProperties(paymentInfoDO, PaymentInfoDetailResp.class);
    }

    @Override
    public void delete(List<Long> ids) {
        paymentInfoMapper.deleteBatchIds(ids);
    }

    @Override
    public void export(PaymentInfoQuery query, SortQuery sortQuery, HttpServletResponse response) {
        List<PaymentInfoResp> paymentInfoRespList = list(query, sortQuery);
        List<PaymentInfoDetailResp> paymentInfoDetailRespList = BeanUtil
            .copyToList(paymentInfoRespList, PaymentInfoDetailResp.class);
        ExcelUtils.export(paymentInfoDetailRespList, "导出数据", PaymentInfoDetailResp.class, response);
    }

}
