package org.farpost.farpostapi2.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.farpost.farpostapi2.facades.FacadeUtils;
import org.farpost.farpostapi2.repositories.ViewDirsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view_dir")
@RequiredArgsConstructor
public class ViewDirApi {

    private final ViewDirsRepository viewDirsRepository;
    private final FacadeUtils facadeUtils;

    @GetMapping
    private List<ViewDir> getAllViewDirs(){
        return viewDirsRepository.getAllViewDirs();
    }

    @PostMapping("/add")
    private ViewDir addNewViewDir(@Valid @RequestBody ViewDrCityDto viewDrCityDto){
        return viewDirsRepository.save(new ViewDir(viewDrCityDto));
    }

    @DeleteMapping("/{view_dir_id}")
    private ViewDir deleteViewDir(@PathVariable("view_dir_id") Integer viewDirId){
        ViewDir viewDir = viewDirsRepository.deleteViewDirById(viewDirId);
        return facadeUtils.checkElemOnNull(viewDir, viewDirId);
    }

    @PutMapping("/{view_dir_id}")
    private ViewDir updateViewDir(@PathVariable("view_dir_id") Integer viewDirId, @Valid @RequestBody ViewDrCityDto viewDrCityDto){
        ViewDir viewDir = viewDirsRepository.updateViewDirById(viewDrCityDto.getFarpostId(), viewDrCityDto.getName(), viewDirId);
        return facadeUtils.checkElemOnNull(viewDir, viewDirId);
    }


}
