package uk.com.bookcloud.controller;

import uk.com.bookcloud.base.BaseURI;
import uk.com.bookcloud.common.ResponseData;
import uk.com.bookcloud.dto.user.UserDTO;
import uk.com.bookcloud.hateoes.HateoasAssembler;
import uk.com.bookcloud.service.UserService;
import uk.com.bookcloud.utils.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseURI.API + BaseURI.V1 + BaseURI.USERS)
public class UserController {

    private final UserService userService;
    private final HateoasAssembler<UserDTO> hateoasAssembler;

    @Operation(summary = "Get users", description = "Get users")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<ResponseData<PageRequest<List<UserDTO>>>> getAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "id") String sortField,
            @RequestParam(value = "order", defaultValue = "asc") String sortOrder,
            @RequestParam(value = "filter", required = false) String filterField,
            @RequestParam(value = "value", required = false) String filterValue) {
        PageRequest<List<UserDTO>> pageRequest = userService.getAll(page, size, sortField, sortOrder, filterField, filterValue);
        hateoasAssembler.addLinks(pageRequest.getResultList());
        return ResponseData.success200(pageRequest);
    }

    @Operation(summary = "Get user by ID", description = "Get user by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<UserDTO>> getById(@PathVariable(value = "id") Long id) {
        UserDTO user = userService.getById(id);
        hateoasAssembler.addLinks(user);
        return ResponseData.success200(user);
    }

}
