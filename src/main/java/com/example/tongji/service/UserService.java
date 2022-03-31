package com.example.tongji.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tongji.dto.UserDTO;
import com.example.tongji.entity.User;
import com.example.tongji.mapper.UserMapper;
import com.example.tongji.vo.UserVO;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 示例用户服务类
 *
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 保存用户信息
	 *
	 * @return 影响行数
	 */
	public Integer saveUser(UserVO userVO) {
		User user = new User();
		// 将请求user中的同名参数拷贝到实体类user中
		BeanUtils.copyProperties(userVO, user);
		return userMapper.insert(user);
	}

	/**
	 * 更新用户信息
	 *
	 * @return 影响行数
	 */
	public Integer updateUser(UserVO userVO) {
		User user = new User();
		user.setId(userVO.getId());
		user.setUserAge(userVO.getUserAge());
		return userMapper.updateById(user);
	}

	/**
	 * 根据id查询用户
	 *
	 * @return 用户信息
	 */
	public UserDTO queryById(Integer id) {
		// 根据 ID 查询
		User user = userMapper.selectById(id);
		return new UserDTO(ImmutableList.of(user));
	}

	/**
	 * 分页查询用户
	 *
	 * @return 符合条件的用户信息
	 */
	public UserDTO queryUser(UserVO userVO) {

		/**
		 *      写法一
		 * 		QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("user_name", userVO.getUserName())
		 * 			.eq("user_age", userVO.getUserAge())
		 * 			.eq("user_title", userVO.getUserTitle());
		 *
		 * 		写法二，建议用写法二    ，记得判断传入的值是否为空
		 * 		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
		 * 			.eq(StringUtils.isNotEmpty(userVO.getUserName()), User::getUserName, userVO.getUserName())
		 * 			.eq(userVO.getUserAge() != null, User::getUserAge, userVO.getUserAge())
		 * 			.eq(User::getUserTitle, userVO.getUserTitle());
		 */


		// 传入entity，相当于allEq，查询出与传入的所有条件相等的值，会自动过滤为空的值
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		QueryWrapper<User> wrapper = new QueryWrapper<User>().setEntity(user);

		// 分页查询 2 条
		Page<User> page = new Page<>(userVO.getPageNo(), userVO.getPageSize());


		Page<User> userPage = userMapper.selectPage(page, wrapper);
		List<User> records = userPage.getRecords();
		long total = userPage.getTotal();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserList(records);
		userDTO.setPageNo(userVO.getPageNo());
		userDTO.setPageSize(userVO.getPageSize());
		userDTO.setTotal(total);
		return userDTO;
	}

	/**
	 * 根据id删除用户
	 *
	 * @return 影响行数
	 */
	public Integer deleteById(Integer id) {
		return userMapper.deleteById(id);
	}
}
