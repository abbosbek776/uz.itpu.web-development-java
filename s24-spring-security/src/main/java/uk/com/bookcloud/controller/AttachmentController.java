package uk.com.bookcloud.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uk.com.bookcloud.base.BaseURI;
import uk.com.bookcloud.common.ResponseData;
import uk.com.bookcloud.dto.attachment.AttachmentDTO;
import uk.com.bookcloud.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping(BaseURI.API + BaseURI.API + BaseURI.ATTACHMENT)
@RequiredArgsConstructor
public class AttachmentController {


    private final AttachmentService attachmentService;

    @Operation(summary = "Upload a file", description = "Uploads a file using multipart/form-data")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = BaseURI.UPLOAD, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseData<AttachmentDTO>> upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        AttachmentDTO dto = attachmentService.upload(file);
        return ResponseData.success200(dto);
    }

    @Operation(summary = "download a file", description = "downloads a file using multipart/form-data")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(BaseURI.DOWNLOAD + "/{filename:.+}")
    public void download(@PathVariable(name = "filename") String name, HttpServletResponse response) {
        attachmentService.download(name, response);
    }


    @Operation(summary = "download a file by ID", description = "downloads a file by ID using multipart/form-data")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public void download(@PathVariable(name = "id") Long id, HttpServletResponse response) {
        attachmentService.download(id, response);
    }
}
