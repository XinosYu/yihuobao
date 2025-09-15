package com.yihuobao.yhb.entity.po;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2025-07-20
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Audit implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("traceCode")
    private String traceCode;

    @TableField("aiBeta")
    private String aiBeta;

    @TableField("aiPass")
    private String aiPass;

    @TableField("aiPercent")
    private String aiPercent;

    @TableField("aiAcceptReason")
    private String aiAcceptReason;

    @TableField("aiRejectReason")
    private String aiRejectReason;

    @TableField("adminerAccout")
    private String adminerAccout;

    @TableField("auditResult")
    private Integer auditResult;

    @TableField("auditReason")
    private String auditReason;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;

    private String hash;


}
