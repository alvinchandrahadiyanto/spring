package id.batch7.demoSpring.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionBookRequest {
  @NotBlank(message = "ID Buku harus diisi!")
  private Long book_id;

  @NotBlank(message = "Nama user harus diisi!")
  private String name_user;

  @NotBlank(message = "apakah dipinjam harus diisi!")
  private Boolean is_borrowed;
}
