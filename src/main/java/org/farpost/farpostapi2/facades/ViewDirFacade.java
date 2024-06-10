package org.farpost.farpostapi2.facades;

import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
import org.farpost.farpostapi2.enitities.City;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.farpost.farpostapi2.facades.utils.FacadeUtils;
import org.farpost.farpostapi2.repositories.ViewDirsRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ViewDirFacade {

    private final ViewDirsRepository viewDirsRepository;
    private final FacadeUtils facadeUtils;

    @Transactional(readOnly = true)
    public List<ViewDir> getViewDirs(){;
        return viewDirsRepository.getAllViewDirs();
    }
    @Transactional
    public void addViewDir(ViewDrCityDto viewDrCityDto){
         viewDirsRepository.save(new ViewDir(viewDrCityDto));
    }

    @Transactional
    public void deleteViewDir(Integer viewDir){
        facadeUtils.checkAvailability(viewDir, ViewDir.class, true);
        viewDirsRepository.deleteViewDirById(viewDir);
    }

    @Transactional
    public void updateViewDir(Integer viewDirId, ViewDrCityDto viewDrCityDto){
        ViewDir viewDir = facadeUtils.checkAvailability(viewDirId, ViewDir.class, true);
        viewDir.setName(viewDrCityDto.getName());
        viewDir.setFarpostId(viewDrCityDto.getFarpostId());
        viewDirsRepository.save(viewDir);
    }


}
