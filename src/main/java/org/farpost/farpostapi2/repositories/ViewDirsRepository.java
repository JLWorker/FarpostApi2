package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ViewDirsRepository extends CrudRepository<ViewDir, Integer> {

    @Query(value = "SELECT viewDir FROM ViewDir viewDir")
    List<ViewDir> getAllViewDirs();

    @Modifying
    @Query(value = "DELETE FROM view_dirs WHERE id =?1", nativeQuery = true)
    void deleteViewDirById(Integer viewDirId);

}
