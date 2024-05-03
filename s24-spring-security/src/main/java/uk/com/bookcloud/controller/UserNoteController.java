package uk.com.bookcloud.controller;

import uk.com.bookcloud.base.BaseURI;
import uk.com.bookcloud.common.ResponseData;
import uk.com.bookcloud.dto.tag.TagWithTotalCostDTO;
import uk.com.bookcloud.dto.user_note.UserNoteCreateDTO;
import uk.com.bookcloud.dto.user_note.UserNoteDTO;
import uk.com.bookcloud.exceptions.AlreadyExistsException;
import uk.com.bookcloud.exceptions.CustomNotFoundException;
import uk.com.bookcloud.hateoes.impl.UserNoteHateoasAdder;
import uk.com.bookcloud.service.UserOrderService;
import uk.com.bookcloud.utils.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequiredArgsConstructor
//@RequestMapping(BaseURI.API + BaseURI.V1 + BaseURI.USER_NOTES)
public class UserNoteController {

    private final UserOrderService userNoteService;
    private final UserNoteHateoasAdder hateoasAdder;

    @Operation(summary = "Get by ID", description = "Get by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<UserNoteDTO>> getById(@PathVariable(value = "id") Long id) throws CustomNotFoundException,
        AlreadyExistsException {
        UserNoteDTO dto = userNoteService.getById(id);
        hateoasAdder.addLinks(dto);
        return ResponseData.success200(dto);
    }


    @Operation(summary = "Get by user", description = "Get by user")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(BaseURI.BY_USER + "/{id}")
    public ResponseEntity<ResponseData<PageRequest<List<UserNoteDTO>>>> getByUser(@PathVariable(value = "id") Long userId,
                                                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                                  @RequestParam(value = "sort", defaultValue = "id") String sortField,
                                                                                  @RequestParam(value = "order", defaultValue = "asc") String sortOrder,
                                                                                  @RequestParam(value = "filter", required = false) String filterField,
                                                                                  @RequestParam(value = "value", required = false) String filterValue) throws CustomNotFoundException, AlreadyExistsException {
        PageRequest<List<UserNoteDTO>> pageRequest = userNoteService.getByUser(userId, page, size, sortField, sortOrder, filterField, filterValue);
        hateoasAdder.addLinks(pageRequest.getResultList());
        return ResponseData.success200(pageRequest);
    }

    @Operation(summary = "Get most used tag", description = "Get most used tag")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{userId}/popular-tag-highest-cost")
    public ResponseEntity<ResponseData<List<TagWithTotalCostDTO>>> getHighestPriceAndMostUsedTagOfUser(@PathVariable(value = "userId") Long userId) throws CustomNotFoundException, AlreadyExistsException {
        List<TagWithTotalCostDTO> dtoList = userNoteService.getHighestPriceAndMostUsedTagOfUser(userId);
        return ResponseData.success200(dtoList);
    }

    @Operation(summary = "Create Orders", description = "Create Orders")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping
    public ResponseEntity<ResponseData<UserNoteDTO>> create(@RequestBody @Valid UserNoteCreateDTO dto) throws CustomNotFoundException, AlreadyExistsException {
        UserNoteDTO orderDTO = userNoteService.create(dto);
        hateoasAdder.addLinks(orderDTO);
        return ResponseData.success201(orderDTO);
    }
}
