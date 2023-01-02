package id.batch7.demoSpring.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
  @NotBlank(message = "Judul Buku harus diisi!")
  private String judulBuku;

  @NotBlank(message = "Penulis harus diisi!")
  private String penulis;

  @NotBlank(message = "Penerbit harus diisi!")
  private String penerbit;

  private String categoryName;
}
