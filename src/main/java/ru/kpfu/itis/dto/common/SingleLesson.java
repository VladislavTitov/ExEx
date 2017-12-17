package ru.kpfu.itis.dto.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.kpfu.itis.converter.SharedField;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "blocks"
})
public class SingleLesson {

    @SharedField(name = "lesson_id")
    @JsonProperty("id")
    private Long id;

    @SharedField(name = "name")
    @JsonProperty("name")
    private String name;

    @JsonProperty("blocks")
    private List<Block> blocks = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public SingleLesson() {
    }

    /**
     *
     * @param id
     * @param name
     * @param blocks
     */
    public SingleLesson(Long id, String name, List<Block> blocks) {
        super();
        this.id = id;
        this.name = name;
        this.blocks = blocks;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("blocks")
    public List<Block> getBlocks() {
        return blocks;
    }

    @JsonProperty("blocks")
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}