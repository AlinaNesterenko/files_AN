package files;

import model.Fruit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonFileTest {
  ObjectMapper mapper = new ObjectMapper();

  @Test
  public void jsonFileParseTest() throws Exception {

    File file = new File("src/test/resources/fruit.json");
    List<Fruit> fruitList = mapper.readValue(file, new TypeReference<>() {
    });
    assertThat(fruitList).hasSize(5);
    assertThat(fruitList.get(0).getType()).isEqualTo("Apple");
    assertThat(fruitList.get(0).getQRCode()).isEqualTo(1);
    assertThat(fruitList.get(0).getWeight()).isEqualTo(100);
    assertThat(fruitList.get(0).getSize()).isEqualTo(3);

    assertThat(fruitList.get(1).getType()).isEqualTo("Pineapple");
    assertThat(fruitList.get(1).getQRCode()).isEqualTo(2);
    assertThat(fruitList.get(1).getWeight()).isEqualTo(200);
    assertThat(fruitList.get(1).getSize()).isEqualTo(2);

    assertThat(fruitList.get(2).getType()).isEqualTo("Mango");
    assertThat(fruitList.get(2).getQRCode()).isEqualTo(3);
    assertThat(fruitList.get(2).getWeight()).isEqualTo(100);
    assertThat(fruitList.get(2).getSize()).isEqualTo(3);

    assertThat(fruitList.get(3).getType()).isEqualTo("Lemon");
    assertThat(fruitList.get(3).getQRCode()).isEqualTo(4);
    assertThat(fruitList.get(3).getWeight()).isEqualTo(1200);
    assertThat(fruitList.get(3).getSize()).isEqualTo(5);

  }
}