package com.github.ewersonc.gamestore.Entity;
import com.github.ewersonc.gamestore.Enums.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    private String id;

    @NotBlank
    private String cliente;

    @Positive
    private double valorTotal;

    private StatusPedido status;

}
