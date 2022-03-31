package com.example.tongji.dto;

import com.example.tongji.entity.Imus;
import com.example.tongji.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName   ImusDTO
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImusDTO extends BaseVO {
    private List<Imus> imusList;
}
