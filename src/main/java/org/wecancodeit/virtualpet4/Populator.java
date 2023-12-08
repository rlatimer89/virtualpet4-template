package org.wecancodeit.virtualpet4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.virtualpet4.enums.PetTypeEnum;
import org.wecancodeit.virtualpet4.models.PetModel;
import org.wecancodeit.virtualpet4.models.ShelterModel;
import org.wecancodeit.virtualpet4.repositories.PetRepository;
import org.wecancodeit.virtualpet4.repositories.ShelterRepository;

import jakarta.annotation.Resource;

@Component
public class Populator implements CommandLineRunner {

    @Resource
    private PetRepository petRepository;
    @Resource
    private ShelterRepository shelterRepository;

    public Populator(PetRepository petRepository, ShelterRepository shelterRepository) {
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ShelterModel shelter1 = new ShelterModel("Shelter 1", "1234567890", "shelter1@No.com");
        shelter1 = shelterRepository.save(shelter1);
        ShelterModel shelter2 = new ShelterModel("Shelter 2", "1234567890", "shelter2@No.com");
        shelter2 = shelterRepository.save(shelter2);
        ShelterModel shelter3 = new ShelterModel("Shelter 3", "1234567890", "shelter3@No.com");
        shelter3 = shelterRepository.save(shelter3);

        PetModel pet1 = new PetModel("Pet1", PetTypeEnum.CAT, 2, shelter1);
        petRepository.save(pet1);
        PetModel pet2 = new PetModel("Pet2", PetTypeEnum.DOG, 2, shelter1);
        petRepository.save(pet2);
        PetModel pet3 = new PetModel("Pet3", PetTypeEnum.Other, 2, shelter1);
        petRepository.save(pet3);

        PetModel pet4 = new PetModel("Pet4", PetTypeEnum.CAT, 2, shelter2);
        petRepository.save(pet4);
        PetModel pet5 = new PetModel("Pet5", PetTypeEnum.DOG, 2, shelter2);
        petRepository.save(pet5);
        PetModel pet6 = new PetModel("Pet6", PetTypeEnum.Other, 2, shelter2);
        petRepository.save(pet6);

        PetModel pet7 = new PetModel("Pet7", PetTypeEnum.CAT, 2, shelter3);
        petRepository.save(pet7);
        PetModel pet8 = new PetModel("Pet8", PetTypeEnum.DOG, 2, shelter3);
        petRepository.save(pet8);
        PetModel pet9 = new PetModel("Pet9", PetTypeEnum.Other, 2, shelter3);
        petRepository.save(pet9);
    }

}
