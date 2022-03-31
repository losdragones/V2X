package com.example.tongji.dto;

import com.example.tongji.entity.Result;
import com.example.tongji.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName   ResultDTO
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO extends BaseVO {

    private List<Result> resultList;
}
