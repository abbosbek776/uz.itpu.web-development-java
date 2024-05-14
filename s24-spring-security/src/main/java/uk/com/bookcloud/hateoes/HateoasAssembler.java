package uk.com.bookcloud.hateoes;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public interface HateoasAssembler<T extends RepresentationModel<T>> {
    void addLinks(T dto);

    void addLinks(List<T> dto);
}
