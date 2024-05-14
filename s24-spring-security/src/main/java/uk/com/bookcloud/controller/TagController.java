package uk.com.bookcloud.controller;


import uk.com.bookcloud.base.BaseURI;
import uk.com.bookcloud.common.ResponseData;
import uk.com.bookcloud.dto.tag.TagCreateDTO;
import uk.com.bookcloud.dto.tag.TagDTO;
import uk.com.bookcloud.dto.tag.TagUpdateDTO;
import uk.com.bookcloud.hateoes.HateoasAssembler;
import uk.com.bookcloud.service.TagService;
import uk.com.bookcloud.utils.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseURI.API + BaseURI.V1 + BaseURI.TAGS)
public class TagController {

    private final TagService tagService;
    private final HateoasAssembler<TagDTO> hateoasAssembler;


    @Operation(summary = "Get tags", description = "Get tags")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<ResponseData<PageRequest<List<TagDTO>>>> getAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "id") String sortField,
            @RequestParam(value = "order", defaultValue = "asc") String sortOrder,
            @RequestParam(value = "filter", required = false) String filterField,
            @RequestParam(value = "value", required = false) String filterValue) {
        PageRequest<List<TagDTO>> pageRequest = tagService.getAll(page, size, sortField, sortOrder, filterField, filterValue);
        hateoasAssembler.addLinks(pageRequest.getResultList());
        return ResponseData.success200(pageRequest);
    }

    @Operation(summary = "Get by ID", description = "Get by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<TagDTO>> getById(@PathVariable(value = "id") Long id) {
        TagDTO tag = tagService.getById(id);
        hateoasAssembler.addLinks(tag);
        return ResponseData.success200(tag);
    }


    @Operation(summary = "Create Tag", description = "Create Tag")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<ResponseData<TagDTO>> create(@Valid @RequestBody TagCreateDTO dto) {
        TagDTO tag = tagService.create(dto);
        hateoasAssembler.addLinks(tag);
        return ResponseData.success201(tag);
    }

    @Operation(summary = "Update Tag", description = "Update Tag")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<TagDTO>> update(@PathVariable(value = "id") Long id,
                                                       @Valid @RequestBody TagUpdateDTO dto) {
        TagDTO tag = tagService.update(dto, id);
        hateoasAssembler.addLinks(tag);
        return ResponseData.success202(tag);
    }


    @Operation(summary = "Delete Tag", description = "Delete Tag")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return ResponseData.success204(tagService.delete(id));
    }

}
