package cn.leafw.admin.service.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author carey
 * @date 2019/3/25
 */
public class BeanConverter {

    /**
     * bean 转换器
     *
     * @param source
     * @param targetClass
     * @param <T>         返回类型
     * @return
     */
    public static <T> T convertOne(Object source, Class<T> targetClass) {
        T ret = null;
        try {
            ret = targetClass.newInstance();
            BeanUtils.copyProperties(source, ret);
            return ret;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * list 转换
     *
     * @param sources
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> convertList(List<?> sources, Class<T> targetClass) {
        List<T> ret = new ArrayList<>();
        sources.forEach(source -> {
            ret.add(convertOne(source, targetClass));
        });
        return ret;
    }

}
