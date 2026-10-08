package com.github.ewersonc.gamestore.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "jogos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogos {

    @Id
    private String id;

    @NotBlank
    private String nome;
    @NotBlank
    private String genero;
    @NotBlank
    private String sinopse;
    @NotBlank
    private String desenvolvedor;

    @Positive
    private Integer anoLancamento;
    @Positive
    private Double valor;

    private String imagemCapa;
}
