package uk.com.bookcloud.hateoes.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import uk.com.bookcloud.controller.NoteController;
import uk.com.bookcloud.dto.note.NoteCreateDTO;
import uk.com.bookcloud.dto.note.NoteDTO;
import uk.com.bookcloud.dto.note.NoteUpdateDTO;
import uk.com.bookcloud.hateoes.HateoasAssembler;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoteHateoasAdder implements HateoasAssembler<NoteDTO> {

    private final TagHateoasAdder tagHateoasAdder;
    private final UserHateoasAdder userHateoasAdder;

    @Override
    public void addLinks(NoteDTO dto) {
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NoteController.class).getById(dto.getId())).withSelfRel());
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NoteController.class).create(new NoteCreateDTO())).withRel("create"));
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NoteController.class).update(dto.getId(), new NoteUpdateDTO())).withRel("update"));
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NoteController.class).delete(dto.getId())).withRel("delete"));
        tagHateoasAdder.addLinks(dto.getTagList());
        userHateoasAdder.addLinks(dto.getUser());
    }

    @Override
    public void addLinks(List<NoteDTO> dto) {
        dto.forEach(this::addLinks);
    }
}
