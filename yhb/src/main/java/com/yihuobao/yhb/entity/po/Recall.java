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
 * @since 2025-07-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Recall implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    @TableField("recallRecord")
    private String recallRecord;

    @TableField("recallHistory")
    private String recallHistory;

    @TableField("recallReason")
    private String recallReason;

    @TableField("recallOfficer")
    private String recallOfficer;

    @TableField("createdAt")
    private LocalDateTime createdAt;

    @TableField("updatedAt")
    private LocalDateTime updatedAt;

  @TableField("traceCode")
  private String traceCode;


}
