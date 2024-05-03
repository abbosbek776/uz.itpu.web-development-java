package uk.com.bookcloud.hateoes.impl;

import uk.com.bookcloud.controller.TagController;
import uk.com.bookcloud.dto.tag.TagCreateDTO;
import uk.com.bookcloud.dto.tag.TagDTO;
import uk.com.bookcloud.dto.tag.TagUpdateDTO;
import uk.com.bookcloud.hateoes.HateoasAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagHateoasAdder implements HateoasAssembler<TagDTO> {


    @Override
    public void addLinks(TagDTO dto) {
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).getById(dto.getId())).withSelfRel());
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).create(new TagCreateDTO())).withRel("create"));
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).update(dto.getId(), new TagUpdateDTO())).withRel("update"));
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).delete(dto.getId())).withRel("delete"));

    }

    @Override
    public void addLinks(List<TagDTO> dto) {
        dto.forEach(this::addLinks);
    }
}
