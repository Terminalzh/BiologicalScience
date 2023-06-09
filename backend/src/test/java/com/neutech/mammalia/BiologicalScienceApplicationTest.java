package com.neutech.mammalia;

import com.neutech.mammalia.bean.Category;
import com.neutech.mammalia.bean.CategoryCount;
import com.neutech.mammalia.mapper.CategoryCountMapper;
import com.neutech.mammalia.mapper.CategoryMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest
public class BiologicalScienceApplicationTest {

    @Resource
    CategoryCountMapper categoryCountMapper;
    @Resource
    CategoryMapper categoryMapper;

    @Test
    void test() {
        List<CategoryCount> categories = categoryMapper.inquireAllCategories();
        updateInheritance(categories);
    }

    public void updateInheritance(List<CategoryCount> dataList) {
        // 第一步：将List转换为Map便于查找父节点数据
        Map<Integer, CategoryCount> dataMap = dataList.stream().collect(Collectors.toMap(CategoryCount::getId, Function.identity()));

        // 第二步：更新每个对象的inheritance字段
        for (CategoryCount data : dataList) {
            CategoryCount categoryCount = new CategoryCount();
            StringBuilder sb = new StringBuilder();
            sb.append(data.getId());
            // 获取指定节点和其所有祖先节点的id
            CategoryCount temp = data;
            while (data.getParentId() != null) {
                data = dataMap.get(data.getParentId());
                sb.insert(0, ".");
                sb.insert(0, data.getId());
            }
            categoryCount.setId(temp.getId());
            categoryCount.setCategorizedInheritance(sb.toString());
            categoryCount.setSubClass(temp.getSubClass());
            categoryCount.setOrderCount(temp.getOrderCount());
            categoryCount.setFamily(temp.getFamily());
            categoryCount.setGenus(temp.getGenus());
            categoryCount.setSpecies(temp.getSpecies());
            categoryCountMapper.add(categoryCount);
        }
    }
}
