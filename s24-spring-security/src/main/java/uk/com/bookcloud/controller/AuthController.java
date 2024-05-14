package uk.com.bookcloud.controller;

import uk.com.bookcloud.base.BaseURI;
import uk.com.bookcloud.common.ResponseData;
import uk.com.bookcloud.dto.token.ReqLoginDTO;
import uk.com.bookcloud.dto.token.ReqRefreshTokenDTO;
import uk.com.bookcloud.dto.token.SessionDTO;
import uk.com.bookcloud.dto.user.UserCreateDTO;
import uk.com.bookcloud.dto.user.UserDTO;
import uk.com.bookcloud.hateoes.HateoasAssembler;
import uk.com.bookcloud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseURI.API + BaseURI.V1 + BaseURI.PUBLIC)
public class AuthController {

    private final UserService userService;
    private final HateoasAssembler<UserDTO> hateoasAssembler;

    @PostMapping(BaseURI.LOGIN)
    public ResponseEntity<ResponseData<SessionDTO>> login(@RequestBody @Valid ReqLoginDTO dto) {
        SessionDTO session = userService.login(dto);
        hateoasAssembler.addLinks(session.getUser());
        return ResponseData.success200(session);
    }

    @PostMapping(BaseURI.REGISTRATION)
    public ResponseEntity<ResponseData<SessionDTO>> register(@RequestBody @Valid UserCreateDTO dto) {
        SessionDTO session = userService.register(dto);
        hateoasAssembler.addLinks(session.getUser());
        return ResponseData.success201(session);
    }

    @PostMapping(BaseURI.REFRESH + BaseURI.TOKEN)
    public ResponseEntity<ResponseData<SessionDTO>> refreshToken(@RequestBody @Valid ReqRefreshTokenDTO dto) {
        SessionDTO session = userService.refreshToken(dto);
        hateoasAssembler.addLinks(session.getUser());
        return ResponseData.success200(session);
    }
}
