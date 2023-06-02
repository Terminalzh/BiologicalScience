package com.neutech.mammalia.aspect;

import com.neutech.mammalia.mapper.BannerMapper;
import com.neutech.mammalia.mapper.WorksMapper;
import jakarta.annotation.Resource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CascadeUpdates {
    @Resource
    private WorksMapper worksMapper;
    @Resource
    private BannerMapper bannerMapper;

    @Before("execution(* com.neutech.mammalia.service.impl.UserServiceImpl.deleteUserById(java.lang.Integer)) && args(userId)")
    public void CascadeDeleteUser(Integer userId) {
        Integer worksId = worksMapper.inquireWorksIdByUserId(userId);
        bannerMapper.deleteBannerByWorksId(worksId);
        worksMapper.deleteWorksByUserId(userId);
    }
}
