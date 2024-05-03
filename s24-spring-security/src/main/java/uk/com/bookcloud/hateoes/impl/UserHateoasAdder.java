package uk.com.bookcloud.hateoes.impl;

import uk.com.bookcloud.controller.UserController;
import uk.com.bookcloud.dto.user.UserDTO;
import uk.com.bookcloud.hateoes.HateoasAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHateoasAdder implements HateoasAssembler<UserDTO> {

    @Override
    public void addLinks(List<UserDTO> dto) {
       dto.forEach(this::addLinks);
    }

    @Override
    public void addLinks(UserDTO userDTO) {
        if (userDTO != null) {
            userDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                            .getById(userDTO.getId()))
                    .withSelfRel());
        }
    }
}
