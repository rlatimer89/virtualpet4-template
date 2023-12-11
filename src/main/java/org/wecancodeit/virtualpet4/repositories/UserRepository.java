package org.wecancodeit.virtualpet4.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.virtualpet4.models.UserModel;

public interface UserRepository extends CrudRepository<UserModel,Long>{
    UserModel findByUserId(String userId);
}
