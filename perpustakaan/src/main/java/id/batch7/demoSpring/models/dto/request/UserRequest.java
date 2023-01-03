package id.batch7.demoSpring.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
  @NotBlank(message = "Kategori Buku harus diisi!")
  private String nameUser;
}
