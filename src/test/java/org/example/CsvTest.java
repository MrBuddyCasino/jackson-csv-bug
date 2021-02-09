package org.example;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class CsvTest {

    @Test
    public void doTest() throws IOException {

        CsvMapper mapper = new CsvMapper();

        Entity e = new Entity();
        e.setNum(10);
        e.getAdditionalProps().put("xxx", "yyy");

        ObjectWriter writer = mapper
                .writerFor(Entity.class)
                .with(mapper.schemaFor(Entity.class));

        StringWriter sw = new StringWriter();
        writer.writeValues(sw).write(e);
        sw.toString();
    }


    static class Entity {

        @JsonProperty
        private int num;

        private Map<String, String> additionalProps = new LinkedHashMap<>();

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @JsonAnyGetter
        public Map<String, String> getAdditionalProps() {
            return additionalProps;
        }

        @JsonAnySetter
        public void setAdditionalProps(Map<String, String> additionalProps) {
            this.additionalProps = additionalProps;
        }
    }

}