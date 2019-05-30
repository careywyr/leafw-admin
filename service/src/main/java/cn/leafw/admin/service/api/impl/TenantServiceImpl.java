package cn.leafw.admin.service.api.impl;

import cn.leafw.admin.dao.entity.Tenant;
import cn.leafw.admin.dao.mapper.TenantMapper;
import cn.leafw.admin.service.api.TenantService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户 服务实现类
 * </p>
 *
 * @author CareyWYR
 * @since 2019-05-30
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

}
