package org.farpost.farpostapi2.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.farpost.farpostapi2.dto.city_viewdir_dto.ViewDrCityDto;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.farpost.farpostapi2.facades.ViewDirFacade;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/view_dir")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication")
@Tag(name = "view_dir", description = "View dir controller api")
public class ViewDirApi {

    private final ViewDirFacade viewDirFacade;

    @Operation(summary = "Get all view dirs")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation")
    })
    @GetMapping
    public List<ViewDir> getAllViewDirs(){
        return viewDirFacade.getViewDirs();
    }

    @Operation(summary = "Add new view dir")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", content = @Content(), description = "View dir already exist or validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PostMapping()
    public void addNewViewDir(@Valid @RequestBody ViewDrCityDto viewDrCityDto){
        viewDirFacade.addViewDir(viewDrCityDto);
    }


    @Operation(summary = "Delete view dir")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "view_dir_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "View dir not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @DeleteMapping("/{view_dir_id}")
    public void deleteViewDir(@PathVariable("view_dir_id") Integer viewDirId){
        viewDirFacade.deleteViewDir(viewDirId);
    }

    @Operation(summary = "Update view dir")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Access token (ONLY WITH ROLE ADMIN!!)", example = "Bearer uhdYUhskn879jd...")
    @Parameter(name = "view_dir_id",in = ParameterIn.PATH, example = "12" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", content = @Content(), description = "View dir not found"),
            @ApiResponse(responseCode = "409", content = @Content(), description = "Validation exception"),
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content())
    })
    @PutMapping("/{view_dir_id}")
    public void updateViewDir(@PathVariable("view_dir_id") Integer viewDirId, @Valid @RequestBody ViewDrCityDto viewDrCityDto){
        viewDirFacade.updateViewDir(viewDirId, viewDrCityDto);
    }

}

