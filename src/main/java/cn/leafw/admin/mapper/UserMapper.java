package cn.leafw.admin.mapper;

import cn.leafw.admin.model.entity.UserDO;
import cn.leafw.admin.model.vo.UserPermissionVO;
import cn.leafw.admin.model.vo.UserQueryVO;
import cn.leafw.admin.model.vo.UserVO;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
public interface UserMapper extends BaseMapper<UserDO> {

    List<UserVO> selectUserList(UserQueryVO userQueryVO);
}
