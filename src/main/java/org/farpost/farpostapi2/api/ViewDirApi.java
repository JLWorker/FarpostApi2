package org.farpost.farpostapi2.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.farpost.farpostapi2.facades.ViewDirFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view_dir")
@RequiredArgsConstructor
public class ViewDirApi {

    private final ViewDirFacade viewDirFacade;


    @GetMapping
    public List<ViewDir> getAllViewDirs(){
        return viewDirFacade.getViewDirs();
    }

    @PostMapping("/add")
    public ViewDir addNewViewDir(@Valid @RequestBody ViewDrCityDto viewDrCityDto){
        return viewDirFacade.addViewDir(viewDrCityDto);
    }

    @DeleteMapping("/{view_dir_id}")
    public ViewDir deleteViewDir(@PathVariable("view_dir_id") Integer viewDirId){
        return viewDirFacade.deleteViewDir(viewDirId);
    }

    @PutMapping("/{view_dir_id}")
    public ViewDir updateViewDir(@PathVariable("view_dir_id") Integer viewDirId, @Valid @RequestBody ViewDrCityDto viewDrCityDto){
        return viewDirFacade.updateViewDir(viewDirId, viewDrCityDto);
    }

}
