package com.github.ewersonc.gamestore.Config;

import com.github.ewersonc.gamestore.Entity.Jogos;
import com.github.ewersonc.gamestore.Repository.JogosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {

    private final JogosRepository jogosRepository;

    public DataLoader(JogosRepository jogosRepository) {
        this.jogosRepository = jogosRepository;
    }

    @Override
    public void run(String... args) {
        Set<String> nomesExistentes = jogosRepository.findAll().stream()
                .map(Jogos::getNome)
                .collect(Collectors.toSet());

        List<Jogos> novosJogos = catalogo().stream()
                .filter(jogo -> !nomesExistentes.contains(jogo.getNome()))
                .toList();

        if (!novosJogos.isEmpty()) {
            jogosRepository.saveAll(novosJogos);
        }
    }

    private List<Jogos> catalogo() {
        return List.of(
                jogo("The Witcher 3: Wild Hunt", "RPG", "CD Projekt Red", 2015, 79.90, "the-witcher-3.jpg",
                        "Geralt de Rívia, caçador de monstros, procura sua filha adotiva em um vasto mundo aberto "
                                + "tomado pela guerra, com escolhas que mudam o destino de regiões inteiras."),
                jogo("Elden Ring", "RPG de ação", "FromSoftware", 2022, 229.90, "elden-ring.jpg",
                        "Explore as Terras Intermédias, um mundo aberto sombrio criado em parceria com George R. R. Martin, "
                                + "enfrentando chefes desafiadores e descobrindo segredos por conta própria."),
                jogo("Red Dead Redemption 2", "Ação e aventura", "Rockstar Games", 2018, 199.90,
                        "red-dead-redemption-2.jpg",
                        "No fim do Velho Oeste, o fora da lei Arthur Morgan e a gangue de Dutch tentam sobreviver "
                                + "enquanto a lei e o progresso fecham o cerco."),
                jogo("Hades", "Roguelike", "Supergiant Games", 2020, 49.90, "hades.jpg",
                        "Como Zagreu, o príncipe do submundo, lute para escapar do reino dos mortos em combates rápidos "
                                + "e a cada fuga descubra mais sobre a mitologia grega."),
                jogo("Hollow Knight", "Metroidvania", "Team Cherry", 2017, 27.99, "hollow-knight.jpg",
                        "Desça ao reino subterrâneo de Hallownest, um mundo vasto e sombrio repleto de insetos, "
                                + "segredos e batalhas exigentes."),
                jogo("Celeste", "Plataforma", "Maddy Makes Games", 2018, 36.99, "celeste.jpg",
                        "Ajude Madeline a escalar a montanha Celeste em fases precisas e difíceis, "
                                + "numa história sensível sobre ansiedade e superação."),
                jogo("Stardew Valley", "Simulação", "ConcernedApe", 2016, 24.99, "stardew-valley.jpg",
                        "Herde uma fazenda abandonada, plante, pesque, explore minas e construa laços com os moradores "
                                + "da cidade no seu próprio ritmo."),
                jogo("Minecraft", "Sandbox", "Mojang Studios", 2011, 129.90, "minecraft.jpg",
                        "Um mundo de blocos gerado proceduralmente onde você coleta recursos, constrói qualquer coisa "
                                + "que imaginar e sobrevive às noites."),
                jogo("Portal 2", "Puzzle", "Valve", 2011, 19.99, "portal-2.jpg",
                        "Use a arma de portais para resolver quebra-cabeças de física nos laboratórios da Aperture Science, "
                                + "com muito humor e uma IA nada confiável."),
                jogo("DOOM Eternal", "FPS", "id Software", 2020, 99.90, "doom-eternal.jpg",
                        "Como o Slayer, enfrente as hordas do inferno em combates frenéticos que premiam agressividade "
                                + "e mobilidade."),
                jogo("Dark Souls III", "RPG de ação", "FromSoftware", 2016, 149.90, "dark-souls-3.jpg",
                        "Em um reino em ruínas onde a chama que sustenta o mundo se apaga, o Inextinto precisa enfrentar "
                                + "inimigos colossais em combates de alta precisão para reunir os Lordes das Cinzas."),
                jogo("Subnautica", "Sobrevivência", "Unknown Worlds Entertainment", 2018, 59.90, "subnautica.jpg",
                        "Depois de cair em um oceano alienígena, construa equipamentos e submarinos para explorar recifes, "
                                + "cavernas e abismos enquanto tenta sobreviver e descobrir o que aconteceu ali.")
        );
    }

    private static Jogos jogo(String nome, String genero, String desenvolvedor, int anoLancamento,
                              double valor, String imagemCapa, String sinopse) {
        Jogos jogo = new Jogos();
        jogo.setNome(nome);
        jogo.setGenero(genero);
        jogo.setDesenvolvedor(desenvolvedor);
        jogo.setAnoLancamento(anoLancamento);
        jogo.setValor(valor);
        jogo.setImagemCapa(imagemCapa);
        jogo.setSinopse(sinopse);
        return jogo;
    }
}
