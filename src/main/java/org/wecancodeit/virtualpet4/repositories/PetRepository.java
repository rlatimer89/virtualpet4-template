package org.wecancodeit.virtualpet4.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.virtualpet4.models.PetModel;

public interface PetRepository extends CrudRepository<PetModel, Long> {
}
