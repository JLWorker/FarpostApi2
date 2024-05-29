package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ViewDirsRepository extends CrudRepository<ViewDir, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT viewDir FROM ViewDir viewDir")
    List<ViewDir> getAllViewDirs();

    @Transactional
    @Query(value = "DELETE FROM view_dirs WHERE id =?1 RETURNING *", nativeQuery = true)
    ViewDir deleteViewDirById(Integer viewDirId);

    @Transactional
    @Query(value = "UPDATE view_dir SET farpost_id = ?1, name = ?2 WHERE id = ?3 RETURNING *", nativeQuery = true)
    ViewDir updateViewDirById(Integer farpostId, String name, Integer id);


}
