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
  private Long bookId;

  @NotBlank(message = "ID user harus diisi!")
  private Integer userId;

  @NotBlank(message = "apakah dipinjam harus diisi!")
  private Boolean isBorrowed;
}
