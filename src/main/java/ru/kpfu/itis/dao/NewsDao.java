package ru.kpfu.itis.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.exceptions.shared.NoSuchIdException;
import ru.kpfu.itis.model.Course;
import ru.kpfu.itis.model.Interest;
import ru.kpfu.itis.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class NewsDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    private int pageSize = 2;

    public List<Course> getNews(int pageNumber, Long userId) {
        User user = entityManager.createQuery("from User as u where u.id = " + userId, User.class).getSingleResult();
        if (user == null) {
            throw new NoSuchIdException("User with id = " + userId + " doesn't exist!");
        }
        TypedQuery<Course> query = entityManager
                .createQuery(
                        "select distinct c from Course c join c.interests interest where interest.id in :interests order by c.title",
                        Course.class
                );
        query.setParameter("interests", user.getInterests().stream().map(Interest::getId).collect(Collectors.toList()));

        query.setFirstResult(pageNumber * pageSize).setMaxResults(pageSize);

        List<Course> news = query.getResultList();
        return news;
    }
}
