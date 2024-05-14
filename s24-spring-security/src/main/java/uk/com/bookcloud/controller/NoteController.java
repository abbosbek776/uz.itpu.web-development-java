package uk.com.bookcloud.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uk.com.bookcloud.base.BaseURI;
import uk.com.bookcloud.common.ResponseData;
import uk.com.bookcloud.dto.note.NoteCreateDTO;
import uk.com.bookcloud.dto.note.NoteDTO;
import uk.com.bookcloud.dto.note.NoteUpdateDTO;
import uk.com.bookcloud.dto.note_tag.NoteRequestDTO;
import uk.com.bookcloud.hateoes.HateoasAssembler;
import uk.com.bookcloud.service.NoteService;
import uk.com.bookcloud.utils.PageRequest;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseURI.API + BaseURI.V1 + BaseURI.NOTES)
public class NoteController {

    private final NoteService noteService;
    private final HateoasAssembler<NoteDTO> hateoasAssembler;

    @GetMapping
    public ResponseEntity<ResponseData<PageRequest<List<NoteDTO>>>> getAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "id") String sortField,
            @RequestParam(value = "order", defaultValue = "asc") String sortOrder,
            @RequestParam(value = "filter", required = false) String filterField,
            @RequestParam(value = "value", required = false) String filterValue) {
        PageRequest<List<NoteDTO>> pageRequest = noteService.getAll(page, size, sortField, sortOrder, filterField, filterValue);
        hateoasAssembler.addLinks(pageRequest.getResultList());
        return ResponseData.success200(pageRequest);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<NoteDTO>> getById(@PathVariable(value = "id") Long id) {
        NoteDTO dto = noteService.getById(id);
        hateoasAssembler.addLinks(dto);
        return ResponseData.success200(dto);
    }


    @GetMapping(BaseURI.BY_NAME)
    public ResponseEntity<ResponseData<PageRequest<List<NoteDTO>>>> getByName(@RequestParam(value = "name") String name, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                              @RequestParam(value = "sort", defaultValue = "id") String sortField,
                                                                              @RequestParam(value = "order", defaultValue = "asc") String sortOrder,
                                                                              @RequestParam(value = "filter", required = false) String filterField,
                                                                              @RequestParam(value = "value", required = false) String filterValue) {
        PageRequest<List<NoteDTO>> pageRequest = noteService.getAllByName(name, page, size, sortField, sortOrder, filterField, filterValue);
        hateoasAssembler.addLinks(pageRequest.getResultList());
        return ResponseData.success200(pageRequest);
    }

    @GetMapping(BaseURI.SEARCH_BY_TAGS)
    public ResponseEntity<ResponseData<PageRequest<List<NoteDTO>>>> getByTags(@RequestParam(value = "tagNames") Set<String> tagNameList,
                                                                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                              @RequestParam(value = "sort", defaultValue = "id") String sortField,
                                                                              @RequestParam(value = "order", defaultValue = "asc") String sortOrder,
                                                                              @RequestParam(value = "filter", required = false) String filterField,
                                                                              @RequestParam(value = "value", required = false) String filterValue) {
        PageRequest<List<NoteDTO>> pageRequest = noteService.getByTags(tagNameList, page, size, sortField, sortOrder, filterField, filterValue);
        hateoasAssembler.addLinks(pageRequest.getResultList());
        return ResponseData.success200(pageRequest);
    }

    @GetMapping(BaseURI.BY_USER)
    public ResponseEntity<ResponseData<PageRequest<List<NoteDTO>>>> getByUser(@RequestParam(name = "user_id") Long userId,
                                                                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                              @RequestParam(value = "sort", defaultValue = "id") String sortField,
                                                                              @RequestParam(value = "order", defaultValue = "asc") String sortOrder,
                                                                              @RequestParam(value = "filter", required = false) String filterField,
                                                                              @RequestParam(value = "value", required = false) String filterValue) {

        PageRequest<List<NoteDTO>> pageRequest = noteService.getByUser(userId, page, size, sortField, sortOrder, filterField, filterValue);
        hateoasAssembler.addLinks(pageRequest.getResultList());
        return ResponseData.success200(pageRequest);
    }


    @Operation(summary = "Create note", description = "Create note")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping
    public ResponseEntity<ResponseData<NoteDTO>> create(@Valid @RequestBody NoteCreateDTO dto) {
        NoteDTO certificate = noteService.create(dto);
        hateoasAssembler.addLinks(certificate);
        return ResponseData.success201(certificate);
    }


    @Operation(summary = "Update note", description = "Update note")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<NoteDTO>> update(@PathVariable(value = "id") Long id,
                                                        @Valid @RequestBody NoteUpdateDTO dto) {
        NoteDTO certificate = noteService.update(id, dto);
        hateoasAssembler.addLinks(certificate);
        return ResponseData.success202(certificate);
    }


    @Operation(summary = "Update a price of note", description = "Update a price of note")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData<NoteDTO>> updateOnlyPrice(@PathVariable(value = "id") Long id,
                                                                 @RequestParam(value = "price") Double price) {
        NoteDTO dto = noteService.updateOnlyPrice(id, price);
        hateoasAssembler.addLinks(dto);
        return ResponseData.success202(dto);
    }


    @Operation(summary = "Delete note", description = "Delete note")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return ResponseData.success204(noteService.delete(id));
    }


    @Operation(summary = "Add new tag", description = "Add new tag to note")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/add_tags")
    public ResponseEntity<ResponseData<NoteDTO>> addNewTag(@Valid @RequestBody NoteRequestDTO dto) {
        NoteDTO noteDTO = noteService.addNewTags(dto);
        hateoasAssembler.addLinks(noteDTO);
        return ResponseData.success201(noteDTO);
    }

    @Operation(summary = "attach file", description = "attach file")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/attach/file")
    public ResponseEntity<ResponseData<NoteDTO>> attachFile(@RequestParam(value = "attachment_id") Long attachmentId,
                                                            @RequestParam(value = "note_id") Long noteId) {
        NoteDTO noteDTO = noteService.attachFile(attachmentId, noteId);
        hateoasAssembler.addLinks(noteDTO);
        return ResponseData.success202(noteDTO);
    }

    @Operation(summary = "Delete tags", description = "Delete tags")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/delete_tags/{id}")
    public ResponseEntity<ResponseData<Boolean>> deleteTags(@PathVariable(value = "id") Long noteId,
                                                            @RequestParam(value = "tags") Set<Long> tags) {
        return ResponseData.success204(noteService.deleteTags(noteId, tags));
    }
}
