package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.converter.AnnotationConverter;
import ru.kpfu.itis.dao.NewsDao;
import ru.kpfu.itis.dto.common.SingleCourse;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;
import ru.kpfu.itis.model.Course;
import ru.kpfu.itis.model.Interest;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repo.CourseRepo;
import ru.kpfu.itis.repo.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private NewsDao newsDao;

    @Autowired
    public NewsService(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public List<SingleCourse> getNews(int pageNumber, Long userId) {
        List<SingleCourse> response = AnnotationConverter.convertArray(newsDao.getNews(pageNumber, userId), SingleCourse.class);
        return response;
    }

}
