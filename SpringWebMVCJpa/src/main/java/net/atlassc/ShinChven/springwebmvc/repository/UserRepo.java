package net.atlassc.ShinChven.springwebmvc.repository;

import net.atlassc.ShinChven.springwebmvc.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ShinChven on 15/3/29.
 */
@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    @Override
    UserEntity save(UserEntity entity);

    @Override
    List<UserEntity> findAll();
}
