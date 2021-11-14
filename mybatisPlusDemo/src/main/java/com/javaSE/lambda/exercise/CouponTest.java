package com.javaSE.lambda.exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fdn
 * @since 2021-09-18 16:11
 */
public class CouponTest {

    public static void main(String[] args) throws JsonProcessingException {
        List<CouponResponse> coupons = getCoupons();

        // TODO 对优惠券统计数量

        Map<Long, CouponInfo> collect = coupons.stream()
                .collect(Collectors.toMap(
                        CouponResponse::getId,// 以id为key去重
                        couponResponse -> {
                            CouponInfo couponInfo = new CouponInfo();
                            couponInfo.setId(couponResponse.getId());
                            couponInfo.setName(couponResponse.getName());
                            couponInfo.setCondition(couponResponse.getCondition());
                            couponInfo.setDenominations(couponResponse.getDenominations());
                            couponInfo.setNum(1);
                            return couponInfo;//注意不要忘记了return语句
                        },
                        (pre, next) -> {// 相同id的优惠券，保留前者，并给前者num +1
                            pre.setNum(pre.getNum() + 1);
                            return pre;
                        }
                ));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(collect));


    }

    private static List<CouponResponse> getCoupons() {
        return Lists.newArrayList(
                new CouponResponse(1L, "满5减4", 500L, 400L),
                new CouponResponse(1L, "满5减4", 500L, 400L),
                new CouponResponse(2L, "满10减9", 1000L, 900L),
                new CouponResponse(3L, "满60减50", 6000L, 5000L)
        );
    }

    @Data
    @AllArgsConstructor
    static class CouponResponse {
        private Long id;
        private String name;
        private Long condition;
        private Long denominations;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class CouponInfo {
        private Long id;
        private String name;
        private Integer num;
        private Long condition;
        private Long denominations;
    }
}
