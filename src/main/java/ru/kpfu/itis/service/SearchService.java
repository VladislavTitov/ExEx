package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dao.SearchDao;
import ru.kpfu.itis.dto.common.SingleCourse;
import ru.kpfu.itis.model.Course;

import java.util.Collections;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    @Qualifier(value = "courseSearchDao")
    private SearchDao<Course> courseSearchDao;

    public List<SingleCourse> searchCourses(String request) {
        try {
            return AnnotationConverter.convertArray(courseSearchDao.search(request), SingleCourse.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
