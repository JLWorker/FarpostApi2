package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewDirsRepository extends CrudRepository<ViewDir, Integer> {

    @Query(value = "SELECT viewDir FROM ViewDir viewDir")
    List<ViewDir> getAllViewDirs();

    @Query(value = "DELETE FROM view_dirs WHERE id =:id RETURNING *", nativeQuery = true)
    ViewDir deleteViewDirById(@Param("id") Integer viewDirId);

    @Query(value = "UPDATE view_dir SET farpost_id = :farpostId, name = :name WHERE id = :id RETURNING *", nativeQuery = true)
    ViewDir updateViewDirById(@Param("farpostId") Integer farpostId, @Param("name") String name, @Param("id") Integer id);


}
