package project.insta.clone.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.insta.clone.domain.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TagFeedResultDTO{
        private String name;
    }

    public static List<TagFeedResultDTO> toTagFeedDTOList(List<Tag> tags){
        return tags.stream()
                .map(t -> TagFeedResultDTO.builder()
                        .name(t.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
