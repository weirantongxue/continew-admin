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

package top.continew.admin.common.enums;

public enum AliPayTradeState {
    /**
     * 支付成功
     */
    SUCCESS("TRADE_SUCCESS"),
    /**
     * 未支付
     */
    NOTPAY("WAIT_BUYER_PAY"),
    /**
     * 订单关闭
     */
    CLOSED("TRADE_SUCCESS"),
    /**
     * 退款成功
     */
    REFUND_SUCCESS("REFUND_SUCCESS"),

    /**
     * 退款失败
     */
    REFUND_ERROR("REFUND_ERROR");

    private final String type;

    private AliPayTradeState(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
