package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Payment;
import cn.wolfcode.edu.query.PaymentQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Payment record);

    Payment selectByPrimaryKey(Long id);

    List<Payment> selectAll();

    int updateByPrimaryKey(Payment record);

    int queryForCount(PaymentQueryObject qo);

    List<Payment> queryForList(PaymentQueryObject qo);

    void audit(@Param("auditorId") Long auditorId, @Param("paymentId") Long paymentId);
}