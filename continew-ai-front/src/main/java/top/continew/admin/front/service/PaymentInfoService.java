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

package top.continew.admin.front.service;

import jakarta.servlet.http.HttpServletResponse;
import top.continew.admin.front.model.query.PaymentInfoQuery;
import top.continew.admin.front.model.resp.PaymentInfoDetailResp;
import top.continew.admin.front.model.resp.PaymentInfoResp;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;

import java.util.List;
import java.util.Map;

/**
 * 支付信息业务接口
 *
 * @author weiran
 * @since 2024/03/25 23:37
 */
public interface PaymentInfoService {
    /**
     * Create payment info for ali pay.
     * 为支付创建日志记录
     * 
     * @param params the params
     */
    void createPaymentInfoForAliPay(Map<String, String> params);

    PageResp<PaymentInfoResp> page(PaymentInfoQuery query, PageQuery pageQuery);

    List<PaymentInfoResp> list(PaymentInfoQuery query, SortQuery sortQuery);

    PaymentInfoDetailResp get(Long id);

    void delete(List<Long> ids);

    void export(PaymentInfoQuery query, SortQuery sortQuery, HttpServletResponse response);
}
