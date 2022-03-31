package com.example.tongji.dto;

import com.example.tongji.entity.User;
import com.example.tongji.vo.BaseVO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseVO {

	private List<User> userList;

}
