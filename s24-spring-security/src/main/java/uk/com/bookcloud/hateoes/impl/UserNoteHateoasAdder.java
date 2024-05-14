package uk.com.bookcloud.hateoes.impl;

import uk.com.bookcloud.controller.UserNoteController;
import uk.com.bookcloud.dto.user_note.UserNoteCreateDTO;
import uk.com.bookcloud.dto.user_note.UserNoteDTO;
import uk.com.bookcloud.hateoes.HateoasAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserNoteHateoasAdder implements HateoasAssembler<UserNoteDTO> {
    private final UserHateoasAdder userHateoasAdder;
    private final NoteHateoasAdder noteHateoasAdder;


    @Override
    public void addLinks(UserNoteDTO dto) {
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserNoteController.class).getById(dto.getId())).withSelfRel());
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserNoteController.class).create(new UserNoteCreateDTO())).withRel("create"));
        userHateoasAdder.addLinks(dto.getUser());
        noteHateoasAdder.addLinks(dto.getNote());
    }

    @Override
    public void addLinks(List<UserNoteDTO> dto) {
        dto.forEach(this::addLinks);
    }
}
