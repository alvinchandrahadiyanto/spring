package id.batch7.demoSpring.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionBookRequest {
  private Long bookId;

  private Integer userId;

  private Boolean isBorrowed;
}
